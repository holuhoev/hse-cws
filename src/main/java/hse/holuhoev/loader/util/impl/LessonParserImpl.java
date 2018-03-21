package hse.holuhoev.loader.util.impl;

import hse.holuhoev.domain.Building;
import hse.holuhoev.domain.Lesson;
import hse.holuhoev.domain.QBuilding;
import hse.holuhoev.loader.util.LessonParser;
import hse.holuhoev.repo.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class LessonParserImpl implements LessonParser {

    private final BuildingRepository buildingRepository;

    @Autowired
    public LessonParserImpl(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    @Override
    public List<Lesson> parse(List<Lesson> lessons) {
        List<Lesson> list = new LinkedList<>();
        QBuilding qBuilding = QBuilding.building;

        if (lessons != null && lessons.size() > 0) {
            lessons.forEach(lesson -> {
                buildingRepository.findOne(qBuilding.name.eq(lesson.getBuilding()))
                        .ifPresent(building -> {
                            // TODO: 1 Взять время занятий согласно городу
                            // TODO: 2 Распарсить через распределение по времени
                        });
            });
        }
        return list;
    }
}
