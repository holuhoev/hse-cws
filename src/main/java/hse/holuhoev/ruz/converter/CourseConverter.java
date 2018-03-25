package hse.holuhoev.ruz.converter;

import hse.holuhoev.domain.Course;

import static java.util.Objects.requireNonNull;

public class CourseConverter implements AttributeConverter<Course, Integer> {
    @Override
    public Course convertToEntityAttribute(Integer ruzAttribute) {
        return Course.of(requireNonNull(ruzAttribute));
    }
}
