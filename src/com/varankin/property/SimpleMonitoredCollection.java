package com.varankin.property;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

/**
 * Реализация {@linkplain Collection коллекции} с публичными {@linkplain PropertyMonitor свойствами}.
 * Слушатели изменений этих свойств моментально уведомляются при добавлении или 
 * удалении элементов коллекции. 
 * 
 * @param <E> класс элементов коллекции.
 *
 * @author &copy; 2015 Николай Варанкин
 */
public class SimpleMonitoredCollection<E> extends AbstractCollection<E> implements MonitoredCollection<E>
{
    private final FiringPropertyMonitor PCS;
    private final Collection<E> КОЛЛЕКЦИЯ;

    /**
     * @param коллекция хранилище элементов коллекции.
     */
    public SimpleMonitoredCollection( Collection<E> коллекция )
    {
        PCS = new SimplePropertyMonitor();
        КОЛЛЕКЦИЯ = коллекция;
    }

    @Override
    public final Iterator<E> iterator()
    {
        return new IteratorImpl( КОЛЛЕКЦИЯ.iterator() );
    }

    @Override
    public final boolean add( E e )
    {
        boolean added = КОЛЛЕКЦИЯ.add( e );
        if( added ) PCS.firePropertyChange( new PropertyChangeEvent( this, 
                            PROPERTY_ADDED, null, e ) );
        return added;
    }

    @Override
    public final int size()
    {
        return КОЛЛЕКЦИЯ.size();
    }

    @Override
    public Collection<PropertyChangeListener> listeners()
    {
        return PCS.listeners();
    }

    private class IteratorImpl implements Iterator<E>
    {
        private final Iterator<E> ITERATOR;
        private E last;

        private IteratorImpl( Iterator<E> iterator )
        {
            ITERATOR = iterator;
        }

        @Override
        public boolean hasNext()
        {
            return ITERATOR.hasNext();
        }

        @Override
        public E next()
        {
            return last = ITERATOR.next();
        }

        @Override
        public void remove()
        {
            ITERATOR.remove();
            PCS.firePropertyChange( new PropertyChangeEvent( SimpleMonitoredCollection.this, 
                            PROPERTY_REMOVED, last, null ) );
            last = null;
        }
        
    }
    
}
