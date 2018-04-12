package hse.holuhoev.datasource;

import com.google.common.base.Strings;
import com.querydsl.core.BooleanBuilder;
import hse.holuhoev.datasource.util.DataSourceResult;
import hse.holuhoev.domain.Lecturer;
import hse.holuhoev.domain.QLecturer;
import hse.holuhoev.repo.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class LecturerDatasource {
    private final LecturerRepository lecturerRepository;

    @Autowired
    public LecturerDatasource(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    public DataSourceResult getLecturerFilter(final Integer chairId,
                                              final Integer lecturerId,
                                              final String lecturerFio) {
        QLecturer qLecturer = QLecturer.lecturer;
        BooleanBuilder builder = new BooleanBuilder();

        if (chairId != null) {
            builder.and(qLecturer.chairId.eq(chairId));
        }

        if (lecturerId != null) {
            builder.and(qLecturer.Id.eq(lecturerId));
        }

        if (!Strings.isNullOrEmpty(lecturerFio)) {
            builder.and(qLecturer.fio.containsIgnoreCase(lecturerFio.trim()));
        }

        String orderByString = "fio";
        Pageable limit = PageRequest.of(0, 300, Sort.Direction.ASC, orderByString);
        Iterable<Lecturer> result = lecturerRepository.findAll(builder, limit);

        return DataSourceResult.create(result);
    }
}
