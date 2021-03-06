package hse.holuhoev.loader;

import hse.holuhoev.domain.*;
import hse.holuhoev.repo.LecturerRepository;
import hse.holuhoev.repo.LecturerWorkloadRepository;
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
    private final LecturerWorkloadRepository lecturerWorkloadRepository;
    private final StudentRepository studentRepository;
    private final LecturerRepository lecturerRepository;
    private final RuzApiService ruzApiService;

    @Autowired
    public WorkloadLoader(StudentWorkloadRepository studentWorkloadRepository
            , LecturerWorkloadRepository lecturerWorkloadRepository
            , StudentRepository studentRepository
            , LecturerRepository lecturerRepository
            , RuzApiService ruzApiService) {
        this.studentWorkloadRepository = studentWorkloadRepository;
        this.lecturerWorkloadRepository = lecturerWorkloadRepository;
        this.studentRepository = studentRepository;
        this.lecturerRepository = lecturerRepository;
        this.ruzApiService = ruzApiService;
    }

    public void run(String... strings) {
        if (strings != null && strings.length > 2) {
            if (Boolean.valueOf(strings[1])) {
                loadStudentWorkload();
            }
            if (Boolean.valueOf(strings[2])) {
                loadLecturerWorkload();
            }
        }
    }

    private void loadStudentWorkload() {
        logger.info("Student workload loader starts.");
        LocalDate now = LocalDate.now();
        QStudentWorkload qStudentWorkload = QStudentWorkload.studentWorkload;
        studentWorkloadRepository.deleteAll(studentWorkloadRepository.findAll(qStudentWorkload.date.after(now).or(qStudentWorkload.date.eq(now))));
        studentRepository.findAll().forEach(student -> {
            createAndSaveStudentWorkload(student, now, now.plusMonths(3));
        });
        logger.info("Student workload loader ends.");
    }

    private void createAndSaveStudentWorkload(Student student, LocalDate fromDate, LocalDate toDate) {
        studentWorkloadRepository.saveAll(ruzApiService.getStudentLessons(student.getId(), fromDate, toDate)
                .stream()
                .collect(Collectors.groupingBy(Lesson::getDate, Collectors.summingInt(Lesson::getHours)))
                .entrySet()
                .stream()
                .map(entry -> {
                    StudentWorkload workload = new StudentWorkload();
                    workload.setDate(LocalDate.parse(entry.getKey(), RuzApiService.formatter));
                    workload.setStudentId(student.getId());
                    workload.setWorkload(entry.getValue());
                    return workload;
                })
                .collect(Collectors.toList()));
    }

    private void loadLecturerWorkload() {
        logger.info("Lecturer workload loader starts.");
        LocalDate fromDate = LocalDate.now();
        // TODO: Scheduler
        // Удалять только загруженности, где дата >= "сегодня"
        QLecturerWorkload qLecturerWorkload = QLecturerWorkload.lecturerWorkload;
        lecturerWorkloadRepository.deleteAll(
                lecturerWorkloadRepository.findAll(
                        qLecturerWorkload.date.eq(fromDate)
                                .or(qLecturerWorkload.date.after(fromDate))
                )
        );
        lecturerRepository.findAll().forEach(lecturer -> {
            // загружаем на полгода вперед.
            createAndSaveLecturerWorkload(lecturer, fromDate, fromDate.plusMonths(3));
            createAndSaveLecturerWorkload(lecturer, fromDate.plusMonths(3).plusDays(1), fromDate.plusMonths(6));
        });
        logger.info("Lecturer workload loader ends.");
    }

    private void createAndSaveLecturerWorkload(Lecturer lecturer, LocalDate fromDate, LocalDate toDate) {
        lecturerWorkloadRepository.saveAll(ruzApiService.getLecturerLessons(lecturer.getId(), fromDate, toDate)
                .stream()
                .collect(Collectors.groupingBy(Lesson::getDate, Collectors.summingInt(Lesson::getHours)))
                .entrySet()
                .stream()
                .map(entry -> {
                    LecturerWorkload workload = new LecturerWorkload();
                    workload.setDate(LocalDate.parse(entry.getKey(), RuzApiService.formatter));
                    workload.setLecturerId(lecturer.getId());
                    workload.setWorkload(entry.getValue());
                    return workload;
                })
                .collect(Collectors.toList()));
    }
}
