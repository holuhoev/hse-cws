package hse.holuhoev.ruz.util.impl;

import hse.holuhoev.domain.Lesson;
import hse.holuhoev.ruz.util.LessonParser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LessonParserImplTest {

    @Autowired
    private LessonParser parser;

    @Test
    public void parseTest() {
        List<Lesson> list = new LinkedList<>();
        Lesson lesson1 = new Lesson("2018.02.20", null, "9:00");
        Lesson lesson2 = new Lesson("2018.02.20", "9:00", null);
        Lesson lesson3 = new Lesson(null, "8:00", "9:00");
        list.add(lesson1);
        list.add(lesson2);
        list.add(lesson3);
        Assert.assertTrue(parser.parse(list).size() == 0);

        Lesson lesson4 = new Lesson("2019.02.02", "", "9:00");
        Lesson lesson5 = new Lesson("2019.02.02", "10:00", "12:00");
        Lesson lesson6 = new Lesson("2019.02.02", "8:00", "");
        Lesson lesson7 = new Lesson("2019.02.02", "12:10", "16:00");
        list.add(lesson4);
        list.add(lesson5);
        list.add(lesson6);
        list.add(lesson7);
        List<Lesson> result = parser.parse(list);
        Assert.assertTrue(result == list);
        Assert.assertTrue(result.size() == 2);
        Assert.assertTrue(result.get(0) == lesson5);
        Assert.assertTrue(result.get(1) == lesson7);
        Assert.assertTrue(result.get(0).getHours() == 3);
        Assert.assertTrue(result.get(1).getHours() == 6);
    }
}