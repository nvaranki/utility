package com.varankin.util;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Cascaded {@link ResourceBundle} for a package. 
 * It includes bundles, if any exist, for parent package(s). Same
 * key-value pair(s) can be stored in any shared parent package.
 * Resolution of a key begins from top level bundle down to 
 * common parent.
 * 
 * @author &copy; 2019 Николай Варанкин
 */
public final class ResourceBundleStack extends ResourceBundle
{
    private static final Logger LOGGER = Logger.getLogger( ResourceBundleStack.class.getName() );
  
    private final List<ResourceBundle> proxies;

    public ResourceBundleStack( Package pkg ) 
    {
        proxies = new LinkedList<>();
        for( String path = pkg.getName(); !path.isEmpty(); )
            try
            {
                proxies.add( ResourceBundle.getBundle( path + ".text" ) );
            }
            catch( MissingResourceException e )
            {
                LOGGER.log( Level.FINEST, "No resource bundle \"text\" found for the package {0}", path );
            }
            finally
            {
                int dot = path.lastIndexOf( '.' );
                path = dot < 0 ? "" : path.substring( 0, dot );
            }
        if( proxies.isEmpty() )
            throw new MissingResourceException( "No resource bundle for the package " + pkg.getName(), null, null );
    }

    @Override
    protected Object handleGetObject( String key ) 
    {
        for( ResourceBundle p : proxies )
            if( p.containsKey( key ) )
                return p.getObject( key );
        return null;
    }

    @Override
    public Enumeration<String> getKeys() 
    {
        Set<String> keys = new LinkedHashSet<>();
        for( int i = proxies.size() - 1; i >= 0; i-- )
            keys.addAll( proxies.get( i ).keySet() );
        return Collections.enumeration( keys );
    }
    
    @Override
    public String getBaseBundleName() 
    {
        return proxies.get( 0 ).getBaseBundleName();
    }

}
