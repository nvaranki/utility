package com.varankin.filter;

/**
 * Композитный фильтр, выполняющий логическую операцию НЕ. 
 * 
 * @param <T> тип фильтруемого объекта.
 * 
 * @author &copy; 2015 Николай Варанкин
 */
public final class НЕ<T> implements Фильтр<T> 
{
    private final Фильтр<T> фильтр;

    /**
     * @param фильтр фильтр-операнд логической операции НЕ.
     */
    public НЕ( Фильтр<T> фильтр ) 
    {
        this.фильтр = фильтр;
    }

    @Override
    public boolean пропускает( T объект ) 
    {
        return !фильтр.пропускает( объект );
    }

    public static <T> НЕ не( Фильтр<T> фильтр )
    {
        return new НЕ( фильтр );
    }
    
}
