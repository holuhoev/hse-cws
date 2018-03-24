package hse.holuhoev.domain;

import javax.persistence.*;

/**
 * Группа студентов.
 *
 * @author Evgeny Kholukhoev
 */
@Entity
@Table(name = "STUDGROUP")
public class Group extends RuzObject {
    @Id
    @Column(name = "ID")
    private Integer groupOid;

    /**
     * Курс из RUZ API.
     */
    @Transient
    private Integer course;

    @Column(name = "course")
    private Course courseType;

    @Column(name = "institute_id")
    private Integer instituteId;

    @Column(name = "faculty_id")
    private Integer facultyOid;

    @Column(name = "group_number")
    private String number;

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public Integer getFacultyOid() {
        return facultyOid;
    }

    public void setFacultyOid(Integer facultyOid) {
        this.facultyOid = facultyOid;
    }

    public Integer getGroupOid() {
        return groupOid;
    }

    public void setGroupOid(Integer groupOid) {
        this.groupOid = groupOid;
    }

    public Group() {

    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(Integer instituteId) {
        this.instituteId = instituteId;
    }

    public Course getCourseType() {
        return courseType;
    }

    public void setCourseType(Course courseType) {
        this.courseType = courseType;
    }
}

