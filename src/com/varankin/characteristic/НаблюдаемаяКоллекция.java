package com.varankin.characteristic;

import com.varankin.property.MonitoredCollection;
import com.varankin.property.SimpleMonitoredCollection;
import java.beans.PropertyChangeEvent;
import java.util.Collection;

/**
 * Наблюдаемая поэлементно коллекция.
 * <ul>Изменения:
 * <li>null => э - добавление элемента</li>
 * <li>э => null - удаление элемента</li>
 * <li>э1 => э2  - замена элемента</li>
 * </ul>
 * 
 * @param <E> тип элемента коллекции.
 * 
 * @author &copy; 2015 Николай Варанкин
 */
public class НаблюдаемаяКоллекция<E> 
        extends НаблюдаемыйImpl<E> 
        implements Свойство<Collection<E>>
{
    @Deprecated //TODO unlink from MonitoredCollection
    private final MonitoredCollection<E> КОЛЛЕКЦИЯ;

    public НаблюдаемаяКоллекция( Collection<E> collection )
    {
        КОЛЛЕКЦИЯ = new SimpleMonitoredCollection<>( collection );
        КОЛЛЕКЦИЯ.listeners().add( (PropertyChangeEvent e) ->
            { сообщить( (E)e.getOldValue(), (E)e.getNewValue() ); } );
    }

    @Override
    public final Collection<E> значение()
    {
        return КОЛЛЕКЦИЯ;
    }
    
}
