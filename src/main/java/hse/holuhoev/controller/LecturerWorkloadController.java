package hse.holuhoev.controller;

import hse.holuhoev.datasource.LecturerWorkloadDatasource;
import hse.holuhoev.datasource.util.DataSourceResult;
import hse.holuhoev.domain.LecturerWorkload;
import hse.holuhoev.ruz.api.RuzApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController
@RequestMapping("**/api/lecturer/")
public class LecturerWorkloadController {
    private final LecturerWorkloadDatasource lecturerWorkloadDatasource;
    private final DateTimeFormatter formatter = RuzApiService.formatter;

    @Autowired
    public LecturerWorkloadController(LecturerWorkloadDatasource lecturerWorkloadDatasource) {
        this.lecturerWorkloadDatasource = lecturerWorkloadDatasource;
    }

    @RequestMapping("/sumWorkload")
    public DataSourceResult getLecturerWorkload(@RequestParam(value = "chairId") Integer chairId,
                                                @RequestParam(value = "fromDate") String fromDate,
                                                @RequestParam(value = "toDate") String toDate)

    {
        return lecturerWorkloadDatasource.getLecturerWorkload(chairId, LocalDate.parse(fromDate, formatter), LocalDate.parse(toDate, formatter));
    }
}
