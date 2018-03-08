package hse.holuhoev.domain;

/**
 * Преподаватель.
 *
 * @author Evgeny Kholukhoev
 */
public class RuzLecturer extends RuzObject {
    private Integer chairOid; // департамент
    private String fio;
    private String shortFio;
    private Integer lecturerOid;

    public Integer getLecturerOid() {
        return lecturerOid;
    }

    public void setLecturerOid(Integer lecturerOid) {
        this.lecturerOid = lecturerOid;
    }

    public RuzLecturer() {
    }

    public Integer getChairOid() {
        return chairOid;
    }

    public void setChairOid(Integer chairOid) {
        this.chairOid = chairOid;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getShortFio() {
        return shortFio;
    }

    public void setShortFio(String shortFio) {
        this.shortFio = shortFio;
    }
}
