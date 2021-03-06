package hse.holuhoev.domain;


import hse.holuhoev.ruz.converter.Convert;
import hse.holuhoev.ruz.converter.KindOfWorkConverter;
import hse.holuhoev.ruz.util.JsonAttribute;

/**
 * @author Evgeny Kholukhoev
 */
public class Lesson extends RuzObject {
    @JsonAttribute
    private String date;

    @JsonAttribute
    private String beginLesson;

    @JsonAttribute
    private String endLesson;

    @JsonAttribute
    private String discipline;

    @JsonAttribute
    @Convert(converter = KindOfWorkConverter.class)
    private KindOfWork kindOfWork;

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

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public KindOfWork getKindOfWork() {
        return kindOfWork;
    }

    public void setKindOfWork(KindOfWork kindOfWork) {
        this.kindOfWork = kindOfWork;
    }
}
