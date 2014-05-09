package com.varankin.characteristic;

import java.util.Map;

/**
 * Владелец одного или нескольких именованных свойств.
 * 
 * @author &copy; 2014 Николай Варанкин
 */
public interface Свойственный
{
    /**
     * @return каталог свойств объекта.
     */
    Map<Object,Свойство> свойства();
}
