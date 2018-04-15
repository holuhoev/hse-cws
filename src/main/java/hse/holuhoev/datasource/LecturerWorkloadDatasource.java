package hse.holuhoev.datasource;

import com.querydsl.core.BooleanBuilder;
import hse.holuhoev.datasource.util.DataSourceResult;
import hse.holuhoev.domain.*;
import hse.holuhoev.repo.LecturerRepository;
import hse.holuhoev.repo.LecturerWorkloadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.google.common.base.Strings.isNullOrEmpty;

@Service
public class LecturerWorkloadDatasource {
    private final LecturerRepository lecturerRepository;
    private final LecturerWorkloadRepository lecturerWorkloadRepository;

    @Autowired
    public LecturerWorkloadDatasource(LecturerRepository lecturerRepository, LecturerWorkloadRepository lecturerWorkloadRepository) {
        this.lecturerRepository = lecturerRepository;
        this.lecturerWorkloadRepository = lecturerWorkloadRepository;
    }

    public DataSourceResult getLecturerWorkload(final Integer lecturerId,
                                                final LocalDate fromDate,
                                                final LocalDate toDate) {

        if (lecturerId == null) {
            return DataSourceResult.createEmpty();
        }
        Optional<Lecturer> optionalLecturer = lecturerRepository.findById(lecturerId);
        if (optionalLecturer.isPresent()) {
            Lecturer lecturer = optionalLecturer.get();
            QLecturerWorkload qLecturerWorkload = QLecturerWorkload.lecturerWorkload;
            BooleanBuilder builder = new BooleanBuilder();
            builder.and(qLecturerWorkload.lecturerId.eq(lecturer.getId()));
            if (fromDate != null) {
                builder.and(qLecturerWorkload.date.after(fromDate).or(qLecturerWorkload.date.eq(fromDate)));
            }

            if (toDate != null) {
                builder.and(qLecturerWorkload.date.before(toDate).or(qLecturerWorkload.date.before(toDate)));
            }
            Iterable<LecturerWorkload> lecturerWorkloads = lecturerWorkloadRepository.findAll(builder);
            lecturerWorkloads.forEach(lecturerWorkload -> lecturerWorkload.setLecturerFio(lecturer.getFio()));
            return DataSourceResult.create(lecturerWorkloads);
        }
        return DataSourceResult.createEmpty();
    }

    public DataSourceResult getLecturerSumWorkload(final Integer chairId,
                                                   final String fio,
                                                   final LocalDate fromDate,
                                                   final LocalDate toDate,
                                                   final Integer top,
                                                   final Integer skip,
                                                   final Boolean fetchTotal) {
        QLecturer qLecturer = QLecturer.lecturer;
        QLecturerWorkload qLecturerWorkload = QLecturerWorkload.lecturerWorkload;
        BooleanBuilder lecturerBuilder = new BooleanBuilder();
        BooleanBuilder workloadBuilder = new BooleanBuilder();

        if (chairId == null || fromDate == null || toDate == null)
            return DataSourceResult.createEmpty();

        lecturerBuilder.and(qLecturer.chairId.eq(chairId));
        workloadBuilder.and(qLecturerWorkload.date.after(fromDate));
        workloadBuilder.and(qLecturerWorkload.date.before(toDate));
        if (fio != null && !fio.isEmpty()) {
            lecturerBuilder.and(qLecturer.fio.containsIgnoreCase(fio));
        }


        Iterable<Lecturer> lecturers;
        if (top != null) {
            String orderByString = "fio";
            Pageable limit = PageRequest.of(skip, top, Sort.Direction.ASC, orderByString);
            lecturers = lecturerRepository.findAll(lecturerBuilder, limit);
        } else {
            lecturers = lecturerRepository.findAll(lecturerBuilder);
        }


        List<LecturerSumWorkload> result = StreamSupport.stream(lecturers.spliterator(), false)
                .map(lecturer -> {
                    BooleanBuilder builder = new BooleanBuilder();
                    builder.and(workloadBuilder).and(qLecturerWorkload.lecturerId.eq(lecturer.getId()));
                    Integer workload =
                            StreamSupport.stream(lecturerWorkloadRepository.findAll(builder).spliterator(), false)
                                    .mapToInt(LecturerWorkload::getWorkload)
                                    .sum();
                    return new LecturerSumWorkload(lecturer.getFio(), workload, lecturer.getId());

                }).collect(Collectors.toList());

        Map<String, Object> hints = new HashMap<>();
        hints.put("paging", true);
        if (fetchTotal != null && fetchTotal) {
            Long count = lecturerWorkloadRepository.count(lecturerBuilder);
            hints.put("total", count);
        }
        return DataSourceResult.create(result, hints);
    }
}
