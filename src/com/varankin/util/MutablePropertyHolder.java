package com.varankin.util;

/**
 * Владелец одного или нескольких изменяемых именованных свойств.
 * 
 * @param <T> тип значения свойства или всех свойств, если он одинаков.
 * 
 * @author &copy; 2013 Николай Варанкин
 */
public interface MutablePropertyHolder<T> extends PropertyHolder<T>
{

    /**
     * Устанавливает значение именованного свойства.
     * 
     * @param name     название свойства.
     * @param newValue новое значение свойства.
     * @return предыдущее значение свойства.
     */
    T setPropertyValue( String name, T newValue );
    
}
