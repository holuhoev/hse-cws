package hse.holuhoev.controller;

import hse.holuhoev.datasource.StudentDataSource;
import hse.holuhoev.datasource.util.DataSourceResult;
import hse.holuhoev.domain.Course;
import hse.holuhoev.domain.EducationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("**/api/student")
public class StudentController {
    private final StudentDataSource studentDataSource;

    @Autowired
    public StudentController(StudentDataSource studentDataSource) {
        this.studentDataSource = studentDataSource;
    }

    @RequestMapping("/getAll")
    public DataSourceResult getAll(@RequestParam(value = "groupId", required = false) Integer groupId,
                                   @RequestParam(value = "studentId", required = false) Integer studentId,
                                   @RequestParam(value = "facultyId", required = false) Integer facultyId,
                                   @RequestParam(value = "instituteId", required = false) Integer instituteId,
                                   @RequestParam(value = "course", required = false) Course course,
                                   @RequestParam(value = "studentFio", required = false) String studentFio,
                                   @RequestParam(value = "educationType", required = false) EducationType educationType,
                                   @RequestParam(value = "$top", required = false) Integer top,
                                   @RequestParam(value = "$skip", required = false) Integer skip,
                                   @RequestParam(value = "$fetchTotal", required = false) Boolean fetchTotal,
                                   @RequestParam(value = "$orderBy", required = false) String orderBy) {
        return studentDataSource.getStudentFilter(groupId
                , studentId
                , facultyId
                , instituteId
                , course
                , studentFio
                , educationType
                , top
                , skip
                , fetchTotal
                , orderBy);
    }
}
