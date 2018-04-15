package hse.holuhoev.ruz.converter;

import com.google.common.base.Strings;
import hse.holuhoev.domain.KindOfWork;

public class KindOfWorkConverter implements AttributeConverter<KindOfWork, String> {
    private final static String LECTURE = "Лекция";
    private final static String SEMINAR = "Семинар";
    private final static String SCIENCE = "Научно-исследовательский семинар";
    private final static String WORK_SHOW = "Показ работ";
    private final static String EXAM = "Экзамен";
    private final static String PRACTICE = "Практическое занятие";
    private final static String CONSULTATION = "Аудиторная консультация";
    private final static String TEST = "Контрольная работа";

    @Override
    public KindOfWork convertToEntityAttribute(String ruzAttribute) {
        if (Strings.isNullOrEmpty(ruzAttribute))
            return KindOfWork.NULL;
        switch (ruzAttribute) {
            case LECTURE:
                return KindOfWork.LECTURE;
            case SEMINAR:
                return KindOfWork.SEMINAR;
            case SCIENCE:
                return KindOfWork.SCIENCE;
            case WORK_SHOW:
                return KindOfWork.WORK_SHOW;
            case EXAM:
                return KindOfWork.EXAM;
            case PRACTICE:
                return KindOfWork.PRACTICE;
            case CONSULTATION:
                return KindOfWork.CONSULTATION;
            case TEST:
                return KindOfWork.TEST;
            default:
                throw new IllegalArgumentException(String.format("Value \'%s\' is not supported!", ruzAttribute));
        }
    }
}
