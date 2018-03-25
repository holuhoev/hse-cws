package hse.holuhoev.ruz.converter;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * The Convert annotation is used to specify the conversion of a Basic field or
 * property. It is not necessary to use the Basic annotation or corresponding XML
 * element to specify the basic type.
 */
@Target({METHOD, FIELD, TYPE})
@Retention(RUNTIME)
public @interface Convert {
    /**
     * Specifies the converter to be applied. A value for this
     * element must be specified if multiple converters would
     * otherwise apply.
     */
    Class converter() default void.class;
}