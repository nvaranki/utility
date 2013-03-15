package com.varankin.util;

import java.util.*;

/**
 * Сцепка {@linkplain Iterable итерируемых} объектов.
 *
 * @author &copy; 2013 Николай Варанкин
 * 
 * @param <E> класс итерируемых элементов.
 */
public final class MultiIterable<E> implements Iterable<E>
{
    private final Iterable<E>[] SOURCES;

    public MultiIterable( Iterable<E>... iterables ) 
    {
        SOURCES = Arrays.copyOf( iterables, iterables.length );
    }

    @Override
    public Iterator<E> iterator() 
    {
        return new MultiIterator();
    }
    
    private class MultiIterator implements Iterator<E> 
    {
        private int index = -1;
        private Iterator<E> iterator = Collections.emptyIterator();

        @Override
        public boolean hasNext() 
        {
            while( index < SOURCES.length )
                if( iterator.hasNext() )
                    return true;
                else if( ++index < SOURCES.length )
                    iterator = SOURCES[index].iterator();
                else 
                    iterator = null;
            return false;
        }

        @Override
        public E next() 
        {
            if( hasNext() )
                return iterator.next();
            else 
                throw new NoSuchElementException();
        }

        @Override
        public void remove() 
        {
            if( iterator == null )
                throw new IllegalStateException();
            else
                iterator.remove();
        }
        
    }
    
}
