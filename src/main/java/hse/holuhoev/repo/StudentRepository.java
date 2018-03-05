package hse.holuhoev.repo;

import hse.holuhoev.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author Evgeny Kholukhoev
 */
public interface StudentRepository extends JpaRepository<Student, Long>, QuerydslPredicateExecutor<Student> {
}
