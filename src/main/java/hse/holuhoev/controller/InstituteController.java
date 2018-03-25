package hse.holuhoev.controller;

import hse.holuhoev.datasource.InstituteDatasource;
import hse.holuhoev.datasource.util.DataSourceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("**/api/institute")
public class InstituteController {
    private final InstituteDatasource instituteDatasource;

    @Autowired
    public InstituteController(InstituteDatasource instituteDatasource) {
        this.instituteDatasource = instituteDatasource;
    }

    @RequestMapping("/")
    public DataSourceResult getInstitutes() {
        return instituteDatasource.getInstituteFilter();
    }
}
