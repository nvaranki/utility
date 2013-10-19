package com.varankin.property;

import java.util.Collection;

/**
 *
 * @author &copy; 2013 Николай Варанкин
 *
 */
public interface MonitoredCollection<E> extends Collection<E>, PropertyMonitor
{
    /** Название свойства - признака добавления нового элемента в коллекцию. */
    String PROPERTY_ADDED = "MonitoredCollection.add";
    /** Название свойства - признака удаления существующего элемента из коллекции. */
    String PROPERTY_REMOVED = "MonitoredCollection.remove";
    
}
