package hse.holuhoev.ruz.util.impl;

import hse.holuhoev.domain.*;
import hse.holuhoev.ruz.util.LessonParser;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

@Component
public class LessonParserImpl implements LessonParser {
    /**
     * Minutes per hour.
     */
    private static final int MINUTES_PER_HOUR = 60;

    /**
     * Minutes per pair.
     */
    private static final int MINUTES_PER_PAIR = 80;

    @Override
    public List<Lesson> parse(List<Lesson> lessons) {
        if (lessons == null)
            return new LinkedList<>();
        lessons.forEach(lesson -> {
            LocalTime begin = LocalTime.parse(lesson.getBeginLesson());
            LocalTime end = LocalTime.parse(lesson.getEndLesson());
            Double hours =
                    (end.getHour() * MINUTES_PER_HOUR + end.getMinute() - begin.getHour() * MINUTES_PER_HOUR - begin.getMinute()) / (double) MINUTES_PER_PAIR;
            lesson.setHours((int) Math.round(hours));
        });
        return lessons;
    }
}
