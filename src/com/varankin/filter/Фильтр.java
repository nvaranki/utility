package com.varankin.filter;

/**
 * Фильтр объектов.
 * 
 * @author &copy; 2015 Николай Варанкин
 * 
 * @param <T> тип фильтруемого объекта.
 */
@FunctionalInterface
public interface Фильтр<T> 
{
    /**
     * @param объект фильтруемый объект.
     * @return {@code true} если фильтр пропускает объект, иначе {@code false}.
     */
    boolean пропускает( T объект );
    
}
