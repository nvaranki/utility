package com.varankin.property;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Реализация {@linkplain Set набора} с публичными {@linkplain PropertyMonitor свойствами}.
 * Слушатели изменений этих свойств моментально уведомляются при добавлении или 
 * удалении элементов набора. 
 * 
 * @param <E> класс элементов набора.
 *
 * @author &copy; 2013 Николай Варанкин
 */
public class MonitoredSet<E> extends AbstractSet<E> implements MonitoredCollection<E>
{
    private final FiringPropertyMonitor PCS;
    private final Set<E> НАБОР;

    /**
     * @param набор хранилище элементов набора.
     */
    public MonitoredSet( Set<E> набор )
    {
        PCS = new SimplePropertyMonitor();
        НАБОР = набор;
    }

    @Override
    public final Iterator<E> iterator()
    {
        return new IteratorImpl( НАБОР.iterator() );
    }

    @Override
    public final boolean add( E e )
    {
        boolean added = НАБОР.add( e );
        if( added ) PCS.firePropertyChange( new PropertyChangeEvent( this, 
                            PROPERTY_ADDED, null, e ) );
        return added;
    }

    @Override
    public final int size()
    {
        return НАБОР.size();
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
            PCS.firePropertyChange( new PropertyChangeEvent( MonitoredSet.this, 
                            PROPERTY_REMOVED, last, null ) );
            last = null;
        }
        
    }
    
}
