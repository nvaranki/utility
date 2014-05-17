package com.varankin.graph;

import java.util.Collection;

/**
 * Узел ненаправленного графа.
 * 
 * @param <T> тип узлов графа.
 *
 * @author &copy; 2014 Николай Варанкин
 */
public class ПростойУзел<T extends Узел> implements Узел<T>
{
    private final Collection<T> УЗЛЫ;

    /**
     * @param коллекция коллекция для хранения связанных узлов.
     */
    public ПростойУзел( Collection<T> коллекция )
    {
        УЗЛЫ = коллекция;
    }
    
    @Override
    public final Collection<T> узлы()
    {
        return УЗЛЫ;
    }
    
}
