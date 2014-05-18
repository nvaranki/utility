package com.varankin.graph;

import java.util.Collection;

/**
 * Узел неориентированного графа.
 * 
 * @param <T> тип узлов графа.
 *
 * @author &copy; 2014 Николай Варанкин
 */
public class ПростойУзелГрафа<T extends УзелГрафа<T>> implements УзелГрафа<T>
{
    private final Collection<T> УЗЛЫ;

    /**
     * @param коллекция коллекция для хранения связанных узлов.
     */
    public ПростойУзелГрафа( Collection<T> коллекция )
    {
        УЗЛЫ = коллекция;
    }
    
    @Override
    public final Collection<T> узлы()
    {
        return УЗЛЫ;
    }
    
}
