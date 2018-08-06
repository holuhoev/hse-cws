package hse.holuhoev.domain;

public enum Course {
    FIRST, SECOND, THIRD, FOURTH, FIFTH, SIX;

    public static Course of(int course) {
        if (course < 1 || course > 6) {
            throw new IllegalArgumentException("Invalid value for Course:" + course);
        }
        return Course.class.getEnumConstants()[course - 1];
    }
}
