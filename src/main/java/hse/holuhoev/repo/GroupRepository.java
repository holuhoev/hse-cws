package hse.holuhoev.repo;

import hse.holuhoev.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author Evgeny Kholukhoev
 */
public interface GroupRepository extends JpaRepository<Group, Integer>, QuerydslPredicateExecutor<Group> {
}
