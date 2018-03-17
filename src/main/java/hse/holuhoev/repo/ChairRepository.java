package hse.holuhoev.repo;

import hse.holuhoev.domain.Chair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ChairRepository extends JpaRepository<Chair, Integer>, QuerydslPredicateExecutor<Chair> {
}
