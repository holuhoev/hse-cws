package hse.holuhoev.datasource;

import static com.google.common.base.Strings.isNullOrEmpty;

import com.querydsl.core.BooleanBuilder;
import hse.holuhoev.datasource.util.DataSourceResult;
import hse.holuhoev.domain.*;
import hse.holuhoev.repo.StudentRepository;
import hse.holuhoev.repo.StudentWorkloadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author Evgeny Kholukhoev
 */
@Service
public class WorkloadDatasource {

    private final StudentWorkloadRepository studentWorkloadRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public WorkloadDatasource(StudentWorkloadRepository studentWorkloadRepository, StudentRepository studentRepository) {
        this.studentWorkloadRepository = studentWorkloadRepository;
        this.studentRepository = studentRepository;
    }

    public List<LecturerWorkload> getLecturerWorkload(final Integer chairId,
                                                      final String fromDate,
                                                      final String toDate) {
        return null;
    }

    public DataSourceResult getStudentWorkload(final Integer groupId,
                                               final Integer studentId,
                                               final Integer facultyId,
                                               final Integer instituteId,
                                               final String studentFio,
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
            studentBuilder.and(qStudent.studentOid.eq(studentId));
        }

        if (facultyId != null) {
            studentBuilder.and(qStudent.facultyID.eq(facultyId));
        }

        if (instituteId != null) {
            studentBuilder.and(qStudent.instituteID.eq(instituteId));
        }

        if (studentFio != null && !studentFio.isEmpty()) {
            studentBuilder.and(qStudent.fio.containsIgnoreCase(studentFio));
        }

        if (fromDate != null) {
            workloadBuilder.and(qStudentWorkload.date.after(fromDate));
        }

        if (toDate != null) {
            workloadBuilder.and(qStudentWorkload.date.before(toDate));
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
            Integer workload =
                    StreamSupport.stream(studentWorkloadRepository.findAll(workloadBuilder.and(qStudentWorkload.studentId.eq(student.getStudentOid()))).spliterator(), false)
                            .mapToInt(StudentWorkload::getWorkload)
                            .sum();
            return new StudentSumWorkload(student.getFio(), workload, student.getStudentOid());
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

    //    NOTE для пагинации: Добавить DataSourceResponse и его возвращать.
    //    - в него класть список возвр объектов - поле result
    //    - хинты для пагинации - поле Map<String,?>
}
