package hse.holuhoev.domain;

/**
 * @author Evgeny Kholukhoev
 */
public class Lesson extends RuzObject {
    private String date;
    private String beginLesson;
    private String endLesson;
    private String building;

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

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }
}
