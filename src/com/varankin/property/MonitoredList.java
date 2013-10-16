package com.varankin.property;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.AbstractList;
import java.util.Collection;
import java.util.List;

/**
 * Реализация {@linkplain List набора} с публичными {@linkplain PropertyMonitor свойствами}.
 * Слушатели изменений этих свойств моментально уведомляются при добавлении или 
 * удалении элементов списка. 
 * 
 * @param <E> класс элементов списка.
 *
 * @author &copy; 2013 Николай Варанкин
 */
public class MonitoredList<E> extends AbstractList<E> implements PropertyMonitor
{
    /** Название свойства - признака добавления нового элемента в список. */
    public static final String PROPERTY_ADDED    = "added";
    /** Название свойства - признака удаления существующего элемента из списка. */
    public static final String PROPERTY_REMOVED  = "removed";

    private final FiringPropertyMonitor PCS;
    private final List<E> СПИСОК;

    /**
     * @param список хранилище элементов списка.
     */
    public MonitoredList( List<E> список )
    {
        PCS = new SimplePropertyMonitor();
        СПИСОК = список;
    }

    @Override
    public final void add( int index, E e )
    {
        СПИСОК.add( index, e );
        PCS.firePropertyChange( new PropertyChangeEvent( this, 
                            PROPERTY_ADDED, null, e ) );
    }

    @Override
    public final E set( int index, E e )
    {
        E old = СПИСОК.set( index, e );
        PCS.firePropertyChange( new PropertyChangeEvent( this, 
                            PROPERTY_ADDED, old, e ) );
        return old;
    }

    @Override
    public final E get( int index )
    {
        return СПИСОК.get( index );
    }
    
    @Override
    public final E remove( int index )
    {
        E old = СПИСОК.remove( index );
        PCS.firePropertyChange( new PropertyChangeEvent( this, 
                            PROPERTY_REMOVED, old, null ) );
        return old;
    }

    @Override
    public final int size()
    {
        return СПИСОК.size();
    }

    @Override
    public Collection<PropertyChangeListener> наблюдатели()
    {
        return PCS.наблюдатели();
    }

}
