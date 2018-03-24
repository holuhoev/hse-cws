package hse.holuhoev.domain;

public class LecturerSumWorkload {
    private String fio;
    private Integer workload;
    private Integer lecturerId;

    public LecturerSumWorkload(String fio, Integer workload, Integer lecturerId) {
        this.fio = fio;
        this.workload = workload;
        this.lecturerId = lecturerId;
    }

    public LecturerSumWorkload() {
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

    public Integer getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Integer lecturerId) {
        this.lecturerId = lecturerId;
    }
}
