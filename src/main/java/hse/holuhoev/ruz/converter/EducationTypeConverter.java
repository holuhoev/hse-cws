package hse.holuhoev.ruz.converter;

import hse.holuhoev.domain.EducationType;

import static java.util.Objects.requireNonNull;

public class EducationTypeConverter implements AttributeConverter<EducationType, Integer> {
    @Override
    public EducationType convertToEntityAttribute(Integer ruzAttribute) {
        return EducationType.of(requireNonNull(ruzAttribute));
    }
}
