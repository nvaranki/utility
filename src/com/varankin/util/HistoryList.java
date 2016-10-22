package com.varankin.util;

import java.io.Serializable;
import java.util.*;
import java.util.logging.*;
import java.util.prefs.*;

/**
 * Композитный список хранения истории использования элементов.
 * Первая часть списка содержит неизменяемые элементы.
 * Вторая часть содержит динамичные элементы истории.
 * Любой элемент списка можно сделать приоритетным в истории, 
 * при этом остальные элементы истории сдвигаются в конец списка.
 * Список элементов оперативно сохраняется в локальном хранилище
 * пользователя операционной системы. Список восстанавливается
 * при создании элемента данного класса.
 *
 * @author &copy; 2016 Николай Варанкин
 * 
 * @param <T> класс элементов списка; обязан реализовать интерфейс {@link Serializable}
 *              для успешного сохранения/восстановления в хранилище.
 */
public class HistoryList<T> implements Iterable<T>
{
    private static final Logger LOGGER = Logger.getLogger(HistoryList.class.getName());
    
    private final List<T> history;
    private final int start;
    private final Preferences storage;

    /**
     * @param base        список с неизменяемыми элементами, используемый в качестве истории использования элементов.
     * @param preferences узел локального хранилища.
     */
    public HistoryList( List<T> base, Preferences preferences ) //TODO int limit
    {
        history = base;
        start = base.size();
        storage = preferences;
        restoreHistoryItems();
    }
    
    /**
     * @param base  список с неизменяемыми элементами, используемый в качестве истории использования элементов.
     * @param owner класс, имя и пакет которого используются при назначении узла локального хранилища.
     */
    public HistoryList( List<T> base, Class<?> owner ) //TODO int limit
    {
        history = base;
        start = base.size();
        storage = owner != null ? Preferences.userNodeForPackage( owner )
            .node( owner.getSimpleName() ) : null;
        restoreHistoryItems();
    }

    /**
     * @return число элементов в списке.
     */
    public final int size()
    {
        return history.size();
    }
    
    /**
     * @return индекс элемента списка, с которого начинаются динамичные элементы истории.
     */
    public final int start()
    {
        return start;
    }
    
    /**
     * @param index индекс элемента списка.
     * @return элемент списка по индексу или {@code null} при отсутствии элемента.
     */
    public final T get( int index )
    {
        return 0 <= index && index < history.size() ? history.get( index ) : null; 
    }
    
    /**
     * Перемещает или устанавливает заданный элемент на приоритетную позицию.
     * 
     * @param item приоритетный элемент. 
     */
    public final void advance( T item )
    {
        int peak = history.size();
        List<T> queue = history.subList( start, peak );
        queue.removeAll( Arrays.asList( item ) );
        queue.add( 0, item );
        peak = Math.max( peak, history.size() );
        for( int i = start; i < peak; i++ )
            saveHistoryItem( i, history.get( i ) );
    }
    
    /**
     * Очищает список от динамичных элементов истории.
     */
    public final void clear()
    {
        for( int i = start; i < history.size(); i++ )
            saveHistoryItem( i, null );
        history.subList( start, history.size() ).clear();
    }

    /**
     * @return итератор по всем элементам списка.
     */
    @Override
    public final Iterator<T> iterator() {
        return history.iterator();
    }

    private void saveHistoryItem( int индекс, T object ) 
    {
        if( storage != null )
        {
            String key = Integer.toString( индекс );
            byte[] value = object != null ? Serializator.objectToByteArray( object ) : null;
            if( value != null )
                storage.putByteArray( key, value );
            else
                storage.remove( key );
        }
    }

    private void restoreHistoryItem( int индекс ) 
    {
        if( storage != null )
        {
            byte[] data = storage.getByteArray( Integer.toString( индекс ), null );
            try
            {
                history.add( (T)Serializator.byteArrayToObject( data ) );
            }
            catch( Throwable ex )
            {
                LOGGER.log( Level.CONFIG, null, ex );
                saveHistoryItem( индекс, null ); // сломался, так сломался; уже не починишь
            }
        }
    }

    private void restoreHistoryItems()
    {
        List<String> keys;
        if( storage == null )
            keys = Collections.emptyList();
        else
            try 
            {
                keys = Arrays.asList( storage.keys() );
            } 
            catch( BackingStoreException ex ) 
            {
                LOGGER.log( Level.CONFIG, null, ex );
                keys = Collections.emptyList();
            }
        keys.stream()
                .map( i -> Integer.valueOf( i ) )
                .filter( i -> i >= start )
                .sorted().forEach( i -> restoreHistoryItem( i ) );
    }
    
}
