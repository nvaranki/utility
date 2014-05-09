package com.varankin.characteristic;

/**
 * Реализация простого изменяемого свойства.
 * 
 * @param <T> тип значения свойства.
 * 
 * @author &copy; 2014 Николай Варанкин
 */
public class ПростоеСвойство<T> implements ИзменяемоеСвойство<T>
{
    private T t;

    public ПростоеСвойство() 
    {
    }
        
    /**
     * @param t начальное значение свойства.
     */
    public ПростоеСвойство( T t ) 
    {
        this.t = t;
    }

    @Override
    public final T значение() 
    {
        return t;
    }

    @Override
    public void значение( T t ) 
    {
        this.t = t;
    }

}
