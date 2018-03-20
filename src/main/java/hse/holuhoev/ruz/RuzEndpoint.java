package hse.holuhoev.ruz;

public enum RuzEndpoint {
    GROUPS("groups"),
    LESSONS("personLessons"),
    LECTURERS("lecturers"),
    FACULTIES("faculties"),
    STAFF_OF_GROUP("StaffOfGroup"),
    CHAIRS("chairs"),
    BUILDINGS("buildings");

    private final String value;

    RuzEndpoint(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
