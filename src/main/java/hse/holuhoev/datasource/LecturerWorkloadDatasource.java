package hse.holuhoev.datasource;

import com.querydsl.core.BooleanBuilder;
import hse.holuhoev.datasource.util.DataSourceResult;
import hse.holuhoev.domain.*;
import hse.holuhoev.repo.LecturerRepository;
import hse.holuhoev.repo.LecturerWorkloadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LecturerWorkloadDatasource {
    private final LecturerRepository lecturerRepository;
    private final LecturerWorkloadRepository lecturerWorkloadRepository;

    @Autowired
    public LecturerWorkloadDatasource(LecturerRepository lecturerRepository, LecturerWorkloadRepository lecturerWorkloadRepository) {
        this.lecturerRepository = lecturerRepository;
        this.lecturerWorkloadRepository = lecturerWorkloadRepository;
    }

    public DataSourceResult getLecturerSumWorkload(final Integer chairId,
                                                   final LocalDate fromDate,
                                                   final LocalDate toDate) {
        QLecturer qLecturer = QLecturer.lecturer;
        QLecturerWorkload qLecturerWorkload = QLecturerWorkload.lecturerWorkload;
        BooleanBuilder lecturerBuilder = new BooleanBuilder();
        BooleanBuilder workloadBuilder = new BooleanBuilder();

        if (chairId != null) {
            lecturerBuilder.and(qLecturer.chairOid.eq(chairId));
        }

        if (fromDate != null) {
            workloadBuilder.and(qLecturerWorkload.date.after(fromDate));
        }

        if (toDate != null) {
            workloadBuilder.and(qLecturerWorkload.date.before(toDate));
        }

        Iterable<Lecturer> lecturers;
        lecturers = lecturerRepository.findAll(lecturerBuilder);

        List<LecturerSumWorkload> result = StreamSupport.stream(lecturers.spliterator(), false)
                .map(lecturer -> {
                    BooleanBuilder builder = new BooleanBuilder();
                    builder.and(workloadBuilder).and(qLecturerWorkload.lecturerId.eq(lecturer.getLecturerOid()));
                    Integer workload =
                            StreamSupport.stream(lecturerWorkloadRepository.findAll(builder).spliterator(), false)
                                    .mapToInt(LecturerWorkload::getWorkload)
                                    .sum();
                    return new LecturerSumWorkload(lecturer.getFio(), workload, lecturer.getLecturerOid());

                }).collect(Collectors.toList());

        return DataSourceResult.create(result, new HashMap<>());
    }
}
