package com.varankin.util;

/**
 *
 * @author Николай
 */
public interface PropertyHolder
{

    <T> T getPropertyValue(String name);

    <T> T setPropertyValue(String name, T newValue);
    
}
