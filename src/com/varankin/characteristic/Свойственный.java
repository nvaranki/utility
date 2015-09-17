package com.varankin.characteristic;

import java.util.Map;

/**
 * Владелец одного или нескольких именованных свойств.
 * 
 * @param <K> тип квалификатора свойств.
 * 
 * @author &copy; 2015 Николай Варанкин
 */
public interface Свойственный<K>
{
    /**
     * @return каталог свойств объекта.
     */
    Map<K,Свойство> свойства();
}
