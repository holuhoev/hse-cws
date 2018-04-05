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
                    workload.setLecture(map.getOrDefault(KindOfWork.LECTURE,0));
                    workload.setExam(map.getOrDefault(KindOfWork.EXAM,0));
                    workload.setSeminar(map.getOrDefault(KindOfWork.SEMINAR,0));
                    workload.setWorkShow(map.getOrDefault(KindOfWork.WORK_SHOW,0));
                    workload.setScience(map.getOrDefault(KindOfWork.SCIENCE,0));
                    workload.setPractice(map.getOrDefault(KindOfWork.PRACTICE,0));
                    workload.setOther(map.getOrDefault(KindOfWork.NULL,0));
                    return workload;
                }).collect(Collectors.toList());
    }
}
