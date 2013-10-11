package com.varankin.util;

import java.beans.PropertyChangeEvent;

/**
 *
 * @author Николай
 */
public interface FiringPropertyMonitor extends PropertyMonitor
{
    void firePropertyChange( PropertyChangeEvent event );
}
