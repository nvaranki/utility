package com.varankin.characteristic;

/**
 * Наблюдаемое именованное свойство.
 * 
 * @param <T> тип значения свойства.
 * 
 * @author &copy; 2014 Николай Варанкин
 */
public interface НаблюдаемоеИзменяемоеСвойство<T> 
    extends ИзменяемоеСвойство<T>, НаблюдаемоеСвойство<T>
{
}
