package hse.holuhoev.ruz.api.impl;


import hse.holuhoev.domain.*;
import hse.holuhoev.ruz.RuzEndpoint;
import hse.holuhoev.ruz.RuzParam;
import hse.holuhoev.ruz.RuzURL;
import hse.holuhoev.ruz.api.RuzApiService;
import hse.holuhoev.loader.util.LessonParser;
import hse.holuhoev.ruz.util.RuzJsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Evgeny Kholukhoev
 */
@Service
public class RuzApiServiceImpl implements RuzApiService {
    private final RuzJsonParser ruzJsonParser;
    private final LessonParser lessonParser;

    @Override
    public List<Building> getAllBuildings() {
        return ruzJsonParser.parse(readRuz(RuzEndpoint.BUILDINGS, null), Building.class);
    }

    @Autowired
    public RuzApiServiceImpl(LessonParser lessonParser) {
        this.lessonParser = lessonParser;
        this.ruzJsonParser = RuzJsonParser.getInstance();
    }

    @Override
    public List<Lecturer> getLecturers(Integer chairId) {
        Map<RuzParam, Object> params = new HashMap<>();
        params.put(RuzParam.CHAIR_ID, chairId);
        String lecturersInString = readRuz(RuzEndpoint.LECTURERS, params);
        return ruzJsonParser.parse(lecturersInString, Lecturer.class);
    }

    @Override
    public List<Lecturer> getAllLecturers() {
        return ruzJsonParser.parse(readRuz(RuzEndpoint.LECTURERS, null), Lecturer.class);
    }

    @Override
    public List<Chair> getAllChairs() {
        return ruzJsonParser.parse(readRuz(RuzEndpoint.CHAIRS, null), Chair.class);
    }

    @Override
    public List<Faculty> getAllFaculties() {
        return ruzJsonParser.parse(readRuz(RuzEndpoint.FACULTIES, null), Faculty.class);
    }

    @Override
    public List<Lesson> getStudentLessons(Integer studentId, LocalDate fromDate, LocalDate toDate) {
        Map<RuzParam, Object> params = new HashMap<>();
        params.put(RuzParam.STUDENT_ID, studentId);
        params.put(RuzParam.FROM_DATE, fromDate.format(formatter));
        params.put(RuzParam.TO_DATE, toDate.format(formatter));
        return getLessons(params);
    }

    @Override
    public List<Lesson> getLecturerLessons(Integer lecturerId, LocalDate fromDate, LocalDate toDate) {
        Map<RuzParam, Object> params = new HashMap<>();
        params.put(RuzParam.LECTURER_ID, lecturerId);
        params.put(RuzParam.LESSON_TYPE, RuzURL.LECTURER_LESSON_TYPE);
        params.put(RuzParam.FROM_DATE, fromDate.format(formatter));
        params.put(RuzParam.TO_DATE, toDate.format(formatter));
        return getLessons(params);
    }

    @Override
    public List<Student> getStudents(final Integer groupId) {
        Map<RuzParam, Object> params = new HashMap<>();
        params.put(RuzParam.GROUP_ID, groupId);
        String studentsInString = readRuz(RuzEndpoint.STAFF_OF_GROUP, params);
        return ruzJsonParser.parse(studentsInString, Student.class);
    }

    @Override
    public List<Group> getGroups() {
        return ruzJsonParser.parse(readRuz(RuzEndpoint.GROUPS, null), Group.class);

    }

    private List<Lesson> getLessons(Map<RuzParam, ?> params) {
        return ruzJsonParser.parse(readRuz(RuzEndpoint.LESSONS, params), Lesson.class);
    }

    private String paramsToString(Map<RuzParam, ?> params) {
        StringBuilder parameters = new StringBuilder();
        if (params != null) {
            parameters.append("?");
            for (RuzParam key : params.keySet()) {
                parameters.append(key)
                        .append("=")
                        .append(params.get(key))
                        .append("&");
            }
        }
        return parameters.toString();
    }

    private String readRuz(RuzEndpoint endpoint, Map<RuzParam, ?> params) {
        try {
            URL url = new URL(RuzURL.URL.concat(endpoint.toString()).concat(paramsToString(params)));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            return content.toString();
        } catch (IOException e) {
            return null;
        }
    }
}
