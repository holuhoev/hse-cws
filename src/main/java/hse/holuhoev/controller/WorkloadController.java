package hse.holuhoev.controller;

import hse.holuhoev.datasource.WorkloadDatasource;
import hse.holuhoev.datasource.util.DataSourceResult;
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
    public DataSourceResult getStudentWorkload(@RequestParam(value = "groupId", required = false) Integer groupId,
                                               @RequestParam(value = "studentId", required = false) Integer studentId,
                                               @RequestParam(value = "facultyId", required = false) Integer facultyId,
                                               @RequestParam(value = "instituteId", required = false) Integer instituteId,
                                               @RequestParam(value = "course", required = false) Integer course,
                                               @RequestParam(value = "studentFio", required = false) String studentFio,
                                               @RequestParam(value = "fromDate", required = false) String fromDate,
                                               @RequestParam(value = "toDate", required = false) String toDate,
                                               @RequestParam(value = "$top", required = false) Integer top,
                                               @RequestParam(value = "$skip", required = false) Integer skip,
                                               @RequestParam(value = "$fetchTotal", required = false) Boolean fetchTotal,
                                               @RequestParam(value = "$orderBy", required = false) String orderBy) {
        return workloadDatasource.getStudentWorkload(groupId
                , studentId
                , facultyId
                , instituteId
                , course
                , studentFio
                , LocalDate.parse(fromDate, formatter)
                , LocalDate.parse(toDate, formatter)
                , top
                , skip
                , fetchTotal
                , orderBy);
    }

    @RequestMapping("/lecturerWorkload")
    public List<LecturerWorkload> getLecturerWorkload(@RequestParam(value = "chairId") Integer chairId,
                                                      @RequestParam(value = "fromDate") String fromDate,
                                                      @RequestParam(value = "toDate") String toDate)

    {
        return workloadDatasource.getLecturerWorkload(chairId, fromDate, toDate);
    }
}
