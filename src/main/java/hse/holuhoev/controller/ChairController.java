package hse.holuhoev.controller;

import hse.holuhoev.datasource.ChairDatasource;
import hse.holuhoev.datasource.util.DataSourceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("**/api/chair")
public class ChairController {
    private final ChairDatasource chairDatasource;

    @Autowired
    public ChairController(ChairDatasource chairDatasource) {
        this.chairDatasource = chairDatasource;
    }

    @RequestMapping("/")
    public DataSourceResult getChairs() {
        return chairDatasource.getChairFilter();
    }
}
