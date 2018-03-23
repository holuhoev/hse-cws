package hse.holuhoev.datasource;

import com.querydsl.core.BooleanBuilder;
import hse.holuhoev.domain.*;
import hse.holuhoev.repo.StudentRepository;
import hse.holuhoev.repo.StudentWorkloadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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

    public List<StudentSumWorkload> getStudentWorkload(final Integer groupId,
                                                       final Integer studentId,
                                                       final Integer facultyId,
                                                       final Integer instituteId,
                                                       final String studentFio,
                                                       final LocalDate fromDate,
                                                       final LocalDate toDate) {
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

        Stream<Student> studentStream = StreamSupport.stream(studentRepository.findAll(studentBuilder).spliterator(), false);
        return studentStream.map(student -> {
            Integer workload =
                    StreamSupport.stream(studentWorkloadRepository.findAll(workloadBuilder.and(qStudentWorkload.studentId.eq(student.getStudentOid()))).spliterator(), false)
                            .mapToInt(StudentWorkload::getWorkload)
                            .sum();
            return new StudentSumWorkload(student.getFio(), workload, student.getStudentOid());
        }).collect(Collectors.toList());
    }

    //    NOTE почему хранить: 1. Можем считать среднюю статистику по загруженности по факультетам/группам/образовательным программам
    //    NOTE для фильтров: Если запрашивают в фильтре по имени фамилии
    //    +добавить всех лекторов и студентов в бд.
    //    -добавляем пагинацию
    //    +делаем репозиторий PagingAndSorting
    //    +берем данные из репозитория
    //    -далее аналогично как по группе

    //    NOTE для пагинации: Добавить DataSourceResponse и его возвращать.
    //    - в него класть список возвр объектов - поле result
    //    - хинты для пагинации - поле Map<String,?>
}
