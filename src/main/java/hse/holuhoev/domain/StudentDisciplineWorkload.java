package hse.holuhoev.domain;

/**
 * Класс представляющий часовую загруженность по дисциплинам НИУ ВШЭ.
 */
public class StudentDisciplineWorkload {
    private String name;
    private Integer lecture;
    private Integer seminar;
    private Integer science;
    private Integer exam;
    private Integer workShow;

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
}
