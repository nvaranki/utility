package com.varankin.graph;

import java.util.Collection;

/**
 * Узел ненаправленного графа.
 *
 * @param <T> тип узлов графа.
 * 
 * @author &copy; 2014 Николай Варанкин
 */
public interface Узел<T extends Узел>
{
    /**
     * @return узлы, связанные с данным узлом.
     */
    Collection<T> узлы();
}
