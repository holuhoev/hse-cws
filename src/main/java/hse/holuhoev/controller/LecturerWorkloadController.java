package hse.holuhoev.controller;

import static com.google.common.base.Strings.isNullOrEmpty;

import hse.holuhoev.datasource.DisciplineWorkloadDatasource;
import hse.holuhoev.datasource.LecturerWorkloadDatasource;
import hse.holuhoev.datasource.util.DataSourceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


@RestController
@RequestMapping("**/api/lecturer/")
public class LecturerWorkloadController {
    private final LecturerWorkloadDatasource lecturerWorkloadDatasource;
    private final DisciplineWorkloadDatasource disciplineWorkloadDatasource;

    @Autowired
    public LecturerWorkloadController(LecturerWorkloadDatasource lecturerWorkloadDatasource, DisciplineWorkloadDatasource disciplineWorkloadDatasource) {
        this.lecturerWorkloadDatasource = lecturerWorkloadDatasource;
        this.disciplineWorkloadDatasource = disciplineWorkloadDatasource;
    }

    @RequestMapping("/workload")
    public DataSourceResult getLecturerWorkload(@RequestParam(value = "lecturerId") Integer lecturerId,
                                                @RequestParam(value = "fromDate", required = false) String fromDate,
                                                @RequestParam(value = "toDate", required = false) String toDate) {
        return lecturerWorkloadDatasource.getLecturerWorkload(lecturerId,
                isNullOrEmpty(fromDate) ? null : LocalDate.parse(fromDate),
                isNullOrEmpty(fromDate) ? null : LocalDate.parse(toDate));
    }

    @RequestMapping("/sumWorkload")
    public DataSourceResult getLecturerSumWorkload(@RequestParam(value = "chairId", required = false) Integer chairId,
                                                   @RequestParam(value = "fromDate", required = false) String fromDate,
                                                   @RequestParam(value = "toDate", required = false) String toDate,
                                                   @RequestParam(value = "fio", required = false) String fio,
                                                   @RequestParam(value = "$top", required = false) Integer top,
                                                   @RequestParam(value = "$skip", required = false) Integer skip,
                                                   @RequestParam(value = "$fetchTotal", required = false) Boolean fetchTotal) {
        return lecturerWorkloadDatasource.getLecturerSumWorkload(chairId
                , fio
                , isNullOrEmpty(fromDate) ? null : LocalDate.parse(fromDate)
                , isNullOrEmpty(fromDate) ? null : LocalDate.parse(toDate),
                top,
                skip,
                fetchTotal);
    }

    @RequestMapping("/disciplineWorkload")
    public DataSourceResult getDisciplineWorkload(@RequestParam(value = "lecturerId", required = false) Integer lecturerId,
                                                  @RequestParam(value = "fromDate", required = false) String fromDate,
                                                  @RequestParam(value = "toDate", required = false) String toDate) {
        return disciplineWorkloadDatasource.getLecturerData(lecturerId,
                isNullOrEmpty(fromDate) ? null : LocalDate.parse(fromDate),
                isNullOrEmpty(toDate) ? null : LocalDate.parse(toDate));
    }
}
