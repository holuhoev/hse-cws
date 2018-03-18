package hse.holuhoev.ruz.util;

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
                    field.setAccessible(true);
                    field.set(object, jsonObject.getMap().get(field.getName()));
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
