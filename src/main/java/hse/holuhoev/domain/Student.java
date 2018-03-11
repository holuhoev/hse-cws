package hse.holuhoev.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Evgeny Kholukhoev
 */
@Entity
@Table(name = "STUDENT")
public class Student {
    @Id
    @Column(name = "ID")
    private Integer studentOid;

    @Column(name = "fio")
    private String fio;

    @Column(name = "facultyID")
    private Integer facultyID;

    @Column(name = "groupID")
    private Integer groupID;

    @Column(name = "instituteID")
    private Integer instituteID;

    public Integer getFacultyID() {
        return facultyID;
    }

    public void setFacultyID(Integer facultyID) {
        this.facultyID = facultyID;
    }

    public Integer getGroupID() {
        return groupID;
    }

    public void setGroupID(Integer groupID) {
        this.groupID = groupID;
    }

    public Integer getInstituteID() {
        return instituteID;
    }

    public void setInstituteID(Integer instituteID) {
        this.instituteID = instituteID;
    }

    public Student() {
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Integer getStudentOid() {
        return studentOid;
    }

    public void setStudentOid(Integer studentOid) {
        this.studentOid = studentOid;
    }
}
