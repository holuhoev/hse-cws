package hse.holuhoev.to;

/**
 * @author Evgeny Kholukhoev
 */
public class LecturerWorkload {
    private Integer workload;
    private Integer lecturerId;
    private String fio;

    public LecturerWorkload() {
    }

    public Integer getWorkload() {
        return workload;
    }

    public void setWorkload(Integer workload) {
        this.workload = workload;
    }

    public Integer getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Integer lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public LecturerWorkload(Integer workload, Integer lecturerOid, String fio) {
        this.workload = workload;
        this.lecturerId = lecturerOid;
        this.fio = fio;
    }
}
