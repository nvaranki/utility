package com.varankin.characteristic;

import java.util.HashSet;
import java.util.Set;

/**
 * Реализация наблюдаемого изменяемого свойства.
 * 
 * @param <T> тип значения свойства.
 * 
 * @author &copy; 2015 Николай Варанкин
 */
public class НаблюдаемоеИзменяемоеСвойствоImpl<T> 
        implements НаблюдаемоеИзменяемоеСвойство<T>
{
    private final ИзменяемоеСвойство<T> СВОЙСТВО;
    private final Set<Наблюдатель<T>> НАБЛЮДАТЕЛИ;
    
    /**
     * @param t начальное значение свойства.
     */
    public НаблюдаемоеИзменяемоеСвойствоImpl( T t ) 
    {
        this( new ПростоеСвойство( t ) );
    }

    /**
     * @param свойство свойство для наблюдения.
     */
    public НаблюдаемоеИзменяемоеСвойствоImpl( ИзменяемоеСвойство<T> свойство ) 
    {
        СВОЙСТВО = свойство;
        НАБЛЮДАТЕЛИ = new HashSet<>(); //TODO dynamic re-assign Collections.EMPTY_SET
    }

    @Override
    public T значение() 
    {
        //TODO notify when changed
        return СВОЙСТВО.значение();
    }

    @Override
    public final void значение( T t ) 
    {
        T прежнее = СВОЙСТВО.значение();
        СВОЙСТВО.значение( t );
        Изменение<T> изменение = new Изменение<>( прежнее, t );
        for( Наблюдатель наблюдатель : НАБЛЮДАТЕЛИ )
            наблюдатель.отклик( изменение );
    }

    @Override
    public final Set<Наблюдатель<T>> наблюдатели() 
    {
        return НАБЛЮДАТЕЛИ; //TODO dynamic re-assign Collections.EMPTY_SET
    }

}
