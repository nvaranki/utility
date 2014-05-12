package com.varankin.characteristic;

import java.util.Collection;

/**
 * Обертка наблюдаемого свойства.
 * 
 * @param <T> тип значения свойства.
 * 
 * @author &copy; 2014 Николай Варанкин
 */
public final class НаблюдаемоеСвойствоWrapper<T> implements НаблюдаемоеСвойство<T>
{
    private final НаблюдаемоеСвойство<T> СВОЙСТВО;

    public НаблюдаемоеСвойствоWrapper( НаблюдаемоеСвойство<T> свойство )
    {
        СВОЙСТВО = свойство;
    }

    @Override
    public Collection наблюдатели()
    {
        return СВОЙСТВО.наблюдатели();
    }

    @Override
    public T значение()
    {
        return СВОЙСТВО.значение();
    }
    
}
