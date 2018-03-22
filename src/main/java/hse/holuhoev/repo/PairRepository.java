package hse.holuhoev.repo;

import hse.holuhoev.domain.Pair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PairRepository extends JpaRepository<Pair, Integer>, QuerydslPredicateExecutor<Pair> {
}
