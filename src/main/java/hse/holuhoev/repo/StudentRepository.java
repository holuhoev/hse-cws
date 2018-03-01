package hse.holuhoev.repo;

import hse.holuhoev.domain.Student;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Evgeny Kholukhoev
 */
public interface StudentRepository extends CrudRepository<Student, Long> {
}
