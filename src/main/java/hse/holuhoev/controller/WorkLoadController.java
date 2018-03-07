package hse.holuhoev.controller;

import hse.holuhoev.to.StudentWorkload;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Evgeny Kholukhoev
 */
@RestController
@RequestMapping("**/api")
public class WorkLoadController {

    @RequestMapping("/studentWorkLoads")
    public List<StudentWorkload> getStudentsWorkLoads(@RequestParam(value = "groupId") Long groupId,
                                                      @RequestParam(value = "fromDate") String fromDate,
                                                      @RequestParam(value = "toDate") String toDate)

    {
        return null;
    }
}
