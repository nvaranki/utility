package com.varankin.property;

import java.beans.PropertyChangeListener;
import java.util.Collection;

/**
 *
 * @author Николай
 */
public interface PropertyMonitor
{
    /**
     * @return изменяемая коллекция наблюдателей за данным монитором.
     */
    Collection<PropertyChangeListener> listeners();
}
