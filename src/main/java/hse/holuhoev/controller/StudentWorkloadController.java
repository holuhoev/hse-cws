package hse.holuhoev.controller;

import hse.holuhoev.datasource.StudentDisciplineWorkloadDatasource;
import hse.holuhoev.datasource.StudentWorkloadDatasource;
import hse.holuhoev.datasource.util.DataSourceResult;
import hse.holuhoev.domain.Course;
import hse.holuhoev.domain.EducationType;
import hse.holuhoev.domain.StudentDisciplineWorkload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * @author Evgeny Kholukhoev
 */
@RestController
@RequestMapping("**/api/student/")
public class StudentWorkloadController {
    private final StudentWorkloadDatasource studentWorkloadDatasource;
    private final StudentDisciplineWorkloadDatasource studentDisciplineWorkloadDatasource;

    @Autowired
    public StudentWorkloadController(StudentWorkloadDatasource studentWorkloadDatasource, StudentDisciplineWorkloadDatasource studentDisciplineWorkloadDatasource) {
        this.studentWorkloadDatasource = studentWorkloadDatasource;
        this.studentDisciplineWorkloadDatasource = studentDisciplineWorkloadDatasource;
    }

    @RequestMapping("/disciplineWorkload")
    public DataSourceResult getStudentDisciplineWorkload(@RequestParam(value = "studentId") Integer studentId,
                                                                        @RequestParam(value = "fromDate", required = false) String fromDate,
                                                                        @RequestParam(value = "toDate", required = false) String toDate) {
        return studentDisciplineWorkloadDatasource.getData(studentId,
                isNullOrEmpty(fromDate) ? null : LocalDate.parse(fromDate),
                isNullOrEmpty(fromDate) ? null : LocalDate.parse(toDate));
    }

    @RequestMapping("/workload")
    public DataSourceResult getStudentWorkload(@RequestParam(value = "studentId") Integer studentId,
                                               @RequestParam(value = "fromDate", required = false) String fromDate,
                                               @RequestParam(value = "toDate", required = false) String toDate) {
        return studentWorkloadDatasource.getStudentWorkload(studentId,
                isNullOrEmpty(fromDate) ? null : LocalDate.parse(fromDate),
                isNullOrEmpty(fromDate) ? null : LocalDate.parse(toDate));
    }

    @RequestMapping("/sumWorkload")
    public DataSourceResult getStudentSumWorkload(@RequestParam(value = "groupId", required = false) Integer groupId,
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
        return studentWorkloadDatasource.getStudentSumWorkload(groupId
                , studentId
                , facultyId
                , instituteId
                , course
                , studentFio
                , educationType
                , isNullOrEmpty(fromDate) ? null : LocalDate.parse(fromDate)
                , isNullOrEmpty(toDate) ? null : LocalDate.parse(toDate)
                , top
                , skip
                , fetchTotal
                , orderBy);
    }
}
