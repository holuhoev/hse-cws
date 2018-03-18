package hse.holuhoev.loader;

import hse.holuhoev.domain.Lesson;
import hse.holuhoev.domain.Student;
import hse.holuhoev.domain.StudentWorkload;
import hse.holuhoev.repo.StudentRepository;
import hse.holuhoev.repo.StudentWorkloadRepository;
import hse.holuhoev.ruz.api.RuzApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;


@Component
public class WorkloadLoader {
    private final Logger logger = LoggerFactory.getLogger(WorkloadLoader.class);
    private final StudentWorkloadRepository studentWorkloadRepository;
    private final StudentRepository studentRepository;
    private final RuzApiService ruzApiService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.d");

    @Autowired
    public WorkloadLoader(StudentWorkloadRepository studentWorkloadRepository, StudentRepository studentRepository, RuzApiService ruzApiService) {
        this.studentWorkloadRepository = studentWorkloadRepository;
        this.studentRepository = studentRepository;
        this.ruzApiService = ruzApiService;
    }

    public void run() {
        logger.info("Workload loader starts.");
        studentWorkloadLoad();
        logger.info("Workload loader ends.");
    }

    private void studentWorkloadLoad() {
        logger.info("Student workload loader starts.");
        studentWorkloadRepository.deleteAll();
        LocalDate now = LocalDate.now();
        studentRepository.findAll().forEach(student -> {
            createWorkload(student, now, now.plusMonths(3));
            createWorkload(student, now.plusMonths(3), now.plusMonths(6));
            createWorkload(student, now.plusMonths(6), now.plusMonths(9));
        });
        logger.info("Student workload loader ends.");
    }

    private void createWorkload(Student student, LocalDate fromDate, LocalDate toDate) {
        ruzApiService.getStudentLessons(student.getStudentOid(), fromDate.format(formatter), toDate.format(formatter))
                .stream()
                .collect(Collectors.groupingBy(Lesson::getDate, Collectors.counting()))
                .forEach((date, count) -> {
                    StudentWorkload workload = new StudentWorkload();
                    workload.setDate(LocalDate.parse(date, formatter));
                    workload.setStudentId(student.getStudentOid());
                    workload.setWorkload(count.intValue());
                    studentWorkloadRepository.save(workload);
                });
    }

    private void lecturerWorkloadLoad() {
        logger.info("Lecturer workload loader starts.");
        // TODO:
        logger.info("Lecturer workload loader ends.");
    }
}
