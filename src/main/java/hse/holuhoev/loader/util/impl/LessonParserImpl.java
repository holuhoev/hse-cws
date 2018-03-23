package hse.holuhoev.loader.util.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import hse.holuhoev.domain.*;
import hse.holuhoev.loader.util.LessonParser;
import hse.holuhoev.repo.BuildingRepository;
import hse.holuhoev.repo.PairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
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
     * Minutes per hour.
     */
    private static final int MINUTES_PER_PAIR = 80;

    private final BuildingRepository buildingRepository;
    private final PairRepository pairRepository;

    @Autowired
    public LessonParserImpl(BuildingRepository buildingRepository, PairRepository pairRepository) {
        this.buildingRepository = buildingRepository;
        this.pairRepository = pairRepository;
    }

    @Override
    public List<Lesson> parse(List<Lesson> lessons) {
        if (lessons == null)
            return new LinkedList<>();
        QBuilding qBuilding = QBuilding.building;
        QPair qPair = QPair.pair1;
        lessons.forEach(lesson -> {
            LocalTime begin = LocalTime.parse(lesson.getBeginLesson());
            LocalTime end = LocalTime.parse(lesson.getEndLesson());
//            DayType dayType = DayType.of(lesson.getDayOfWeek());
//            CityType cityType = buildingRepository.findOne(qBuilding.name.eq(lesson.getBuilding() != null ? lesson.getBuilding() : ""))
//                    .map(Building::getCity)
//                    .orElse(CityType.OTHER);
//
//            BooleanBuilder beginBuilder = new BooleanBuilder();
//            beginBuilder.and(qPair.cityType.eq(cityType));
//            beginBuilder.and(qPair.begin.eq(begin));
//            beginBuilder.and(qPair.dayType.eq(dayType));
//
//            BooleanBuilder endBuilder = new BooleanBuilder();
//            endBuilder.and(qPair.cityType.eq(cityType));
//            endBuilder.and(qPair.end.eq(end));
//            endBuilder.and(qPair.dayType.eq(dayType));
//
//            pairRepository.findOne(beginBuilder)
//                    .ifPresent(beginPair -> pairRepository.findOne(endBuilder)
//                            .ifPresent(endPair -> {
//                                int hours = endPair.getPair() - beginPair.getPair() + 1;
//                                lesson.setHours(hours);
//                            }));

            if (lesson.getHours() == null) {
                // If not find in pair map. Will count by hand.
                Double hours =
                        (end.getHour() * MINUTES_PER_HOUR + end.getMinute() - begin.getHour() * MINUTES_PER_HOUR - begin.getMinute()) / (double) MINUTES_PER_PAIR;
                lesson.setHours((int)Math.round(hours));
            }
        });
        // TODO: Test
        return lessons;
    }
}
