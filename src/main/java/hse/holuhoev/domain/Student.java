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

    @Column(name = "faculty_id")
    private Integer facultyID;

    @Column(name = "group_id")
    private Integer groupID;

    @Column(name = "institute_id")
    private Integer instituteID;

    @Column(name = "course")
    private Course course;

    @Column(name = "education_type")
    private EducationType educationType;

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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public EducationType getEducationType() {
        return educationType;
    }

    public void setEducationType(EducationType educationType) {
        this.educationType = educationType;
    }
}
