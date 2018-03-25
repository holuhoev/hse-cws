package hse.holuhoev.datasource;

import hse.holuhoev.datasource.util.DataSourceResult;
import hse.holuhoev.domain.Chair;
import hse.holuhoev.repo.ChairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChairDatasource {
    private final ChairRepository chairRepository;

    @Autowired
    public ChairDatasource(ChairRepository chairRepository) {
        this.chairRepository = chairRepository;
    }

    public DataSourceResult getChairFilter() {
        Iterable<Chair> chairs = chairRepository.findAll();
        return DataSourceResult.create(chairs);
    }
}
