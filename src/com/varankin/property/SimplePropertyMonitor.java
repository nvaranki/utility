package com.varankin.property;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
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
    public final Collection<PropertyChangeListener> наблюдатели()
    {
        return LISTENERS;
    }
    
    @Override
    public final void firePropertyChange( PropertyChangeEvent event )
    {
        for( PropertyChangeListener listener : LISTENERS )
            listener.propertyChange( event );
    }

}
