package com.varankin.characteristic;

import java.util.*;

/**
 * Наблюдаемый поэлементно список.
 * <ul>Изменения:
 * <li>null => э - добавление или вставка элемента</li>
 * <li>э => null - удаление элемента</li>
 * <li>э1 => э2  - замена элемента</li>
 * </ul>
 * 
 * @param <E> тип элемента списка.
 * 
 * @author &copy; 2015 Николай Варанкин
 */
public class НаблюдаемыйСписок<E> 
        extends AbstractList<E>
        implements Наблюдаемый<E>
{
    private final List<E> СПИСОК;
    private final НаблюдаемыйАгент<E> АГЕНТ;

    public НаблюдаемыйСписок( List<E> список )
    {
        СПИСОК = список;
        АГЕНТ = new НаблюдаемыйАгент<>();
    }

    @Override
    public final void add( int index, E e )
    {
        СПИСОК.add( index, e );
        АГЕНТ.сообщить( null, e );
    }

    @Override
    public final E set( int index, E e )
    {
        E было = СПИСОК.set( index, e );
        АГЕНТ.сообщить( было, e );
        return было;
    }

    @Override
    public final E get( int index )
    {
        return СПИСОК.get( index );
    }
    
    @Override
    public final E remove( int index )
    {
        E было = СПИСОК.remove( index );
        АГЕНТ.сообщить( было, null );
        return было;
    }

    @Override
    public final int size()
    {
        return СПИСОК.size();
    }

    @Override
    public final Collection<Наблюдатель<E>> наблюдатели()
    {
        return АГЕНТ.наблюдатели();
    }
    
}
