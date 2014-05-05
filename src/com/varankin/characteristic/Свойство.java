package com.varankin.characteristic;

/**
 * Именованное свойство.
 * 
 * @param <T> тип значения свойства.
 * 
 * @author &copy; 2014 Николай Варанкин
 */
public interface Свойство<T> extends Именованный
{
    
    /**
     * Возвращает значение свойства.
     *
     * @return текущее значение свойства.
     */
    T значение();
    
}
