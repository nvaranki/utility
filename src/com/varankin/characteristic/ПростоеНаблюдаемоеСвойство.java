package com.varankin.characteristic;

import java.util.HashSet;
import java.util.Set;

/**
 * Реализация простого наблюдаемого свойства.
 * 
 * @param <T> тип значения свойства.
 * 
 * @author &copy; 2014 Николай Варанкин
 */
public class ПростоеНаблюдаемоеСвойство<T> 
        extends ПростоеСвойство<T>
        implements НаблюдаемоеСвойство<T>
{
    private final Set<Наблюдатель> НАБЛЮДАТЕЛИ;
    
    /**
     * @param t начальное значение свойства.
     */
    public ПростоеНаблюдаемоеСвойство( T t ) 
    {
        super( t );
        НАБЛЮДАТЕЛИ = new HashSet<>(); //TODO dynamic re-assign Collections.EMPTY_SET
    }

    /**
     * Устанавливает значение свойства и уведомляет наблюдателей об изменении.
     *
     * @param t значение свойства.
     */
    @Override
    public final void значение( T t ) 
    {
        T прежнее = значение();
        super.значение( t );
        Изменение<T> изменение = new Изменение<>( прежнее, t );
        for( Наблюдатель наблюдатель : НАБЛЮДАТЕЛИ )
            наблюдатель.отклик( изменение );
    }

    @Override
    public final Set<Наблюдатель> наблюдатели() 
    {
        return НАБЛЮДАТЕЛИ; //TODO dynamic re-assign Collections.EMPTY_SET
    }

}
