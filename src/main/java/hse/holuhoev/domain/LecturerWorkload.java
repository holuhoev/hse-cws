package hse.holuhoev.domain;

import hse.holuhoev.jpa.converter.LocalDateConverter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Evgeny Kholukhoev
 */
@Entity
@Table(name = "LECTWORKLOAD")
public class LecturerWorkload {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "workload", nullable = false)
    private Integer workload;

    @Column(name = "lecturer_id",nullable = false)
    private Integer lecturerId;

    @Column(name = "day", nullable = false)
    @Convert(converter = LocalDateConverter.class)
    private LocalDate date;

    @Transient
    private String lecturerFio;

    public LecturerWorkload() {
    }

    public Integer getWorkload() {
        return workload;
    }

    public void setWorkload(Integer workload) {
        this.workload = workload;
    }

    public Integer getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Integer lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getLecturerFio() {
        return lecturerFio;
    }

    public void setLecturerFio(String fio) {
        this.lecturerFio = fio;
    }

    public LecturerWorkload(Integer workload, Integer lecturerOid, String fio) {
        this.workload = workload;
        this.lecturerId = lecturerOid;
        this.lecturerFio = fio;
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
