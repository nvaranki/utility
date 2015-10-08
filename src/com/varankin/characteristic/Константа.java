package com.varankin.characteristic;

/**
 * Реализация простого неизменяемого свойства.
 * 
 * @param <T> тип значения свойства.
 * 
 * @author &copy; 2015 Николай Варанкин
 */
public class Константа<T> implements Свойство<T>
{
    private T t;

    /**
     * @param t начальное значение свойства.
     */
    public Константа( T t ) 
    {
        this.t = t;
    }

    @Override
    public final T значение() 
    {
        return t;
    }

}
