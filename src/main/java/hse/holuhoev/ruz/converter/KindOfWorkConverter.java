package hse.holuhoev.ruz.converter;

import com.google.common.base.Strings;
import hse.holuhoev.domain.KindOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KindOfWorkConverter implements AttributeConverter<KindOfWork, String> {
    private final Logger logger = LoggerFactory.getLogger(KindOfWorkConverter.class);
    private final static String LECTURE = "Лекция";
    private final static String SEMINAR = "Семинар";
    private final static String SCIENCE = "Научно-исследовательский семинар";
    private final static String WORK_SHOW = "Показ работ";
    private final static String EXAM = "Экзамен";
    private final static String ORAL_EXAM = "Устный экзамен";
    private final static String ORAL_EXAM_2 = "Экзамен (устная часть)";
    private final static String WRITTEN_EXAM = "Письменный экзамен";
    private final static String WRITTEN_EXAM_2 = "Экзамен (письменная часть)";
    private final static String PRACTICE = "Практическое занятие";
    private final static String AUDITORIUM_CONSULTATION = "Аудиторная консультация";
    private final static String CONSULTATION = "Консультация";
    private final static String TEST = "Контрольная работа";
    private final static String MASTER_CLASS = "Мастер-класс";
    private final static String PROJECT_PROPOSAL = "Project Proposal";
    private final static String CASE = "Пробный письменный кейс-экзамен";

    @Override
    public KindOfWork convertToEntityAttribute(String ruzAttribute) {
        if (Strings.isNullOrEmpty(ruzAttribute)
                || ruzAttribute.equals(MASTER_CLASS)
                || ruzAttribute.equals(PROJECT_PROPOSAL)
                || ruzAttribute.equals(CASE))
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
            case PRACTICE:
                return KindOfWork.PRACTICE;
            case TEST:
                return KindOfWork.TEST;
            case CONSULTATION:
            case AUDITORIUM_CONSULTATION:
                return KindOfWork.CONSULTATION;
            case EXAM:
            case ORAL_EXAM:
            case WRITTEN_EXAM:
            case ORAL_EXAM_2:
            case WRITTEN_EXAM_2:
                return KindOfWork.EXAM;
            default:
                logger.error(String.format("Value \'%s\' is not supported!", ruzAttribute));
                return KindOfWork.NULL;
        }
    }
}
