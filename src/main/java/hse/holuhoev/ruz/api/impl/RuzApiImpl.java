package hse.holuhoev.ruz.api.impl;




import hse.holuhoev.domain.RuzGroup;
import hse.holuhoev.domain.RuzLecturer;
import hse.holuhoev.domain.RuzLesson;
import hse.holuhoev.domain.RuzStudent;
import hse.holuhoev.ruz.RuzURL;
import hse.holuhoev.ruz.api.RuzApi;
import hse.holuhoev.ruz.util.RuzJsonParser;

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
public class RuzApiImpl implements RuzApi {
    private final RuzJsonParser ruzJsonParser;

    public RuzApiImpl() {
        this.ruzJsonParser = RuzJsonParser.getInstance();
    }

    @Override
    public Iterable<RuzLecturer> getLecturers() {
        String lecturersInString = readRuz(RuzURL.LECTURERS_ENDPOINS, null);
        return ruzJsonParser.parse(lecturersInString, RuzLecturer.class);
    }

    @Override
    public List<RuzLesson> getStudentLessons(Integer studentId, String fromDate, String toDate) {
        Map<String, Object> params = new HashMap<>();
        params.put(RuzURL.STUDENT_ID, studentId);
        params.put(RuzURL.FROM_DATE, fromDate);
        params.put(RuzURL.TO_DATE, toDate);
        String studentLesson = readLessons(params);
        return ruzJsonParser.parse(studentLesson, RuzLesson.class);
    }

    @Override
    public List<RuzLesson> getLecturerLessons(Integer lecturerId, String fromDate, String toDate) {
        return null;
    }

    @Override
    public List<RuzStudent> getStudents(final Integer groupId) {
        Map<String, Object> params = new HashMap<>();
        params.put(RuzURL.GROUP_ID, groupId);
        String studentsInString = readRuz(RuzURL.STAFF_OF_GROUP, params);
        return ruzJsonParser.parse(studentsInString, RuzStudent.class);
    }

    @Override
    public List<RuzGroup> getGroups() {
        String groupsInString = readRuz(RuzURL.GROUPS_ENDPOINS, null);
        return ruzJsonParser.parse(groupsInString, RuzGroup.class);

    }


    private String readLessons(Map<String, ?> params) {
        return readRuz(RuzURL.LESSONS_ENDPOINS, params);
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
