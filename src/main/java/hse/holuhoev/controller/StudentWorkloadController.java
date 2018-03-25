package hse.holuhoev.controller;

import hse.holuhoev.datasource.StudentWorkloadDatasource;
import hse.holuhoev.datasource.util.DataSourceResult;
import hse.holuhoev.domain.Course;
import hse.holuhoev.domain.EducationType;
import hse.holuhoev.ruz.api.RuzApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Evgeny Kholukhoev
 */
@RestController
@RequestMapping("**/api/student/")
public class StudentWorkloadController {

    private final StudentWorkloadDatasource studentWorkloadDatasource;
    private final DateTimeFormatter formatter = RuzApiService.formatter;

    @Autowired
    public StudentWorkloadController(StudentWorkloadDatasource studentWorkloadDatasource) {
        this.studentWorkloadDatasource = studentWorkloadDatasource;
    }

    @RequestMapping("/sumWorkload")
    public DataSourceResult getStudentWorkload(@RequestParam(value = "groupId", required = false) Integer groupId,
                                               @RequestParam(value = "studentId", required = false) Integer studentId,
                                               @RequestParam(value = "facultyId", required = false) Integer facultyId,
                                               @RequestParam(value = "instituteId", required = false) Integer instituteId,
                                               @RequestParam(value = "course", required = false) Course course,
                                               @RequestParam(value = "studentFio", required = false) String studentFio,
                                               @RequestParam(value = "educationType", required = false) EducationType educationType,
                                               @RequestParam(value = "fromDate", required = false) String fromDate,
                                               @RequestParam(value = "toDate", required = false) String toDate,
                                               @RequestParam(value = "$top", required = false) Integer top,
                                               @RequestParam(value = "$skip", required = false) Integer skip,
                                               @RequestParam(value = "$fetchTotal", required = false) Boolean fetchTotal,
                                               @RequestParam(value = "$orderBy", required = false) String orderBy) {
        return studentWorkloadDatasource.getStudentWorkload(groupId
                , studentId
                , facultyId
                , instituteId
                , course
                , studentFio
                , educationType
                , LocalDate.parse(fromDate, formatter)
                , LocalDate.parse(toDate, formatter)
                , top
                , skip
                , fetchTotal
                , orderBy);
    }
}
