package com.varankin.characteristic;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * Абстрактный каталог свойств объекта.
 * 
 * @author &copy; 2015 Николай Варанкин
 */
public abstract class АбстрактныйКаталогСвойств implements Свойственный.Каталог
{
    private final Map<Свойство<?>, Class> КЛАССЫ;
    private final Map<Свойство<?>, String> КЛЮЧИ;

    /**
     * @param классы хранилище карты классов.
     * @param ключи  хранилище карты ключей.
     */
    protected АбстрактныйКаталогСвойств( Map классы, Map ключи )
    {
        КЛАССЫ = классы;
        КЛЮЧИ = ключи;
    }
    
    /**
     * Загружает свойства в каталог.
     * 
     * @param контейнер контейнер свойств.
     */
    protected void load( Свойственный контейнер )
    {
        for( Class cls = контейнер.getClass(); cls != null; cls = cls.getSuperclass() )
            for( Field field : cls.getDeclaredFields() )
            {
                Свойственное аннотация = field.getAnnotation( Свойственное.class );
                if( аннотация != null )
                {
                    String ключ = аннотация.value();
                    try
                    {
                        Свойство свойство = getField( field, контейнер );
                        КЛАССЫ.put( свойство, getGenericClass( field ) );
                        КЛЮЧИ.put( свойство, ключ );
                    }
                    catch( Exception e )
                    {
                        throw new RuntimeException( "Inappropriate property " + ключ, e );
                    }
                }
            }
    }
    
    /**
     * Извлекает объект, реализующий {@link Свойство}, 
     * из владеющего {@linkplain Свойственный контейнера}.
     * 
     * @param field   поле контейнера.
     * @param элемент владеющий контейнер.
     * @return свойство.
     * @throws IllegalAccessException
     * @throws IllegalArgumentException 
     */
    protected abstract Свойство getField( Field field, Свойственный элемент ) 
            throws IllegalAccessException, IllegalArgumentException;

    /**
     * Извлекает класс объектов, хранимых в {@link Свойство свойстве}.
     * 
     * @param field поле контейнера.
     * @return класс.
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException 
     */
    protected Class getGenericClass( Field field ) 
            throws NoSuchMethodException, IllegalAccessException, 
                IllegalArgumentException, InvocationTargetException
    {
        Type genericType = field.getGenericType();
        if( genericType != null )
        {
            // hack sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl
            Method method = genericType.getClass().getMethod( "getActualTypeArguments" );
            Object types = method.invoke( genericType );
            Type[] ta = (Type[])types;
            if( ta.length != 1 )
                throw new IllegalArgumentException( "No single generic type but " + ta.length );
            else 
                return (Class)ta[0];
        }      
        return Object.class;
    }
    
    @Override
    public Collection<Свойство<?>> перечень()
    {
        return Collections.unmodifiableCollection( КЛАССЫ.keySet() );
    }

    @Override
    public Свойство<?> свойство( String ключ )
    {
        return КЛЮЧИ.entrySet().stream()
                .filter( (e) -> e.getValue().equals( ключ ) )
                .map( (e) -> e.getKey() )
                .findAny().orElse( null );
    }

    @Override
    public String ключ( Свойство свойство )
    {
        return КЛЮЧИ.get( свойство );
    }

    @Override
    public Class класс( Свойство свойство )
    {
        Class cls = КЛАССЫ.get( свойство );
        return cls != null ? cls : Object.class;
    }

}
