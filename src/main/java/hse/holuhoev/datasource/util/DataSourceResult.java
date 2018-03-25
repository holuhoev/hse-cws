package hse.holuhoev.datasource.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class DataSourceResult<T> {
    private List<T> result;
    private Map<String, Object> hints;

    private DataSourceResult(List<T> objects, Map<String, Object> hints) {
        this.result = objects;
        this.hints = hints;
    }

    public static <T> DataSourceResult create(List<T> objects, Map<String, Object> hints) {
        return new DataSourceResult<>(objects, hints);
    }

    public static <T> DataSourceResult create(List<T> objects) {
        return create(objects, new HashMap<>());
    }

    public static <T> DataSourceResult create(Iterable<T> objects, Map<String, Object> hints) {
        return new DataSourceResult<>(StreamSupport.stream(objects.spliterator(), false).collect(Collectors.toList()), hints);
    }

    public static <T> DataSourceResult createEmpty() {
        return create(new LinkedList<>());
    }

    public static <T> DataSourceResult create(Iterable<T> objects) {
        return create(objects, new HashMap<>());
    }

    public List<T> getResult() {
        return result;
    }

    public Map<String, Object> getHints() {
        return hints;
    }
}
