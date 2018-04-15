package hse.holuhoev.datasource;

import hse.holuhoev.datasource.util.DataSourceResult;
import hse.holuhoev.domain.Institute;
import hse.holuhoev.domain.QInstitute;
import hse.holuhoev.repo.InstituteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class InstituteDatasource {
    private final InstituteRepository instituteRepository;

    @Autowired
    public InstituteDatasource(InstituteRepository instituteRepository) {
        this.instituteRepository = instituteRepository;
    }

    public DataSourceResult getInstituteFilter() {
        Sort sort = new Sort(Sort.Direction.ASC, "name");
        Iterable<Institute> institutes = instituteRepository.findAll(sort);
        return DataSourceResult.create(institutes);
    }
}
