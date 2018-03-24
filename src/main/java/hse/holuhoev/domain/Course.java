package hse.holuhoev.domain;

public enum Course {
    FISRT, SECOND, THIRD, FOURTH, FIFTH;

    public static Course of(int course) {
        if (course < 1 || course > 5) {
            throw new IllegalArgumentException("Invalid value for Course:" + course);
        }
        return Course.class.getEnumConstants()[course - 1];
    }
}
