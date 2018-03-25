package hse.holuhoev.domain;

import hse.holuhoev.ruz.util.JsonAttribute;

import javax.persistence.*;

/**
 * Образовательная программа.
 *
 * @author Evgeny Kholukhoev
 */
@Entity
@Table(name = "FACULTY")
public class Faculty {
    @Id
    @JsonAttribute(name = "facultyOid")
    @Column(name = "ID")
    private Integer Id;

    @Column(name = "institute_id")
    private Integer instituteId;

    @JsonAttribute
    @Column(name = "faculty_name")
    private String name;

    @Transient
    @JsonAttribute
    private String institute;

    public Faculty() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        this.Id = id;
    }

    public Integer getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(Integer instituteId) {
        this.instituteId = instituteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }
}
