package com.varankin.property;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Николай
 */
public class SynchronizedPropertyMonitor implements FiringPropertyMonitor
{
    private final Set<PropertyChangeListener> LISTENERS 
            = Collections.synchronizedSet( new HashSet<>() );

    @Override
    public final Collection<PropertyChangeListener> listeners()
    {
        return LISTENERS;
    }
    
    @Override
    public final void firePropertyChange( PropertyChangeEvent event )
    {
        synchronized( LISTENERS )
        {
            for( PropertyChangeListener listener : LISTENERS )
                listener.propertyChange( event );
        }
    }

}
