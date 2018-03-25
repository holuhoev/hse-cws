package hse.holuhoev.controller;

import hse.holuhoev.datasource.FacultyDatasource;
import hse.holuhoev.datasource.util.DataSourceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("**/api/faculty")
public class FacultyController {
    private final FacultyDatasource facultyDatasource;

    @Autowired
    public FacultyController(FacultyDatasource facultyDatasource) {
        this.facultyDatasource = facultyDatasource;
    }

    @RequestMapping("/")
    public DataSourceResult getFaculties(@RequestParam(value = "instituteId", required = false) Integer instituteId,
                                         @RequestParam(value = "name", required = false) String name) {
        return facultyDatasource.getFacultyFilter(instituteId, name);
    }
}
