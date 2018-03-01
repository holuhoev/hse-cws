package hse.holuhoev.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Студент.
 *
 * @author Evgeny Kholukhoev
 */
@Data
@Entity
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    /**
     * Часовая загруженность студента за неделю.
     */
    private Integer workload;

    public Student() {
    }

    public Student(String firstName, String lastName, Integer workload) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.workload = workload;
    }
}
