package hse.holuhoev.controller;

import static com.google.common.base.Strings.isNullOrEmpty;

import hse.holuhoev.datasource.LecturerWorkloadDatasource;
import hse.holuhoev.datasource.util.DataSourceResult;
import hse.holuhoev.ruz.api.RuzApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@RestController
@RequestMapping("**/api/lecturer/")
public class LecturerWorkloadController {
    private final LecturerWorkloadDatasource lecturerWorkloadDatasource;

    @Autowired
    public LecturerWorkloadController(LecturerWorkloadDatasource lecturerWorkloadDatasource) {
        this.lecturerWorkloadDatasource = lecturerWorkloadDatasource;
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
                                                   @RequestParam(value = "fio", required = false) String fio) {
        return lecturerWorkloadDatasource.getLecturerSumWorkload(chairId
                , fio
                , isNullOrEmpty(fromDate) ? null : LocalDate.parse(fromDate)
                , isNullOrEmpty(fromDate) ? null : LocalDate.parse(toDate));
    }
}
