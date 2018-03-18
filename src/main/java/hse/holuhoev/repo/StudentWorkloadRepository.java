package hse.holuhoev.repo;

import hse.holuhoev.domain.StudentWorkload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface StudentWorkloadRepository extends JpaRepository<StudentWorkload, Integer>, QuerydslPredicateExecutor<StudentWorkload> {
}
