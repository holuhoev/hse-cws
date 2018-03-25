package hse.holuhoev.datasource;

import com.querydsl.core.BooleanBuilder;
import hse.holuhoev.datasource.util.DataSourceResult;
import hse.holuhoev.domain.Course;
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
                                           final Course course) {
        QGroup qGroup = QGroup.group;

        BooleanBuilder builder = new BooleanBuilder();
        if (facultyId != null) {
            builder.and(qGroup.facultyOid.eq(facultyId));
        }

        if (instituteId != null) {
            builder.and(qGroup.instituteId.eq(instituteId));
        }

        if (course != null) {
            builder.and(qGroup.course.eq(course));
        }

        Sort sort = new Sort(Sort.Direction.ASC, "course", "number");
        Iterable<Group> groups = groupRepository.findAll(builder, sort);
        return DataSourceResult.create(groups, new HashMap<>());

    }
}
