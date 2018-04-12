package hse.holuhoev.datasource;

import hse.holuhoev.datasource.util.DataSourceResult;
import hse.holuhoev.domain.KindOfWork;
import hse.holuhoev.domain.Lesson;
import hse.holuhoev.domain.DisciplineWorkload;
import hse.holuhoev.ruz.api.RuzApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static hse.holuhoev.domain.KindOfWork.*;

@Service
public class DisciplineWorkloadDatasource {
    private final RuzApiService ruzApiService;

    @Autowired
    public DisciplineWorkloadDatasource(RuzApiService ruzApiService) {
        this.ruzApiService = ruzApiService;
    }

    public DataSourceResult getStudentData(final Integer studentId,
                                           final LocalDate fromDate,
                                           final LocalDate toDate) {
        if (studentId == null || fromDate == null || toDate == null)
            return DataSourceResult.createEmpty();
        Iterable<DisciplineWorkload> result = createWorkloads(
                ruzApiService.getStudentLessons(studentId, fromDate, toDate));

        return DataSourceResult.create(result);
    }

    public DataSourceResult getLecturerData(final Integer lecturerId,
                                            final LocalDate fromDate,
                                            final LocalDate toDate) {
        if (lecturerId == null || fromDate == null || toDate == null)
            return DataSourceResult.createEmpty();
        Iterable<DisciplineWorkload> result = createWorkloads(
                ruzApiService.getLecturerLessons(lecturerId, fromDate, toDate));

        return DataSourceResult.create(result);
    }


    private Iterable<DisciplineWorkload> createWorkloads(List<Lesson> lessons) {
        // TODO: parse long date period
        return lessons.stream()
                .collect(Collectors.groupingBy(Lesson::getDiscipline))
                .entrySet()
                .stream()
                .map(e -> {
                    DisciplineWorkload workload = new DisciplineWorkload();
                    workload.setName(e.getKey());

                    Map<KindOfWork, Integer> map = e.getValue().stream()
                            .collect(Collectors.groupingBy(Lesson::getKindOfWork,
                                    Collectors.summingInt(Lesson::getHours)));
                    workload.setLecture(map.getOrDefault(LECTURE, 0));
                    workload.setExam(map.getOrDefault(EXAM, 0));
                    workload.setSeminar(map.getOrDefault(SEMINAR, 0));
                    workload.setWorkShow(map.getOrDefault(WORK_SHOW, 0));
                    workload.setScience(map.getOrDefault(SCIENCE, 0));
                    workload.setPractice(map.getOrDefault(PRACTICE, 0));
                    workload.setConsultation(map.getOrDefault(CONSULTATION, 0));
                    workload.setOther(map.getOrDefault(NULL, 0));
                    return workload;
                }).collect(Collectors.toList());
    }
}
