package com.varankin.filter;

/**
 * Фильтр объектов.
 * 
 * @author &copy; 2014 Николай Варанкин
 * 
 * @param <T> тип фильтруемого объекта.
 */
public interface Фильтр<T> 
{
    /**
     * @param объект фильтруемый объект.
     * @return {@code true} если фильтр пропускает объект, иначе {@code false}.
     */
    boolean пропускает( T объект );
    
    Фильтр ВСЕ = new Фильтр() 
    {

        @Override
        public boolean пропускает( Object __ )
        {
            return true;
        }
    };
    
    Фильтр НИЧЕГО = new Фильтр() 
    {

        @Override
        public boolean пропускает( Object __ )
        {
            return false;
        }
    };
    
}
