package hse.holuhoev.domain;

import javax.persistence.*;

/**
 * @author Evgeny Kholukhoev
 */
@Entity
@Table(name = "FACULTY")
public class Faculty {
    @Id
    @Column(name = "ID")
    private Integer facultyOid;

    @Column(name = "instituteID")
    private Integer instituteId; // факультет

    @Column(name = "name")
    private String name;  // сама программа образовательная

    @Transient
    private String institute;

    public Faculty() {
    }

    public Integer getFacultyOid() {
        return facultyOid;
    }

    public void setFacultyOid(Integer facultyOid) {
        this.facultyOid = facultyOid;
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
