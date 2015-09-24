package com.varankin.characteristic;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * Аннотация наблюдаемого свойства.
 * 
 * @author &copy; 2015 Николай Варанкин
 */
@Retention( value = RetentionPolicy.RUNTIME )
public @interface Свойственное 
{
    String value();
}
