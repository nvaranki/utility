package com.varankin.characteristic;

/**
 * Изменение значения.
 * 
 * @param <T> тип значения.
 * 
 * @author &copy; 2016 Николай Варанкин
 */
public class Изменение<T>
{
    public final T ПРЕЖНЕЕ, АКТУАЛЬНОЕ;

    public Изменение( T прежнее, T актуальное )
    {
        this.ПРЕЖНЕЕ = прежнее;
        this.АКТУАЛЬНОЕ = актуальное;
    }

}
