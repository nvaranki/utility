package com.varankin.util;

import java.lang.annotation.*;
import java.lang.annotation.RetentionPolicy;
import java.util.prefs.Preferences;

/**
 * Аннотация особенностей получения объекта класса {@link Preferences}.
 *
 * @author &copy; 2010 Николай Варанкин
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Preference {
    String alias();
}
