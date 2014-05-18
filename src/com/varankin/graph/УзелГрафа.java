package com.varankin.graph;

import java.util.Collection;

/**
 * Узел неориентированного графа.
 *
 * @param <T> тип узлов графа.
 * 
 * @author &copy; 2014 Николай Варанкин
 */
public interface УзелГрафа<T extends УзелГрафа<T>>
{
    /**
     * @return узлы, связанные с данным узлом.
     */
    Collection<T> узлы();
}
