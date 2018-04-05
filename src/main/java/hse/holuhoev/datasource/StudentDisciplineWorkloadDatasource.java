package hse.holuhoev.datasource;

import hse.holuhoev.domain.KindOfWork;
import hse.holuhoev.domain.Lesson;
import hse.holuhoev.domain.StudentDisciplineWorkload;
import hse.holuhoev.ruz.api.RuzApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentDisciplineWorkloadDatasource {
    private final RuzApiService ruzApiService;

    @Autowired
    public StudentDisciplineWorkloadDatasource(RuzApiService ruzApiService) {
        this.ruzApiService = ruzApiService;
    }

    public List<StudentDisciplineWorkload> getData(final Integer studentId,
                                                   final LocalDate fromDate,
                                                   final LocalDate toDate) {
        if (studentId == null || fromDate == null || toDate == null)
            return null;
        // TODO: parse long date period

        return ruzApiService.getStudentLessons(studentId, fromDate, toDate).stream()
                .collect(Collectors.groupingBy(Lesson::getDiscipline))
                .entrySet()
                .stream()
                .map(e -> {
                    StudentDisciplineWorkload workload = new StudentDisciplineWorkload();
                    workload.setName(e.getKey());

                    Map<KindOfWork, Integer> map = e.getValue().stream()
                            .collect(Collectors.groupingBy(Lesson::getKindOfWork,
                                    Collectors.summingInt(Lesson::getHours)));
                    workload.setLecture(map.get(KindOfWork.LECTURE));
                    workload.setExam(map.get(KindOfWork.EXAM));
                    workload.setSeminar(map.get(KindOfWork.SEMINAR));
                    workload.setWorkShow(map.get(KindOfWork.WORK_SHOW));
                    workload.setScience(map.get(KindOfWork.SCIENCE));
                    return workload;
                }).collect(Collectors.toList());
    }
}
