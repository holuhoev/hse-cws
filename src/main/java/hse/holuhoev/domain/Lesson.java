package hse.holuhoev.domain;


/**
 * @author Evgeny Kholukhoev
 */
public class Lesson extends RuzObject {
    private String date;
    private String beginLesson;
    private String endLesson;
    private Integer hours;

    public Lesson(String date, String beginLesson, String endLesson) {
        this.date = date;
        this.beginLesson = beginLesson;
        this.endLesson = endLesson;
    }

    public String getBeginLesson() {
        return beginLesson;
    }

    public void setBeginLesson(String beginLesson) {
        this.beginLesson = beginLesson;
    }

    public String getEndLesson() {
        return endLesson;
    }

    public void setEndLesson(String endLesson) {
        this.endLesson = endLesson;
    }

    public Lesson() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

}
