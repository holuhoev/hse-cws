package hse.holuhoev.domain;

/**
 * @author Evgeny Kholukhoev
 */
public class RuzGroup extends RuzObject {
    private Integer chairOid;
    private Integer course;
    private String faculty;
    private Integer facultyOid;
    private Integer groupOid;

    public Integer getChairOid() {
        return chairOid;
    }

    public void setChairOid(Integer chairOid) {
        this.chairOid = chairOid;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
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

    public RuzGroup() {

    }

    public RuzGroup(Integer chairOid, Integer course, String faculty, Integer facultyOid, Integer groupOid) {
        this.chairOid = chairOid;
        this.course = course;
        this.faculty = faculty;
        this.facultyOid = facultyOid;
        this.groupOid = groupOid;
    }
}

