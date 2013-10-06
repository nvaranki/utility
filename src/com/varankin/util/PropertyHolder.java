package com.varankin.util;

/**
 * Владелец одного или нескольких именованных свойств.
 * 
 * @param <T> тип значения свойства или всех свойств, если он одинаков.
 * 
 * @author &copy; 2013 Николай Варанкин
 */
public interface PropertyHolder<T>
{

    /**
     * Возвращает значение именованного свойства.
     *
     * @param name название свойства.
     * @return текущее значение свойства.
     */
    T getPropertyValue( String name );
    
}
