package com.varankin.filter;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Обертка {@linkplain Iterable итерируемого} объекта, оперирующая 
 * исключительно элементами, пропущенными через фильтры.
 * 
 * @author &copy; 2013 Николай Варанкин
 * 
 * @param <T> класс итерируемых элементов.
 */
public final class FilteredIterable<T> implements Iterable<T> 
{
    private final java.lang.Iterable<T> iterable;
    private final Фильтр<? super T>[] фильтры;

    /**
     * @param iterable исходный итерируемый объект.
     * @param фильтры  фильтры для отбора элементов.
     */
    public FilteredIterable( Iterable<T> iterable, Фильтр<? super T>... фильтры ) 
    {
        this.iterable = iterable;
        this.фильтры = фильтры;
    }

    /**
     * {@linkplain Iterator Итератор} элементов, пропущенных всеми заданными фильтрами.
     */
    @Override
    public Iterator<T> iterator() 
    {
        return new IteratorImpl( iterable.iterator() );
    }
    
    private T filter( T кандидат )
    {
        for( Фильтр<? super T> фильтр : фильтры )
            if( !фильтр.пропускает( кандидат ) )
                return null;
        return кандидат;
    }

    private class IteratorImpl implements Iterator<T>
    {
        final Iterator<T> iterator;
        T очередной;

        IteratorImpl( Iterator<T> iterator ) 
        {
            this.iterator = iterator;
        }
        
        @Override
        public boolean hasNext() 
        {
            while( true )
                if( очередной != null )
                    return true;
                else if( !iterator.hasNext() )
                    return false;
                else if( ( очередной = filter( iterator.next() ) ) != null )
                    return true;
        }

        @Override
        public T next() 
        {
            if( hasNext() )
            {
                T кандидат = очередной;
                очередной = null;
                return кандидат;
            }
            else
                throw new NoSuchElementException();
        }

        @Override
        public void remove() 
        {
            iterator.remove();
        }
        
    }
    
}
