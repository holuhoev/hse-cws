package hse.holuhoev.to;

/**
 * @author Evgeny Kholukhoev
 */
public class StudentWorkload {
    private Integer workload;
    private Integer studentOid;
    private String fio;

    public Integer getWorkload() {
        return workload;
    }

    public void setWorkload(Integer workload) {
        this.workload = workload;
    }

    public Integer getStudentOid() {
        return studentOid;
    }

    public void setStudentOid(Integer studentOid) {
        this.studentOid = studentOid;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public StudentWorkload(Integer workload, Integer studentOid, String fio) {
        this.workload = workload;
        this.studentOid = studentOid;
        this.fio = fio;
    }

    public StudentWorkload() {
    }
}
