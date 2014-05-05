package com.varankin.characteristic;

import java.util.HashSet;
import java.util.Set;

/**
 * Реализация простого именованного свойства.
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
     * @param название название свойства.
     */
    public ПростоеНаблюдаемоеСвойство( String название ) 
    {
        super( название );
        НАБЛЮДАТЕЛИ = new HashSet<>(); //TODO dynamic re-assign Collections.EMPTY_SET
    }

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
