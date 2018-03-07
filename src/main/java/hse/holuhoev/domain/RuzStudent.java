package hse.holuhoev.domain;

/**
 * @author Evgeny Kholukhoev
 */
public class RuzStudent {
    private String fio;
    private String shortFIO;
    private Integer studentOid;

    public RuzStudent() {
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getShortFIO() {
        return shortFIO;
    }

    public void setShortFIO(String shortFIO) {
        this.shortFIO = shortFIO;
    }

    public Integer getStudentOid() {
        return studentOid;
    }

    public void setStudentOid(Integer studentOid) {
        this.studentOid = studentOid;
    }
}
