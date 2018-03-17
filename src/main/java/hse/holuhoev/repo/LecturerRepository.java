package hse.holuhoev.repo;

import hse.holuhoev.domain.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface LecturerRepository extends JpaRepository<Lecturer, Integer>, QuerydslPredicateExecutor<Lecturer> {
}
