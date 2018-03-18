package hse.holuhoev.datasource;

import hse.holuhoev.ruz.api.RuzApiService;
import hse.holuhoev.domain.LecturerWorkload;
import hse.holuhoev.domain.StudentWorkload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Evgeny Kholukhoev
 */
@Service
public class WorkloadDatasource {

    private final RuzApiService ruzApiService;

    @Autowired
    public WorkloadDatasource(RuzApiService ruzApiService) {
        this.ruzApiService = ruzApiService;
    }

    public List<LecturerWorkload> getLecturerWorkload(final Integer chairId,
                                                      final String fromDate,
                                                      final String toDate) {
        return null;
//        return ruzApiService.getLecturers(chairId)
//                .stream()
//                .map(ruzLecturer ->
//                        new LecturerWorkload(
//                                ruzApiService.getLecturerLessons(ruzLecturer.getLecturerOid(), fromDate, toDate).size()
//                                , ruzLecturer.getLecturerOid()
//                                , ruzLecturer.getFio()
//                        )
//                ).collect(Collectors.toList());
    }

    public List<StudentWorkload> getStudentWorkload(final Integer groupId,
                                                    final String fromDate,
                                                    final String toDate) {
        return null;
//        return ruzApiService.getStudents(groupId)
//                .stream()
//                .map(ruzStudent ->
//                        new StudentWorkload(
//                                ruzApiService.getStudentLessons(ruzStudent.getStudentOid(), fromDate, toDate).size()
//                                , ruzStudent.getStudentOid()
//                                , ruzStudent.getFio()
//                        )
//                ).collect(Collectors.toList());
    }

    //    NOTE для фильтров: Если запрашивают в фильтре по имени фамилии
    //    +добавить всех лекторов и студентов в бд.
    //    -добавляем пагинацию
    //    +делаем репозиторий PagingAndSorting
    //    -берем данные из репозитория
    //    -далее аналогично как по группе

    //    NOTE для пагинации: Добавить DataSourceResponse и его возвращать.
    //    - в него класть список возвр объектов - поле result
    //    - хинты для пагинации - поле Map<String,?>

    //    NOTE для разбития сдвоенных/строенных пар:
    //    - после того как получили List<RuzLessons> отдаем их в LessonParser и там разбиваем и возвращаем List<RuzLessons>
}
