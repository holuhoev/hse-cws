package hse.holuhoev.loader;

import com.querydsl.core.BooleanBuilder;
import hse.holuhoev.domain.*;
import hse.holuhoev.loader.util.LessonParser;
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
    private final LessonParser lessonParser;

    @Autowired
    public WorkloadLoader(StudentWorkloadRepository studentWorkloadRepository, LecturerWorkloadRepository lecturerWorkloadRepository, StudentRepository studentRepository, LecturerRepository lecturerRepository, RuzApiService ruzApiService, LessonParser lessonParser) {
        this.studentWorkloadRepository = studentWorkloadRepository;
        this.lecturerWorkloadRepository = lecturerWorkloadRepository;
        this.studentRepository = studentRepository;
        this.lecturerRepository = lecturerRepository;
        this.ruzApiService = ruzApiService;
        this.lessonParser = lessonParser;
    }

    public void run(String... strings) {
        if (strings != null && strings.length > 2) {
            logger.info("Workload loader starts.");
            if (Boolean.valueOf(strings[1])) {
                loadStudentWorkload();
            }
            if (Boolean.valueOf(strings[2])) {
                loadLecturerWorkload();
            }
            logger.info("Workload loader ends.");
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
        // TODO: (Delete dublicates)
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

    private void loadLecturerWorkload() {
        logger.info("Lecturer workload loader starts.");
        lecturerWorkloadRepository.deleteAll();
        LocalDate now = LocalDate.now();
        lecturerRepository.findAll().forEach(lecturer -> {
            createAndSaveLecturerWorkload(lecturer, now.minusMonths(3), now.minusDays(1));
            createAndSaveLecturerWorkload(lecturer, now, now.plusMonths(3));
        });
        logger.info("Lecturer workload loader ends.");
    }

    private void createAndSaveLecturerWorkload(Lecturer lecturer, LocalDate fromDate, LocalDate toDate) {
        lecturerWorkloadRepository.saveAll(lessonParser.parse(ruzApiService.getLecturerLessons(lecturer.getLecturerOid(), fromDate, toDate))
                .stream()
                .collect(Collectors.groupingBy(Lesson::getDate, Collectors.summingInt(Lesson::getHours)))
                .entrySet()
                .stream()
                .map(entry -> {
                    LecturerWorkload workload = new LecturerWorkload();
                    workload.setDate(LocalDate.parse(entry.getKey(), RuzApiService.formatter));
                    workload.setLecturerId(lecturer.getLecturerOid());
                    workload.setWorkload(entry.getValue());
                    return workload;
                })
                .collect(Collectors.toList()));
    }
}
