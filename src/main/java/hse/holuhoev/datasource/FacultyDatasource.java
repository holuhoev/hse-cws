package hse.holuhoev.datasource;

import com.querydsl.core.BooleanBuilder;
import hse.holuhoev.datasource.util.DataSourceResult;
import hse.holuhoev.domain.Faculty;
import hse.holuhoev.domain.QFaculty;
import hse.holuhoev.repo.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class FacultyDatasource {
    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyDatasource(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public DataSourceResult getFacultyFilter(final Integer instituteId,
                                             final String name) {
        QFaculty qFaculty = QFaculty.faculty;
        BooleanBuilder builder = new BooleanBuilder();

        if (instituteId != null) {
            builder.and(qFaculty.instituteId.eq(instituteId));
        }

        if (name != null && !name.isEmpty()) {
            builder.and(qFaculty.name.containsIgnoreCase(name));
        }

        Sort sort = new Sort(Sort.Direction.ASC, "name");
        Iterable<Faculty> faculties = facultyRepository.findAll(builder, sort);
        return DataSourceResult.create(faculties);
    }
}
