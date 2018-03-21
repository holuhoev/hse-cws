package hse.holuhoev.loader.util.impl;

import hse.holuhoev.domain.Building;
import hse.holuhoev.domain.CityType;
import hse.holuhoev.domain.Lesson;
import hse.holuhoev.domain.QBuilding;
import hse.holuhoev.loader.util.LessonParser;
import hse.holuhoev.repo.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
                CityType cityType = buildingRepository.findOne(qBuilding.name.eq(lesson.getBuilding() != null ? lesson.getBuilding() : ""))
                        .map(Building::getCity)
                        .orElse(CityType.OTHER);
                // NOTE: Так как везде по 8 пар, можно завести таблицу в бд: ID, CityType, Номер пары, начало, конец
            });
        }
        return list;
    }
}
