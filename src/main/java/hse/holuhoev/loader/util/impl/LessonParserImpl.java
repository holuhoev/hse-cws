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
                // E.g. Moscow 1 9:00 10:30
                // 1. find where beginLesson = lesson.beginLesson(заменить мб на минимальную разницу по модулю), endPairNumber = startPairNumber = ... ;
                // 2. while (lesson.endLesson <= endLesson)  ++endPairNumber(или же создавать еще один Lesson;
                // понять, хранить начало и конец пары в Lesson, или рассматривать составной урок как разные пары?
            });
            // TODO: Добавить в Lesson день недели, так как в выходной день в Перми другое расписание. Добавить день недели в Pair
        }
        return list;
    }
}
