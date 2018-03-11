package hse.holuhoev.repo;

import hse.holuhoev.domain.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author Evgeny Kholukhoev
 */
public interface FacultyRepository extends JpaRepository<Faculty, Integer>, QuerydslPredicateExecutor<Faculty> {
}
