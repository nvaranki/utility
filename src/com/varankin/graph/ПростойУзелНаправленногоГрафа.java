package com.varankin.graph;

import java.util.Arrays;
import java.util.Collection;

/**
 * Узел направленного графа. Состоит из двух компонентных 
 * узлов. вместе составляющих данный узел. Эти узлы служат 
 * для раздельного связывания входящих и выходящих ребер графа.
 *
 * @param <T> тип компонентных узлов графа.
 * 
 * @author &copy; 2014 Николай Варанкин
 */
public class ПростойУзелНаправленногоГрафа<T extends Узел> implements УзелНаправленногоГрафа<T>
{
    private final КомпонентныйУзел<T> ВХОД, ВЫХОД;

    public ПростойУзелНаправленногоГрафа( КомпонентныйУзел<T> вход, КомпонентныйУзел<T> выход )
    {
        ВХОД  = вход;
        ВЫХОД = выход;
    }
    
    @Override
    public final КомпонентныйУзел<T> вход()
    {
        return ВХОД;
    }

    @Override
    public final КомпонентныйУзел<T> выход()
    {
        return ВЫХОД;
    }

    @Override
    public Collection<КомпонентныйУзел<T>> узлы()
    {
        return Arrays.asList( ВХОД, ВЫХОД );
    }
    
}
