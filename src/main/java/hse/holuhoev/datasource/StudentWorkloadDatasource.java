package hse.holuhoev.datasource;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.base.Strings.repeat;

import com.querydsl.core.BooleanBuilder;
import hse.holuhoev.datasource.util.DataSourceResult;
import hse.holuhoev.domain.*;
import hse.holuhoev.repo.StudentRepository;
import hse.holuhoev.repo.StudentWorkloadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author Evgeny Kholukhoev
 */
@Service
public class StudentWorkloadDatasource {

    private final StudentWorkloadRepository studentWorkloadRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public StudentWorkloadDatasource(StudentWorkloadRepository studentWorkloadRepository, StudentRepository studentRepository) {
        this.studentWorkloadRepository = studentWorkloadRepository;
        this.studentRepository = studentRepository;
    }

    public DataSourceResult getStudentWorkload(final Integer studentId,
                                               final LocalDate fromDate,
                                               final LocalDate toDate) {

        if (studentId == null) {
            return DataSourceResult.createEmpty();
        }
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            QStudentWorkload qStudentWorkload = QStudentWorkload.studentWorkload;
            BooleanBuilder builder = new BooleanBuilder();
            builder.and(qStudentWorkload.studentId.eq(student.getId()));
            if (fromDate != null) {
                builder.and(qStudentWorkload.date.after(fromDate).or(qStudentWorkload.date.eq(fromDate)));
            }

            if (toDate != null) {
                builder.and(qStudentWorkload.date.before(toDate).or(qStudentWorkload.date.before(toDate)));
            }
            Iterable<StudentWorkload> studentWorkloads = studentWorkloadRepository.findAll(builder);
            return DataSourceResult.create(studentWorkloads);
        }
        return DataSourceResult.createEmpty();
    }

    public DataSourceResult getStudentSumWorkload(final Integer groupId,
                                                  final Integer studentId,
                                                  final Integer facultyId,
                                                  final Integer instituteId,
                                                  final Course course,
                                                  final String studentFio,
                                                  final EducationType educationType,
                                                  final LocalDate fromDate,
                                                  final LocalDate toDate,
                                                  final Integer top,
                                                  final Integer skip,
                                                  final Boolean fetchTotal,
                                                  final String orderBy) {
        QStudent qStudent = QStudent.student;
        QStudentWorkload qStudentWorkload = QStudentWorkload.studentWorkload;

        BooleanBuilder studentBuilder = new BooleanBuilder();
        BooleanBuilder workloadBuilder = new BooleanBuilder();

        if (groupId != null) {
            studentBuilder.and(qStudent.groupID.eq(groupId));
        }

        if (studentId != null) {
            studentBuilder.and(qStudent.Id.eq(studentId));
        }

        if (facultyId != null) {
            studentBuilder.and(qStudent.facultyID.eq(facultyId));
        }

        if (instituteId != null) {
            studentBuilder.and(qStudent.instituteID.eq(instituteId));
        }

        if (course != null) {
            studentBuilder.and(qStudent.course.eq(course));
        }
        if (studentFio != null && !studentFio.isEmpty()) {
            studentBuilder.and(qStudent.fio.containsIgnoreCase(studentFio));
        }

        if (educationType != null) {
            studentBuilder.and(qStudent.educationType.eq(educationType));
        }

        if (fromDate != null) {
            workloadBuilder.and(qStudentWorkload.date.after(fromDate).or(qStudentWorkload.date.eq(fromDate)));
        }

        if (toDate != null) {
            workloadBuilder.and(qStudentWorkload.date.before(toDate).or(qStudentWorkload.date.before(toDate)));
        }

        Iterable<Student> students;
        if (top != null) {
            String orderByString = isNullOrEmpty(orderBy) ? "fio" : orderBy;
            Pageable limit = PageRequest.of(skip, top, Sort.Direction.ASC, orderByString);
            students = studentRepository.findAll(studentBuilder, limit);
        } else {
            students = studentRepository.findAll(studentBuilder);
        }

        Stream<Student> studentStream = StreamSupport.stream(students.spliterator(), false);
        List<StudentSumWorkload> result = studentStream.map(student -> {
            BooleanBuilder builder = new BooleanBuilder();
            builder.and(workloadBuilder).and(qStudentWorkload.studentId.eq(student.getId()));
            // Если запросили тот период, по которому нет инфы, то запрашивать из руза и добавлять в загруженность
            // Еще надо смотреть, что есть такой date == toDate, если нет, то искать макисмальный, а остальное запросить
            Integer workload =
                    StreamSupport.stream(studentWorkloadRepository.findAll(builder).spliterator(), false)
                            .mapToInt(StudentWorkload::getWorkload)
                            .sum();
            return new StudentSumWorkload(student.getFio(), workload, student.getId());
        }).collect(Collectors.toList());

        Map<String, Object> hints = new HashMap<>();
        hints.put("paging", true);
        if (fetchTotal != null && fetchTotal) {
            Long count = studentRepository.count(studentBuilder);
            hints.put("total", count);
        }
        return DataSourceResult.create(result, hints);
    }
    //    NOTE почему хранить: 1. Можем считать среднюю статистику по загруженности по факультетам/группам/образовательным программам
}
