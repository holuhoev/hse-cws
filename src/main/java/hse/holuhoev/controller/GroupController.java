package hse.holuhoev.controller;

import hse.holuhoev.datasource.GroupDatasource;
import hse.holuhoev.datasource.util.DataSourceResult;
import hse.holuhoev.domain.Course;
import hse.holuhoev.domain.EducationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("**/api/group")
public class GroupController {
    private final GroupDatasource groupDatasource;

    @Autowired
    public GroupController(GroupDatasource groupDatasource) {
        this.groupDatasource = groupDatasource;
    }

    @RequestMapping("/")
    public DataSourceResult getGroups(@RequestParam(value = "facultyId", required = false) Integer facultyId,
                                      @RequestParam(value = "instituteId", required = false) Integer instituteId,
                                      @RequestParam(value = "course", required = false) Course course,
                                      @RequestParam(value = "number", required = false) String number,
                                      @RequestParam(value = "educationType", required = false) EducationType educationType) {
        return groupDatasource.getGroupFilter(facultyId, instituteId, course, number, educationType);
    }
}
