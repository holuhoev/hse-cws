package hse.holuhoev.datasource.util;

import java.util.List;
import java.util.Map;

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

    public List<T> getResult() {
        return result;
    }

    public Map<String, Object> getHints() {
        return hints;
    }
}
