package com.varankin.characteristic;

import java.util.Collection;

/**
 * Наблюдаемый объект.
 * 
 * @author &copy; 2014 Николай Варанкин
 */
public interface Наблюдаемый 
{

    /**
     * @return изменяемая коллекция наблюдателей за данным объектом.
     */
    Collection<Наблюдатель> наблюдатели();
    
}
