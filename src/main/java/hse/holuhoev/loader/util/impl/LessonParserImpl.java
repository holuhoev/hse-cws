package hse.holuhoev.loader.util.impl;

import hse.holuhoev.domain.*;
import hse.holuhoev.loader.util.LessonParser;
import hse.holuhoev.repo.BuildingRepository;
import hse.holuhoev.repo.PairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

@Component
public class LessonParserImpl implements LessonParser {
    private final BuildingRepository buildingRepository;
    private final PairRepository pairRepository;

    @Autowired
    public LessonParserImpl(BuildingRepository buildingRepository, PairRepository pairRepository) {
        this.buildingRepository = buildingRepository;
        this.pairRepository = pairRepository;
    }

    @Override
    public List<Lesson> parse(List<Lesson> lessons) {
        List<Lesson> list = new LinkedList<>();
        QBuilding qBuilding = QBuilding.building;
        QPair qPair = QPair.pair1;

        if (lessons != null && lessons.size() > 0) {
            lessons.forEach(lesson -> {
                CityType cityType = buildingRepository.findOne(qBuilding.name.eq(lesson.getBuilding() != null ? lesson.getBuilding() : ""))
                        .map(Building::getCity)
                        .orElse(CityType.OTHER);
                LocalTime begin = LocalTime.parse(lesson.getBeginLesson());
                LocalTime end = LocalTime.parse(lesson.getEndLesson());

                pairRepository.findOne(qPair.begin.before(begin).and(qPair.end.after(begin)).and(qPair.cityType.eq(cityType)))
                        .ifPresent(beginPair -> pairRepository.findOne(qPair.begin.before(end).and(qPair.end.after(end)).and(qPair.cityType.eq(cityType)))
                                .ifPresent(endPair -> {
                                    int hours = endPair.getPair() - beginPair.getPair() + 1;
                                    lesson.setHours(hours);
                                }));
            });
            // TODO: Добавить в Lesson день недели, так как в выходной день в Перми другое расписание. Добавить день недели в Pair
            // TODO: Test
        }
        return list;
    }
}
