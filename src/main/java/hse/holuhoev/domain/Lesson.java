package hse.holuhoev.domain;

/**
 * @author Evgeny Kholukhoev
 */
public class Lesson extends RuzObject {
    private String date;

    public Lesson() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
