package hse.holuhoev.domain;

/**
 * @author Evgeny Kholukhoev
 */
public class RuzFaculty {
    private Integer facultyOid;
    private String institute; // факультет
    private String name;  // сама программа образовательная

    public RuzFaculty() {
    }

    public Integer getFacultyOid() {
        return facultyOid;
    }

    public void setFacultyOid(Integer facultyOid) {
        this.facultyOid = facultyOid;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
