package com.varankin.filter;

/**
 * Композитный фильтр, выполняющий логическую операцию ИЛИ. 
 * Если очередной фильтр возвращает {@code true}, результат операции будет 
 * {@code true} и остальные фильтры не применяются. 
 * При отсутствии фильтров, или если ни один из фильтров не вернул {@code true}, 
 * результат операции будет {@code false}.
 * 
 * @param <T> тип фильтруемого объекта.
 * 
 * @author &copy; 2015 Николай Варанкин
 */
public final class Или<T> implements Фильтр<T> 
{
    private final Фильтр<T>[] фильтры;

    /**
     * @param фильтры набор фильтров-операндов логической операции ИЛИ.
     */
    public Или( Фильтр<T>... фильтры ) 
    {
        this.фильтры = фильтры;
    }

    @Override
    public boolean пропускает( T объект ) 
    {
        boolean результат = false;
        for( Фильтр<T> фильтр : фильтры )
            if( результат = фильтр.пропускает( объект ) )
                break;
        return результат;
    }

    public static <T> Или или( Фильтр<T>... фильтры )
    {
        return new Или( фильтры );
    }
}
