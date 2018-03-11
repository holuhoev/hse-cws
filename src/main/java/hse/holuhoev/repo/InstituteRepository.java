package hse.holuhoev.repo;

import hse.holuhoev.domain.Institute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author Evgeny Kholukhoev
 */
public interface InstituteRepository extends JpaRepository<Institute, Integer>, QuerydslPredicateExecutor<Institute> {
}
