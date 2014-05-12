package com.varankin.characteristic;

import com.varankin.property.MonitoredSet;
import java.beans.PropertyChangeEvent;
import java.util.Set;

/**
 * Наблюдаемый поэлементно набор.
 * <ul>Изменения:
 * <li>null => э - добавление элемента</li>
 * <li>э => null - удаление элемента</li>
 * <li>э1 => э2  - замена элемента</li>
 * </ul>
 * 
 * @param <E> тип элемента набора.
 * 
 * @author &copy; 2014 Николай Варанкин
 */
public class НаблюдаемыйНабор<E> 
        extends НаблюдаемыйImpl<E> 
        implements Свойство<Set<E>>
{
    @Deprecated //TODO unlink from MonitoredSet
    private final MonitoredSet<E> НАБОР;

    public НаблюдаемыйНабор( Set<E> list )
    {
        НАБОР = new MonitoredSet<>( list );
        НАБОР.наблюдатели().add( (PropertyChangeEvent e) ->
            { сообщить( (E)e.getOldValue(), (E)e.getNewValue() ); } );
    }

    @Override
    public final Set<E> значение()
    {
        return НАБОР;
    }
    
}
