package hse.holuhoev.controller;

import hse.holuhoev.datasource.LecturerWorkloadDatasource;
import hse.holuhoev.domain.LecturerWorkload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("**/api/lecturer/")
public class LecturerWorkloadController {
    private final LecturerWorkloadDatasource lecturerWorkloadDatasource;

    @Autowired
    public LecturerWorkloadController(LecturerWorkloadDatasource lecturerWorkloadDatasource) {
        this.lecturerWorkloadDatasource = lecturerWorkloadDatasource;
    }

    @RequestMapping("/sumWorkload")
    public List<LecturerWorkload> getLecturerWorkload(@RequestParam(value = "chairId") Integer chairId,
                                                      @RequestParam(value = "fromDate") String fromDate,
                                                      @RequestParam(value = "toDate") String toDate)

    {
        return lecturerWorkloadDatasource.getLecturerWorkload(chairId, fromDate, toDate);
    }
}
