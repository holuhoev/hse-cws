package hse.holuhoev.controller;

import hse.holuhoev.datasource.LecturerDatasource;
import hse.holuhoev.datasource.util.DataSourceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("**/api/lecturer")
public class LecturerController {
    private final LecturerDatasource lecturerDatasource;

    @Autowired
    public LecturerController(LecturerDatasource lecturerDatasource) {
        this.lecturerDatasource = lecturerDatasource;
    }

    @RequestMapping("/getAll")
    public DataSourceResult getAll(@RequestParam(value = "chairId", required = false) Integer groupId,
                                   @RequestParam(value = "lecturerId", required = false) Integer lecturerId,
                                   @RequestParam(value = "lecturerFio", required = false) String lecturerFio) {
        return lecturerDatasource.getLecturerFilter(groupId, lecturerId, lecturerFio);
    }


}
