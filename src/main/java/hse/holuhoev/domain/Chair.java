package hse.holuhoev.domain;

/**
 * Департамент
 *
 * @author Evgeny Kholukhoev
 */
public class Chair extends RuzObject {
    private Integer facultyOid; // ID программы
    private String name;
    private String chairOid;
    private String code;

    public Chair() {
    }

    public Integer getFacultyOid() {
        return facultyOid;
    }

    public void setFacultyOid(Integer facultyOid) {
        this.facultyOid = facultyOid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChairOid() {
        return chairOid;
    }

    public void setChairOid(String chairOid) {
        this.chairOid = chairOid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
