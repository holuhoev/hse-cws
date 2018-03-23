package hse.holuhoev.domain;

public enum CityType {
    MOSCOW,
    SAINT_PETERBURG,
    PERM,
    NIZHNIY_NOVGOROD,
    OTHER;

    private static final String MOSCOW_STR = "Москва";
    private static final String SAINT_PETERBURG_STR = "Санкт-Петербург";
    private static final String PERM_STR = "Пермь";
    private static final String NIZHNIY_NOVGOROD_STR = "Нижний Новгород";

    public static CityType of(String address) {
        if (address == null || address.isEmpty())
            return OTHER;
        if (address.startsWith(MOSCOW_STR))
            return MOSCOW;
        if (address.startsWith(SAINT_PETERBURG_STR))
            return SAINT_PETERBURG;
        if (address.startsWith(PERM_STR))
            return PERM;
        if (address.startsWith(NIZHNIY_NOVGOROD_STR))
            return NIZHNIY_NOVGOROD;
        return OTHER;
    }
}
