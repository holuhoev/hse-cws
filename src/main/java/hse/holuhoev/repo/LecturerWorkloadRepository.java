package hse.holuhoev.repo;

import hse.holuhoev.domain.LecturerWorkload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface LecturerWorkloadRepository extends JpaRepository<LecturerWorkload,Integer>, QuerydslPredicateExecutor<LecturerWorkload> {
}
