package hse.holuhoev.ruz.api;


import hse.holuhoev.domain.*;

import java.util.List;

/**
 * Предоставляет интерфейс взаимодействия с http://ruz.hse.ru
 *
 * @author Evgeny Kholukhoev
 */
public interface RuzApiService {
    List<Lesson> getStudentLessons(Integer studentId, String fromDate, String toDate);

    List<Lesson> getLecturerLessons(Integer lecturerId, String fromDate, String toDate);

    List<Student> getStudents(final Integer groupOid);

    List<Group> getGroups();

    List<Lecturer> getLecturers(Integer chairId);

    List<Lecturer> getAllLecturers();

    List<Chair> getAllChairs();

    List<Faculty> getAllFaculties();
}
