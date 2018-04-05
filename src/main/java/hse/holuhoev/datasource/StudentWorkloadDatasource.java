package hse.holuhoev.datasource;

import com.querydsl.core.BooleanBuilder;
import hse.holuhoev.datasource.util.DataSourceResult;
import hse.holuhoev.domain.*;
import hse.holuhoev.repo.StudentRepository;
import hse.holuhoev.repo.StudentWorkloadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Evgeny Kholukhoev
 */
@Service
public class StudentWorkloadDatasource {

    private final StudentWorkloadRepository studentWorkloadRepository;
    private final StudentDataSource studentDataSource;
    private final StudentRepository studentRepository;
    @Autowired
    public StudentWorkloadDatasource(StudentWorkloadRepository studentWorkloadRepository, StudentDataSource studentRepository, StudentRepository studentRepository1) {
        this.studentWorkloadRepository = studentWorkloadRepository;
        this.studentDataSource = studentRepository;
        this.studentRepository = studentRepository1;
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
            studentWorkloads.forEach(studentWorkload -> studentWorkload.setStudentFio(student.getFio()));
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
        DataSourceResult<Student> studentDataSourceResult =
                studentDataSource.getStudentFilter(groupId,
                        studentId,
                        facultyId,
                        instituteId,
                        course,
                        studentFio,
                        educationType,
                        top,
                        skip,
                        fetchTotal,
                        orderBy);

        QStudentWorkload qStudentWorkload = QStudentWorkload.studentWorkload;
        BooleanBuilder workloadBuilder = new BooleanBuilder();

        if (fromDate != null) {
            workloadBuilder.and(qStudentWorkload.date.after(fromDate).or(qStudentWorkload.date.eq(fromDate)));
        }

        if (toDate != null) {
            workloadBuilder.and(qStudentWorkload.date.before(toDate).or(qStudentWorkload.date.before(toDate)));
        }


        List<StudentSumWorkload> result = studentDataSourceResult.getResult().stream().map(student -> {
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

        Map<String, Object> hints = studentDataSourceResult.getHints();
        return DataSourceResult.create(result, hints);
    }
    //    NOTE почему хранить: 1. Можем считать среднюю статистику по загруженности по факультетам/группам/образовательным программам
}
