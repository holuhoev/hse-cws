package hse.holuhoev.domain;

import hse.holuhoev.jpa.converter.LocalDateConverter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Дневная часовая загруженность студента.
 *
 * @author Evgeny Kholukhoev
 */
@Entity
@Table(name = "STUDWORKLOAD")
public class StudentWorkload {
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "workload", nullable = false)
    private Integer workload;

    @Column(name = "student_id", nullable = false)
    private Integer studentId;

    @Column(name = "day", nullable = false)
    @Convert(converter = LocalDateConverter.class)
    private LocalDate date;

    @Transient
    private String studentFio;

    public Integer getWorkload() {
        return workload;
    }

    public void setWorkload(Integer workload) {
        this.workload = workload;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentFio() {
        return studentFio;
    }

    public void setStudentFio(String studentFio) {
        this.studentFio = studentFio;
    }

    public StudentWorkload(Integer workload, Integer studentId, String studentFio) {
        this.workload = workload;
        this.studentId = studentId;
        this.studentFio = studentFio;
    }

    public StudentWorkload() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
