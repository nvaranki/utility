package com.varankin.characteristic;

import java.util.*;

/**
 * Наблюдаемая поэлементно коллекция.
 * <ul>Изменения:
 * <li>null => э - добавление элемента</li>
 * <li>э => null - удаление элемента</li>
 * <li>э1 => э2  - замена элемента</li>
 * </ul>
 * 
 * @param <E> тип элемента коллекции.
 * 
 * @author &copy; 2015 Николай Варанкин
 */
public class НаблюдаемаяКоллекция<E> 
        extends AbstractCollection<E>
        implements Наблюдаемый<E> 
{
    private final Collection<E> КОЛЛЕКЦИЯ;
    private final НаблюдаемыйАгент<E> АГЕНТ;

    public НаблюдаемаяКоллекция( Collection<E> коллекция )
    {
        КОЛЛЕКЦИЯ = коллекция;
        АГЕНТ = new НаблюдаемыйАгент<>();
    }

    @Override
    public final Iterator<E> iterator()
    {
        return new НаблюдаемыйIterator<>( КОЛЛЕКЦИЯ.iterator(), АГЕНТ );
    }

    @Override
    public final boolean add( E e )
    {
        boolean added = КОЛЛЕКЦИЯ.add( e );
        if( added ) АГЕНТ.сообщить( null, e );
        return added;
    }

    @Override
    public final int size()
    {
        return КОЛЛЕКЦИЯ.size();
    }

    @Override
    public final Collection<Наблюдатель<E>> наблюдатели()
    {
        return АГЕНТ.наблюдатели();
    }

}
