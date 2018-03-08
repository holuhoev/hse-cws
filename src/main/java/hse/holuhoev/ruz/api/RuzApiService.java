package hse.holuhoev.ruz.api;


import hse.holuhoev.domain.RuzGroup;
import hse.holuhoev.domain.RuzLecturer;
import hse.holuhoev.domain.RuzLesson;
import hse.holuhoev.domain.RuzStudent;

import java.util.List;

/**
 * Предоставляет интерфейс взаимодействия с http://ruz.hse.ru
 *
 * @author Evgeny Kholukhoev
 */
public interface RuzApiService {
    List<RuzLesson> getStudentLessons(Integer studentId, String fromDate, String toDate);

    List<RuzLesson> getLecturerLessons(Integer lecturerId, String fromDate, String toDate);

    List<RuzStudent> getStudents(final Integer groupOid);

    List<RuzGroup> getGroups();

    List<RuzLecturer> getLecturers(Integer chairId);

    List<RuzLecturer> getAllLecturers();
}
