package hse.holuhoev.controller;

import hse.holuhoev.datasource.WorkloadDatasource;
import hse.holuhoev.domain.LecturerWorkload;
import hse.holuhoev.domain.StudentSumWorkload;
import hse.holuhoev.ruz.api.RuzApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Evgeny Kholukhoev
 */
@RestController
@RequestMapping("**/api")
public class WorkloadController {

    private final WorkloadDatasource workloadDatasource;
    private final DateTimeFormatter formatter = RuzApiService.formatter;

    @Autowired
    public WorkloadController(WorkloadDatasource workloadDatasource) {
        this.workloadDatasource = workloadDatasource;
    }

    @RequestMapping("/studentWorkload")
    public List<StudentSumWorkload> getStudentWorkload(@RequestParam(value = "groupId") Integer groupId,
                                                       @RequestParam(value = "fromDate") String fromDate,
                                                       @RequestParam(value = "toDate") String toDate) {
        return workloadDatasource.getStudentWorkload(groupId, LocalDate.parse(fromDate, formatter), LocalDate.parse(toDate, formatter));
    }

    @RequestMapping("/lecturerWorkload")
    public List<LecturerWorkload> getLecturerWorkload(@RequestParam(value = "chairId") Integer chairId,
                                                      @RequestParam(value = "fromDate") String fromDate,
                                                      @RequestParam(value = "toDate") String toDate)

    {
        return workloadDatasource.getLecturerWorkload(chairId, fromDate, toDate);
    }
}
