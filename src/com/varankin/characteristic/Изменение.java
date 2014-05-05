package com.varankin.characteristic;

/**
 * Изменение значения в момент времени.
 * 
 * @param <T> тип значения.
 * 
 * @author &copy; 2014 Николай Варанкин
 */
public class Изменение<T>
{
    public final T ПРЕЖНЕЕ, АКТУАЛЬНОЕ;
    public final long момент; //TODO into extending class?

    public Изменение( T прежнее, T актуальное, long момент ) 
    {
        this.ПРЕЖНЕЕ = прежнее;
        this.АКТУАЛЬНОЕ = актуальное;
        this.момент = момент;
    }

    public Изменение( T прежнее, T актуальное )
    {
        this( прежнее, актуальное, System.currentTimeMillis() );
    }
    
    
}
