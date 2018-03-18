package hse.holuhoev.repo;

import hse.holuhoev.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import javax.transaction.Transactional;
import java.util.stream.Stream;

/**
 * @author Evgeny Kholukhoev
 */
public interface StudentRepository extends JpaRepository<Student, Integer>, QuerydslPredicateExecutor<Student> {
}
