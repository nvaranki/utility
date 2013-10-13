package com.varankin.property;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.AbstractSet;
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
public class MonitoredSet<E> extends AbstractSet<E> implements PropertyMonitor
{
    /** Название свойства - признака добавления нового элемента в набор. */
    public static final String PROPERTY_ADDED    = "added";
    /** Название свойства - признака удаления существующего элемента из набора. */
    public static final String PROPERTY_REMOVED  = "removed";

    private final PropertyChangeSupport PCS;
    private final Set<E> НАБОР;

    /**
     * @param набор хранилище элементов набора.
     */
    public MonitoredSet( Set<E> набор )
    {
        PCS = new PropertyChangeSupport( this );
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
        if( added ) PCS.firePropertyChange( PROPERTY_ADDED, null, e );
        return added;
    }

    @Override
    public final int size()
    {
        return НАБОР.size();
    }

    @Override
    public final void addPropertyChangeListener( PropertyChangeListener listener )
    {
        PCS.addPropertyChangeListener( listener );
    }

    @Override
    public final void removePropertyChangeListener( PropertyChangeListener listener )
    {
        PCS.removePropertyChangeListener( listener );
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
            PCS.firePropertyChange( PROPERTY_REMOVED, last, null );
            last = null;
        }
        
    }
    
}
