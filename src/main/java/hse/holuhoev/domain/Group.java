package hse.holuhoev.domain;

import hse.holuhoev.ruz.converter.Convert;
import hse.holuhoev.ruz.converter.CourseConverter;

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

    @Column(name = "course")
    @Convert(converter = CourseConverter.class)
    private Course course;

    @Column(name = "institute_id")
    private Integer instituteId;

    @Column(name = "faculty_id")
    private Integer facultyOid;

    @Column(name = "group_number")
    private String number;


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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}

