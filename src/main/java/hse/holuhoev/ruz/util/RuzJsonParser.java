package hse.holuhoev.ruz.util;

import static com.google.common.base.Strings.isNullOrEmpty;

import hse.holuhoev.ruz.RuzField;
import hse.holuhoev.ruz.converter.AttributeConverter;
import hse.holuhoev.ruz.converter.Convert;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Evgeny Kholukhoev
 */
public class RuzJsonParser {
    private final Logger logger = LoggerFactory.getLogger(RuzJsonParser.class);

    public <T> List<T> parse(String str, Class<T> clazz) {
        if (str == null)
            return null;
        JsonArray jsonArray = new JsonArray(str);
        List<T> list = new LinkedList<>();

        try {
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.getJsonObject(i);
                T object = clazz.newInstance();

                for (Field field : clazz.getDeclaredFields()) {
                    RuzField ruzFieldAnnotation = field.getAnnotation(RuzField.class);
                    if (ruzFieldAnnotation != null) {
                        field.setAccessible(true);
                        Object value = jsonObject.getMap().get(isNullOrEmpty(ruzFieldAnnotation.name()) ? field.getName() : ruzFieldAnnotation.name());
                        if (field.getAnnotation(Convert.class) != null) {
                            Object converterInstance = field.getAnnotation(Convert.class).converter().newInstance();
                            if (converterInstance instanceof AttributeConverter) {
                                value = ((AttributeConverter) converterInstance).convertToEntityAttribute(value);
                            }
                        }
                        field.set(object, value);
                    }
                }

                list.add(object);
            }
            return list;
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    private RuzJsonParser() {
    }

    private static RuzJsonParser instance;

    public static RuzJsonParser getInstance() {
        if (instance == null) {
            instance = new RuzJsonParser();
        }
        return instance;
    }
}
