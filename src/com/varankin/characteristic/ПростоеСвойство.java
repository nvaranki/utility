package com.varankin.characteristic;

/**
 * Реализация простого именованного свойства.
 * 
 * @param <T> тип значения свойства.
 * 
 * @author &copy; 2014 Николай Варанкин
 */
public class ПростоеСвойство<T> implements ИзменяемоеСвойство<T>
{
    private final String НАЗВАНИЕ;
    
    private T t;

    /**
     * @param название название свойства.
     */
    public ПростоеСвойство( String название ) 
    {
        НАЗВАНИЕ = название;
    }

    @Override
    public final String название() 
    {
        return НАЗВАНИЕ;
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
