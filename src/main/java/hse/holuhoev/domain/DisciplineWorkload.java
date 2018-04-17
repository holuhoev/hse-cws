package hse.holuhoev.domain;

/**
 * Класс представляющий часовую загруженность по дисциплинам НИУ ВШЭ.
 */
public class DisciplineWorkload {
    private String name;
    private Integer lecture;
    private Integer seminar;
    private Integer science;
    private Integer practice;
    private Integer exam;
    private Integer test;
    private Integer workShow;
    private Integer other;
    private Integer consultation;
    private Integer total;

    public DisciplineWorkload() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLecture() {
        return lecture;
    }

    public void setLecture(Integer lecture) {
        this.lecture = lecture;
    }

    public Integer getSeminar() {
        return seminar;
    }

    public void setSeminar(Integer seminar) {
        this.seminar = seminar;
    }

    public Integer getScience() {
        return science;
    }

    public void setScience(Integer science) {
        this.science = science;
    }

    public Integer getExam() {
        return exam;
    }

    public void setExam(Integer exam) {
        this.exam = exam;
    }

    public Integer getWorkShow() {
        return workShow;
    }

    public void setWorkShow(Integer workShow) {
        this.workShow = workShow;
    }

    public Integer getPractice() {
        return practice;
    }

    public void setPractice(Integer practice) {
        this.practice = practice;
    }

    public Integer getOther() {
        return other;
    }

    public void setOther(Integer other) {
        this.other = other;
    }

    public Integer getConsultation() {
        return consultation;
    }

    public void setConsultation(Integer consultation) {
        this.consultation = consultation;
    }

    public Integer getTest() {
        return test;
    }

    public void setTest(Integer test) {
        this.test = test;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
