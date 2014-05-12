package com.varankin.characteristic;

import java.util.HashSet;
import java.util.Set;

/**
 * Реализация простого наблюдаемого объекта.
 * 
 * @param <T> тип рассылаемого изменения.
 * 
 * @author &copy; 2014 Николай Варанкин
 */
public class НаблюдаемыйImpl<T> implements Наблюдаемый<T>
{
    private final Set<Наблюдатель<T>> НАБЛЮДАТЕЛИ;
    
    protected НаблюдаемыйImpl() 
    {
        НАБЛЮДАТЕЛИ = new HashSet<>();
    }

    /**
     * Рассылает изменение по наблюдателям.
     * 
     * @param прежнее прежнее значение.
     * @param новое   новое значение.
     */
    protected final void сообщить( T прежнее, T новое )
    {
        Изменение<T> изменение = new Изменение<>( прежнее, новое );
        for( Наблюдатель<T> наблюдатель : НАБЛЮДАТЕЛИ )
            наблюдатель.отклик( изменение );
    }

    @Override
    public final Set<Наблюдатель<T>> наблюдатели() 
    {
        return НАБЛЮДАТЕЛИ;
    }

}
