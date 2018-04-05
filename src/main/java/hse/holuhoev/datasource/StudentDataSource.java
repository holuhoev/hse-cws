package hse.holuhoev.datasource;

import com.querydsl.core.BooleanBuilder;
import hse.holuhoev.datasource.util.DataSourceResult;
import hse.holuhoev.domain.Course;
import hse.holuhoev.domain.EducationType;
import hse.holuhoev.domain.QStudent;
import hse.holuhoev.domain.Student;
import hse.holuhoev.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Strings.isNullOrEmpty;

@Service
public class StudentDataSource {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentDataSource(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public DataSourceResult<Student> getStudentFilter(final Integer groupId,
                                             final Integer studentId,
                                             final Integer facultyId,
                                             final Integer instituteId,
                                             final Course course,
                                             final String studentFio,
                                             final EducationType educationType,
                                             final Integer top,
                                             final Integer skip,
                                             final Boolean fetchTotal,
                                             final String orderBy) {
        QStudent qStudent = QStudent.student;
        BooleanBuilder studentBuilder = new BooleanBuilder();

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

        Iterable<Student> students;
        if (top != null) {
            String orderByString = isNullOrEmpty(orderBy) ? "fio" : orderBy;
            Pageable limit = PageRequest.of(skip, top, Sort.Direction.ASC, orderByString);
            students = studentRepository.findAll(studentBuilder, limit);
        } else {
            students = studentRepository.findAll(studentBuilder);
        }

        Map<String, Object> hints = new HashMap<>();
        hints.put("paging", true);
        if (fetchTotal != null && fetchTotal) {
            Long count = studentRepository.count(studentBuilder);
            hints.put("total", count);
        }
        return DataSourceResult.create(students, hints);
    }
}
