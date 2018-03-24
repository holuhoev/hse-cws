package hse.holuhoev.ruz.util;

import hse.holuhoev.domain.Lesson;

import java.util.List;

public interface LessonParser {
    List<Lesson> parse(List<Lesson> lessons);
}
