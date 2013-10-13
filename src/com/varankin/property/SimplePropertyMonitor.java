package com.varankin.property;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Николай
 */
public class SimplePropertyMonitor implements FiringPropertyMonitor
{
    private final Set<PropertyChangeListener> LISTENERS = new HashSet<>();

    @Override
    public final void addPropertyChangeListener( PropertyChangeListener listener )
    {
        LISTENERS.add( listener );
    }

    @Override
    public final void removePropertyChangeListener( PropertyChangeListener listener )
    {
        LISTENERS.remove( listener );
    }
    
    @Override
    public final void firePropertyChange( PropertyChangeEvent event )
    {
        for( PropertyChangeListener listener : LISTENERS )
            listener.propertyChange( event );
    }
    
}
