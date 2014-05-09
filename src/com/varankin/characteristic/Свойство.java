package com.varankin.characteristic;

/**
 * Измеряемое свойство.
 * 
 * @param <T> тип значения свойства.
 * 
 * @author &copy; 2014 Николай Варанкин
 */
public interface Свойство<T>
{
    
    /**
     * Возвращает значение свойства.
     *
     * @return текущее значение свойства.
     */
    T значение();
    
}
