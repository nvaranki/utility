package com.varankin.util;

import java.beans.PropertyChangeListener;

/**
 *
 * @author Николай
 */
public interface PropertyHolder 
{

    void addPropertyChangeListener(PropertyChangeListener listener);

    void removePropertyChangeListener(PropertyChangeListener listener);

    <T> T getPropertyValue(String name);

    <T> T setPropertyValue(String name, T newValue);
    
}
