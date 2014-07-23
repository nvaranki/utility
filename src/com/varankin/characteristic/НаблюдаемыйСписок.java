package com.varankin.characteristic;

import com.varankin.property.MonitoredList;
import java.beans.PropertyChangeEvent;
import java.util.List;

/**
 * Наблюдаемый поэлементно список.
 * <ul>Изменения:
 * <li>null => э - добавление или вставка элемента</li>
 * <li>э => null - удаление элемента</li>
 * <li>э1 => э2  - замена элемента</li>
 * </ul>
 * 
 * @param <E> тип элемента списка.
 * 
 * @author &copy; 2014 Николай Варанкин
 */
public class НаблюдаемыйСписок<E> 
        extends НаблюдаемыйImpl<E> 
        implements Свойство<List<E>>
{
    @Deprecated //TODO unlink from MonitoredList
    private final MonitoredList<E> СПИСОК;

    public НаблюдаемыйСписок( List<E> list )
    {
        СПИСОК = new MonitoredList<>( list );
        СПИСОК.listeners().add( (PropertyChangeEvent e) ->
            { сообщить( (E)e.getOldValue(), (E)e.getNewValue() ); } );
    }

    @Override
    public final List<E> значение()
    {
        return СПИСОК;
    }
    
}
