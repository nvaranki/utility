package com.varankin.characteristic;

import java.util.Collection;

/**
 * Реализация наблюдаемого изменяемого свойства 
 * с интерфейсом неизменяемого.
 * 
 * @param <T> тип значения свойства.
 * 
 * @author &copy; 2014 Николай Варанкин
 */
public class НаблюдаемоеИзменяемоеСвойствоWrapper<T> 
        extends НаблюдаемоеИзменяемоеСвойствоImpl<T>
        implements НаблюдаемоеИзменяемоеСвойство<T>
{
    private НаблюдаемоеСвойство<T> НС;
    
    /**
     * @param t начальное значение свойства.
     */
    public НаблюдаемоеИзменяемоеСвойствоWrapper( T t ) 
    {
        super( t );
    }

    /**
     * @return данное свойство как неизменяемое.
     */
    public final НаблюдаемоеСвойство<T> toНаблюдаемоеСвойство()
    {
        if( НС == null )
            НС = new НаблюдаемоеСвойство<T>()
            {

                @Override
                public Collection наблюдатели()
                {
                    return НаблюдаемоеИзменяемоеСвойствоWrapper.this.наблюдатели();
                }

                @Override
                public T значение()
                {
                    return НаблюдаемоеИзменяемоеСвойствоWrapper.this.значение();
                }
        };
        return НС;
    }

}
