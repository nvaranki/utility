package com.varankin.characteristic;

/**
 * Изменяемое свойство.
 * 
 * @param <T> тип значения свойства.
 * 
 * @author &copy; 2014 Николай Варанкин
 */
public interface ИзменяемоеСвойство<T> extends Свойство<T>
{
    
    /**
     * Устанавливает значение свойства.
     *
     * @param value значение свойства.
     */
    void значение( T value );
    
}
