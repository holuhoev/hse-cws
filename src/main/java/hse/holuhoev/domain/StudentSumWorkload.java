package hse.holuhoev.domain;

/**
 * Суммарная часовая загруженность студента.
 */
public class StudentSumWorkload {
    private String fio;
    private Integer workload;
    private Integer studentId;

    public StudentSumWorkload(String fio, Integer workload, Integer studentId) {
        this.fio = fio;
        this.workload = workload;
        this.studentId = studentId;
    }

    public StudentSumWorkload() {
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Integer getWorkload() {
        return workload;
    }

    public void setWorkload(Integer workload) {
        this.workload = workload;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}
