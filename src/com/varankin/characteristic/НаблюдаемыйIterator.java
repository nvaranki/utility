package com.varankin.characteristic;

import java.util.Iterator;

/**
 * Итератор наблюдаемых коллекций.
 * 
 * @param <E> тип элемента коллекции.
 * 
 * @author &copy; 2015 Николай Варанкин
 */
class НаблюдаемыйIterator<E> implements Iterator<E>
{
    private final Iterator<E> ITERATOR;
    private final НаблюдаемыйАгент<E> АГЕНТ;
    private E last;

    НаблюдаемыйIterator( Iterator<E> iterator, НаблюдаемыйАгент<E> агент )
    {
        ITERATOR = iterator;
        АГЕНТ = агент;
    }

    @Override
    public final boolean hasNext()
    {
        return ITERATOR.hasNext();
    }

    @Override
    public final E next()
    {
        return last = ITERATOR.next();
    }

    @Override
    public final void remove()
    {
        ITERATOR.remove();
        АГЕНТ.сообщить( last, null );
        last = null;
    }
    
}
