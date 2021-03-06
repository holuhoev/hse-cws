package hse.holuhoev.domain;

import java.time.DayOfWeek;

public enum DayType {
    WEEKEND, WORKING_DAY;

    public static DayType of(Integer dayOfWeek) {
        if (dayOfWeek < 1 || dayOfWeek > 7) {
            dayOfWeek = 1;
        }
        DayOfWeek day = DayOfWeek.of(dayOfWeek);
        if (day == DayOfWeek.SATURDAY) {
            return WEEKEND;
        }
        return WORKING_DAY;
    }
}
