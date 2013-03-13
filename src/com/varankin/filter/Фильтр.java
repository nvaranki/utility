package com.varankin.filter;

/**
 * Фильтр объектов.
 * 
 * @author &copy; 2013 Николай Варанкин
 */
public interface Фильтр<T> 
{
    /**
     * @param объект фильтруемый объект.
     * @return {@code true} если фильтр пропускает объект, иначе {@code false}.
     */
    boolean пропускает( T объект );
}
