package hse.holuhoev.ruz.converter;


public interface AttributeConverter<X, Y> {
    /**
     * Converts the data stored in the ruz object field into the
     * value to be stored in the entity attribute.
     *
     * @param ruzAttribute the data from the ruz object field to be converted
     * @return the converted value to be stored in the entity attribute
     */
    public X convertToEntityAttribute(Y ruzAttribute);
}
