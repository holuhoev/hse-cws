package hse.holuhoev.datasource;

import hse.holuhoev.datasource.util.DataSourceResult;
import hse.holuhoev.domain.Institute;
import hse.holuhoev.repo.InstituteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstituteDatasource {
    private final InstituteRepository instituteRepository;

    @Autowired
    public InstituteDatasource(InstituteRepository instituteRepository) {
        this.instituteRepository = instituteRepository;
    }

    public DataSourceResult getInstituteFilter() {
        Iterable<Institute> institutes = instituteRepository.findAll();
        return DataSourceResult.create(institutes);
    }
}
