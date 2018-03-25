package hse.holuhoev.datasource;

import com.querydsl.core.BooleanBuilder;
import hse.holuhoev.datasource.util.DataSourceResult;
import hse.holuhoev.domain.Course;
import hse.holuhoev.domain.EducationType;
import hse.holuhoev.domain.Group;
import hse.holuhoev.domain.QGroup;
import hse.holuhoev.repo.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class GroupDatasource {
    private final GroupRepository groupRepository;

    @Autowired
    public GroupDatasource(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public DataSourceResult getGroupFilter(final Integer facultyId,
                                           final Integer instituteId,
                                           final Course course,
                                           final String number,
                                           final EducationType educationType) {
        QGroup qGroup = QGroup.group;

        BooleanBuilder builder = new BooleanBuilder();
        if (facultyId != null) {
            builder.and(qGroup.facultyId.eq(facultyId));
        }

        if (instituteId != null) {
            builder.and(qGroup.instituteId.eq(instituteId));
        }

        if (course != null) {
            builder.and(qGroup.course.eq(course));
        }

        if (number != null) {
            builder.and(qGroup.number.containsIgnoreCase(number));
        }

        if (educationType != null) {
            builder.and(qGroup.educationType.eq(educationType));
        }

        Sort sort = new Sort(Sort.Direction.ASC, "course", "number");
        Iterable<Group> groups = groupRepository.findAll(builder, sort);
        return DataSourceResult.create(groups, new HashMap<>());

    }
}
