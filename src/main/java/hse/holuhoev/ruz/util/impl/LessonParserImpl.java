package hse.holuhoev.ruz.util.impl;

import hse.holuhoev.domain.Lesson;
import hse.holuhoev.ruz.util.LessonParser;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LessonParserImpl implements LessonParser {
    @Override
    public List<Lesson> parse(List<Lesson> lessons) {
        // TODO:
        return lessons;
    }
}
