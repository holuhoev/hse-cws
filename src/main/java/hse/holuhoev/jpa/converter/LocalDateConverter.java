package hse.holuhoev.jpa.converter;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;
import java.sql.Date;

import static java.util.Objects.requireNonNull;

/**
 * Конвертер хранимых значений типа {@link LocalDate}.
 * Применяется ко всем полям типа LocalDate.
 *
 * @author Evgeny Kholukhoev
 */
//@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date>
{

    @Override
    public Date convertToDatabaseColumn(LocalDate localDate)
    {
        return Date.valueOf(requireNonNull(localDate));
    }

    @Override
    public LocalDate convertToEntityAttribute(Date date)
    {

        return requireNonNull(date).toLocalDate();
    }
}
