package com.varankin.characteristic;

import java.util.Collection;

/**
 * Наблюдаемый объект.
 * 
 * @param <T> тип изменяемого объекта.
 * 
 * @author &copy; 2014 Николай Варанкин
 */
public interface Наблюдаемый<T> 
{

    /**
     * @return изменяемая коллекция наблюдателей за данным объектом.
     */
    Collection<Наблюдатель<T>> наблюдатели();
    
}
