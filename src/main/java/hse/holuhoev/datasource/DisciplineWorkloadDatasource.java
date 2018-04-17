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
        List<DisciplineWorkload> workloads = createWorkloads(
                ruzApiService.getLecturerLessons(lecturerId, fromDate, toDate));

        return DataSourceResult.create(workloads);
    }


    private List<DisciplineWorkload> createWorkloads(List<Lesson> lessons) {
        List<DisciplineWorkload> workloads = lessons.stream()
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
                    workload.setTest(map.getOrDefault(TEST, 0));
                    workload.setSeminar(map.getOrDefault(SEMINAR, 0));
                    workload.setWorkShow(map.getOrDefault(WORK_SHOW, 0));
                    workload.setScience(map.getOrDefault(SCIENCE, 0));
                    workload.setPractice(map.getOrDefault(PRACTICE, 0));
                    workload.setConsultation(map.getOrDefault(CONSULTATION, 0));
                    workload.setOther(map.getOrDefault(NULL, 0));
                    workload.setTotal(map.values().stream().mapToInt(Integer::intValue).sum());
                    return workload;
                }).collect(Collectors.toList());
        DisciplineWorkload summ = new DisciplineWorkload();
        summ.setName("Итог");
        summ.setLecture(0);
        summ.setExam(0);
        summ.setTest(0);
        summ.setSeminar(0);
        summ.setScience(0);
        summ.setWorkShow(0);
        summ.setConsultation(0);
        summ.setPractice(0);
        summ.setOther(0);
        summ.setTotal(0);
        workloads.add(
                workloads.stream()
                        .reduce(summ, (disciplineWorkload, disciplineWorkload2) -> {
                            disciplineWorkload.setTotal(disciplineWorkload.getTotal() + disciplineWorkload2.getTotal());
                            disciplineWorkload.setLecture(disciplineWorkload.getLecture() + disciplineWorkload2.getLecture());
                            disciplineWorkload.setExam(disciplineWorkload.getExam() + disciplineWorkload2.getExam());
                            disciplineWorkload.setTest(disciplineWorkload.getTest() + disciplineWorkload2.getTest());
                            disciplineWorkload.setSeminar(disciplineWorkload.getSeminar() + disciplineWorkload2.getSeminar());
                            disciplineWorkload.setScience(disciplineWorkload.getScience() + disciplineWorkload2.getScience());
                            disciplineWorkload.setWorkShow(disciplineWorkload.getWorkShow() + disciplineWorkload2.getWorkShow());
                            disciplineWorkload.setOther(disciplineWorkload.getOther() + disciplineWorkload2.getOther());
                            disciplineWorkload.setConsultation(disciplineWorkload.getConsultation() + disciplineWorkload2.getConsultation());
                            disciplineWorkload.setPractice(disciplineWorkload.getPractice() + disciplineWorkload2.getPractice());
                            return disciplineWorkload;
                        })
        );

        return workloads;
    }
}
