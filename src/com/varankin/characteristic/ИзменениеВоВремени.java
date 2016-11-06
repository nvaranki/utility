package com.varankin.characteristic;

/**
 * Изменение значения в момент времени.
 * 
 * @param <T> тип значения.
 * 
 * @author &copy; 2016 Николай Варанкин
 */
public class ИзменениеВоВремени<T> extends Изменение<T>
{
    public final long МОМЕНТ;

    public ИзменениеВоВремени( T прежнее, T актуальное, long момент ) 
    {
        super( прежнее, актуальное );
        this.МОМЕНТ = момент;
    }
    
}
