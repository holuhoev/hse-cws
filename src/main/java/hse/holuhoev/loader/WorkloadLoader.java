package hse.holuhoev.loader;

import hse.holuhoev.domain.Lesson;
import hse.holuhoev.domain.Student;
import hse.holuhoev.domain.StudentWorkload;
import hse.holuhoev.loader.util.LessonParser;
import hse.holuhoev.repo.StudentRepository;
import hse.holuhoev.repo.StudentWorkloadRepository;
import hse.holuhoev.ruz.api.RuzApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.stream.Collectors;


@Component
public class WorkloadLoader {
    private final Logger logger = LoggerFactory.getLogger(WorkloadLoader.class);
    private final StudentWorkloadRepository studentWorkloadRepository;
    private final StudentRepository studentRepository;
    private final RuzApiService ruzApiService;
    private final LessonParser lessonParser;

    @Autowired
    public WorkloadLoader(StudentWorkloadRepository studentWorkloadRepository, StudentRepository studentRepository, RuzApiService ruzApiService, LessonParser lessonParser) {
        this.studentWorkloadRepository = studentWorkloadRepository;
        this.studentRepository = studentRepository;
        this.ruzApiService = ruzApiService;
        this.lessonParser = lessonParser;
    }

    public void run() {
        logger.info("Workload loader starts.");
        loadStudentWorkload();
        logger.info("Workload loader ends.");
    }

    private void loadStudentWorkload() {
        logger.info("Student workload loader starts.");
        studentWorkloadRepository.deleteAll();
        LocalDate now = LocalDate.now();
        studentRepository.findAll().forEach(student -> {
            createAndSaveWorkload(student, now, now.plusMonths(3));
            createAndSaveWorkload(student, now.plusMonths(3), now.plusMonths(6));
            createAndSaveWorkload(student, now.plusMonths(6), now.plusMonths(9));
        });
        logger.info("Student workload loader ends.");
    }

    private void createAndSaveWorkload(Student student, LocalDate fromDate, LocalDate toDate) {
        // TODO: (проверить подсчет через hours)
        studentWorkloadRepository.saveAll(lessonParser.parse(ruzApiService.getStudentLessons(student.getStudentOid(), fromDate, toDate))
                .stream()
                .collect(Collectors.groupingBy(Lesson::getDate, Collectors.summingInt(Lesson::getHours)))
                .entrySet()
                .stream()
                .map(entry -> {
                    StudentWorkload workload = new StudentWorkload();
                    workload.setDate(LocalDate.parse(entry.getKey(), RuzApiService.formatter));
                    workload.setStudentId(student.getStudentOid());
                    workload.setWorkload(entry.getValue());
                    return workload;
                })
                .collect(Collectors.toList()));
    }

    private void lecturerWorkloadLoad() {
        logger.info("Lecturer workload loader starts.");
        // TODO:
        logger.info("Lecturer workload loader ends.");
    }
}
