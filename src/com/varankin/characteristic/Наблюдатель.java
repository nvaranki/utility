package com.varankin.characteristic;

/**
 * Наблюдатель за изменениями.
 * 
 * @param <T> тип изменяемого объекта.
 * 
 * @author &copy; 2014 Николай Варанкин
 */
public interface Наблюдатель<T> 
{
    void отклик( Изменение<T> изменение ); //TODO boolean ? Vetoable?
}
