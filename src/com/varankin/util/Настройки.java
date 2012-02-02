package com.varankin.util;

import java.lang.annotation.Annotation;
import java.util.prefs.Preferences;

/**
 * Локатор настроек объекта.
 *
 * @author &copy; 2011 Николай Варанкин
 */
@Preference(alias="setup")
public class Настройки 
{
    private Настройки(){};//TODO

    /**
     * Возвращает менеджер настроек заданного объекта. 
     * 
     * @param объект соискатель настроек.
     * @return менеджер настроек объекта.
     */
    public static Preferences preferences( Object объект )
    {
        Class<?> класс = объект != null ? объект.getClass() : Настройки.class;
        return Preferences.userNodeForPackage( класс ).node( узел( класс ) );
    }

    private static String узел( Class<?> класс )
    {
        String name = класс.getSimpleName();
        for( Annotation a : класс.getAnnotations() )
            if( a instanceof Preference )
                name = ((Preference)a).alias();
        return name;
    }

}
