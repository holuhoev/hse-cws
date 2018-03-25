package hse.holuhoev.ruz;

public enum Param {
    GROUP_ID("groupOid"),
    STUDENT_ID("studentOid"),
    LECTURER_ID("lecturerOid"),
    FROM_DATE("fromDate"),
    TO_DATE("toDate"),
    LESSON_TYPE("receiverType"),
    CHAIR_ID("chairOid");

    private final String value;

    Param(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
