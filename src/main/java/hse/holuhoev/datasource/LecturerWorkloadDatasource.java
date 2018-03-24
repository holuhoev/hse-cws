package hse.holuhoev.datasource;

import hse.holuhoev.domain.LecturerWorkload;
import hse.holuhoev.repo.LecturerRepository;
import hse.holuhoev.repo.LecturerWorkloadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LecturerWorkloadDatasource {
    private final LecturerRepository lecturerRepository;
    private final LecturerWorkloadRepository lecturerWorkloadRepository;

    @Autowired
    public LecturerWorkloadDatasource(LecturerRepository lecturerRepository, LecturerWorkloadRepository lecturerWorkloadRepository) {
        this.lecturerRepository = lecturerRepository;
        this.lecturerWorkloadRepository = lecturerWorkloadRepository;
    }

    public List<LecturerWorkload> getLecturerWorkload(final Integer chairId,
                                                      final String fromDate,
                                                      final String toDate) {
        return null;
    }

}
