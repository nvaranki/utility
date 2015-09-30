package com.varankin.characteristic;

import java.util.*;

/**
 * Наблюдаемый поэлементно набор.
 * <ul>Изменения:
 * <li>null => э - добавление элемента</li>
 * <li>э => null - удаление элемента</li>
 * <li>э1 => э2  - замена элемента</li>
 * </ul>
 * 
 * @param <E> тип элемента набора.
 * 
 * @author &copy; 2015 Николай Варанкин
 */
public class НаблюдаемыйНабор<E> 
        extends AbstractSet<E>
        implements Наблюдаемый<E>
{
    private final Set<E> НАБОР;
    private final НаблюдаемыйАгент<E> АГЕНТ;

    public НаблюдаемыйНабор( Set<E> набор )
    {
        НАБОР = набор;
        АГЕНТ = new НаблюдаемыйАгент<>();
    }

    @Override
    public final Iterator<E> iterator()
    {
        return new НаблюдаемыйIterator<>( НАБОР.iterator(), АГЕНТ );
    }

    @Override
    public final boolean add( E e )
    {
        boolean added = НАБОР.add( e );
        if( added ) АГЕНТ.сообщить( null, e );
        return added;
    }

    @Override
    public final int size()
    {
        return НАБОР.size();
    }

    @Override
    public final Collection<Наблюдатель<E>> наблюдатели()
    {
        return АГЕНТ.наблюдатели();
    }

}
