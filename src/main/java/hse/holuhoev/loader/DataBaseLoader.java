package hse.holuhoev.loader;

import hse.holuhoev.domain.QStudent;
import hse.holuhoev.domain.Student;
import hse.holuhoev.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Evgeny Kholukhoev
 */
@Component
public class DataBaseLoader implements CommandLineRunner {
    private final StudentRepository studentRepository;

    @Autowired
    public DataBaseLoader(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        QStudent qStudent = QStudent.student;
        studentRepository.save(new Student("Emil", "Fataliev", 40));
        studentRepository.save(new Student("Emil1", "Fataliev1", 42));
        studentRepository.save(new Student("Emil2", "Fataliev2", 45));
        studentRepository.save(new Student("Emil3", "Fataliev3", 46));
        studentRepository.save(new Student("Emil4", "Fataliev4", 49));
    }
}
