package hse.holuhoev.ruz.api.impl;


import hse.holuhoev.domain.*;
import hse.holuhoev.ruz.RuzURL;
import hse.holuhoev.ruz.api.RuzApiService;
import hse.holuhoev.ruz.util.RuzJsonParser;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Evgeny Kholukhoev
 */
@Service
public class RuzApiServiceImpl implements RuzApiService {
    private final RuzJsonParser ruzJsonParser;

    public RuzApiServiceImpl() {
        this.ruzJsonParser = RuzJsonParser.getInstance();
    }

    @Override
    public List<Lecturer> getLecturers(Integer chairId) {
        Map<String, Object> params = new HashMap<>();
        params.put(RuzURL.CHAIR_ID, chairId);
        String lecturersInString = readRuz(RuzURL.LECTURERS_ENDPOINT, params);
        return ruzJsonParser.parse(lecturersInString, Lecturer.class);
    }

    @Override
    public List<Lecturer> getAllLecturers() {
        String lecturersInString = readRuz(RuzURL.LECTURERS_ENDPOINT, null);
        return ruzJsonParser.parse(lecturersInString, Lecturer.class);
    }

    @Override
    public List<Faculty> getAllFaculties() {
        String facultiesInString = readRuz(RuzURL.FACULTIES_ENDPOING, null);
        return ruzJsonParser.parse(facultiesInString, Faculty.class);
    }

    @Override
    public List<Lesson> getStudentLessons(Integer studentId, String fromDate, String toDate) {
        Map<String, Object> params = new HashMap<>();
        params.put(RuzURL.STUDENT_ID, studentId);
        params.put(RuzURL.FROM_DATE, fromDate);
        params.put(RuzURL.TO_DATE, toDate);
        return getLessons(params);
    }

    @Override
    public List<Lesson> getLecturerLessons(Integer lecturerId, String fromDate, String toDate) {
        Map<String, Object> params = new HashMap<>();
        params.put(RuzURL.LECTURER_ID, lecturerId);
        params.put(RuzURL.LESSON_TYPE, RuzURL.LECTURER_LESSON_TYPE);
        params.put(RuzURL.FROM_DATE, fromDate);
        params.put(RuzURL.TO_DATE, toDate);
        return getLessons(params);
    }

    @Override
    public List<Student> getStudents(final Integer groupId) {
        Map<String, Object> params = new HashMap<>();
        params.put(RuzURL.GROUP_ID, groupId);
        String studentsInString = readRuz(RuzURL.STAFF_OF_GROUP, params);
        return ruzJsonParser.parse(studentsInString, Student.class);
    }

    @Override
    public List<Group> getGroups() {
        String groupsInString = readRuz(RuzURL.GROUPS_ENDPOINT, null);
        return ruzJsonParser.parse(groupsInString, Group.class);

    }

    private List<Lesson> getLessons(Map<String, ?> params) {
        return ruzJsonParser.parse(readRuz(RuzURL.LESSONS_ENDPOINT, params), Lesson.class);
    }

    private String paramsToString(Map<String, ?> params) {
        StringBuilder parameters = new StringBuilder();
        if (params != null) {
            parameters.append("?");
            for (String key : params.keySet()) {
                parameters.append(key)
                        .append("=")
                        .append(params.get(key))
                        .append("&");
            }
        }
        return parameters.toString();
    }

    private String readRuz(String endpoint, Map<String, ?> params) {
        try {
            URL url = new URL(RuzURL.URL.concat(endpoint).concat(paramsToString(params)));
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