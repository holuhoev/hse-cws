package hse.holuhoev.controller;

import hse.holuhoev.datasource.WorkloadDatasource;
import hse.holuhoev.domain.LecturerWorkload;
import hse.holuhoev.domain.StudentWorkload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Evgeny Kholukhoev
 */
@RestController
@RequestMapping("**/api")
public class WorkloadController {

    private final WorkloadDatasource workloadDatasource;

    @Autowired
    public WorkloadController(WorkloadDatasource workloadDatasource) {
        this.workloadDatasource = workloadDatasource;
    }

    @RequestMapping("/studentWorkload")
    public List<StudentWorkload> getStudentWorkload(@RequestParam(value = "groupId") Integer groupId,
                                                    @RequestParam(value = "fromDate") String fromDate,
                                                    @RequestParam(value = "toDate") String toDate)

    {
        return workloadDatasource.getStudentWorkload(groupId,fromDate,toDate);
    }

    @RequestMapping("/lecturerWorkload")
    public List<LecturerWorkload> getLecturerWorkload(@RequestParam(value = "chairId") Integer chairId,
                                                      @RequestParam(value = "fromDate") String fromDate,
                                                      @RequestParam(value = "toDate") String toDate)

    {
        return workloadDatasource.getLecturerWorkload(chairId,fromDate,toDate);
    }
}
