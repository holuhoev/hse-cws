package hse.holuhoev.domain;

import hse.holuhoev.ruz.RuzField;
import hse.holuhoev.ruz.converter.Convert;
import hse.holuhoev.ruz.converter.CourseConverter;
import hse.holuhoev.ruz.converter.EducationTypeConverter;

import javax.persistence.*;

/**
 * Группа студентов.
 *
 * @author Evgeny Kholukhoev
 */
@Entity
@Table(name = "STUDGROUP")
public class Group extends RuzObject {
    @Id
    @RuzField(name = "groupOid")
    @Column(name = "ID")
    private Integer Id;

    @RuzField
    @Column(name = "course")
    @Convert(converter = CourseConverter.class)
    private Course course;

    @Column(name = "institute_id")
    private Integer instituteId;

    @RuzField(name = "facultyOid")
    @Column(name = "faculty_id")
    private Integer facultyId;

    @RuzField
    @Column(name = "group_number")
    private String number;

    @Column(name = "education_type")
    @RuzField(name = "kindEducation")
    @Convert(converter = EducationTypeConverter.class)
    private EducationType educationType;

    public Integer getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        this.Id = id;
    }

    public Group() {

    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(Integer instituteId) {
        this.instituteId = instituteId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public EducationType getEducationType() {
        return educationType;
    }

    public void setEducationType(EducationType educationType) {
        this.educationType = educationType;
    }
}

