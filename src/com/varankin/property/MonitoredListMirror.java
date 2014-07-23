package com.varankin.property;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import java.util.List;

/**
 * Реализация списка типа {@link MonitoredList}, которая копирует исходный 
 * MonitoredList. Копирование выполняется посредством назначаемого
 * {@link PropertyChangeListener}, который использует предоставленный агент 
 * копирования. Данный список является внешне неизменяемым объектом. 
 *
 * @author &copy; 2013 Николай Варанкин
 */
public class MonitoredListMirror<E> extends MonitoredList<E>
{
    private final MonitoredList<E> SOURCE;

    public MonitoredListMirror( MonitoredList<E> source, Copier<E> агент )
    {
        super( new LinkedList<E>() );
        SOURCE = source;
        агент.copy( SOURCE, (List)this );
        source.listeners().add( new PropertyChangeListenerImpl( агент ) );
    }

    public interface Copier<E>
    {
        void copy( List<E> source, List<E> target );
    }
    
    private class PropertyChangeListenerImpl implements PropertyChangeListener
    {
        private final Copier<E> АГЕНТ;

        PropertyChangeListenerImpl( Copier<E> агент )
        {
            АГЕНТ = агент;
        }
        
        @Override
        public void propertyChange( PropertyChangeEvent evt )
        {
            АГЕНТ.copy( SOURCE, MonitoredListMirror.this );
        }

    }
    
}
