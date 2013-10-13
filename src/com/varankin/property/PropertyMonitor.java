package com.varankin.property;

import java.beans.PropertyChangeListener;

/**
 *
 * @author Николай
 */
public interface PropertyMonitor
{

    void addPropertyChangeListener( PropertyChangeListener listener );

    void removePropertyChangeListener( PropertyChangeListener listener );
    
}
