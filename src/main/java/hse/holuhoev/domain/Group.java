package hse.holuhoev.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Evgeny Kholukhoev
 */
@Entity
@Table(name = "GROUP")
public class Group extends RuzObject {
    @Id
    @Column(name = "ID")
    private Integer groupOid;

    @Column(name = "course")
    private Integer course;

    @Column(name = "facultyID")
    private Integer facultyOid;

    @Column(name = "name")
    private String name;

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

    public Group(Integer course, Integer facultyOid, Integer groupOid) {
        this.course = course;
        this.facultyOid = facultyOid;
        this.groupOid = groupOid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

