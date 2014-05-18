package com.varankin.graph;

/**
 * Узел ориентированного графа. Состоит из двух компонентных 
 * узлов, вместе составляющих данный узел. Эти узлы служат 
 * для раздельного связывания входящих и выходящих ребер графа.
 *
 * @param <T> тип компонентных узлов графа.
 * 
 * @author &copy; 2014 Николай Варанкин
 */
public interface УзелОрграфа<T extends УзелОрграфа.Компонент<T>> 
{

    /**
     * @return компонентный узел для входящих ребер графа.
     */
    T вход();

    /**
     * @return компонентный узел для выходящих ребер графа.
     */
    T выход();
    
    /**
     * Компонентный узел графа в составе {@link УзелОрграфа}. 
     * 
     * @param <T> тип компонентных узлов графа.
     */
    public interface Компонент<T extends Компонент<T>> extends УзелГрафа<T>
    {
        /**
         * @return агрегатный узел, в составе которого находится данный компонентный узел.
         */
        УзелОрграфа<T> агрегат();
    }
    
}
