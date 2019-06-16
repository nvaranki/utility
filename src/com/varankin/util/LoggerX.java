package com.varankin.util;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.*;

/**
 * Немного более удобный Logger на основе {@linkplain Logger стандартного}.
 * 
 * @author &copy; 2019 Николай Варанкин
 */
public class LoggerX
{
    private final java.util.logging.Logger STD_LOGGER;
    
    private LoggerX( String name, String resourceBundleName )
    {
        //super( name, resourceBundleName );
        STD_LOGGER = java.util.logging.Logger.getLogger( name, resourceBundleName );
    }
    
    public final java.util.logging.Logger getLogger()
    {
        return STD_LOGGER;
    }
    
    public static LoggerX getLogger( Class<?> класс ) 
    {
        LoggerX lx = new LoggerX( класс.getName(), класс.getPackage().getName() + ".text" );
        lx.STD_LOGGER.setResourceBundle( new ResourceBundleStack( класс.getPackage() ));
        return lx;
    }

    public void log( String msg, Object... params )
    {
        STD_LOGGER.log( levelAsLastChar( msg ), msg, params );
    }
    
    public void log( Level level, String msg, Object... params )
    {
        STD_LOGGER.log( level, msg, params );
    }
    
    private Level levelAsLastChar( String msg )
    {
        char c = msg != null && msg.length() > 0 ? msg.charAt( msg.length() - 1 ) : 'a';
        Level value = LEVELS.get( c );
        return value != null ? value : Level.ALL;
    }
    
    public String text( String msg, Object... params )
    {
        String result;
        ResourceBundle resourceBundle = STD_LOGGER.getResourceBundle();
        
        if( msg == null )
        {
            StringBuilder sb = new StringBuilder();
            for( Object param : params )
                if( param == null )
                    sb.append( " null" );
                else
                    sb.append( ' ' ).append( param.toString() );
            if( sb.length() > 0 ) sb.replace( 0, 1, "" );
            result = sb.toString();
        }
        else if( resourceBundle == null )
        {
            result = MessageFormat.format( msg, params ); //TODO cache of MessageFormat for each msg
        }
        else if( resourceBundle.containsKey( msg ) )
        {
            if( params.length == 0 )
                result = resourceBundle.getString( msg );
            else
                result = MessageFormat.format( resourceBundle.getString( msg ), params );
        }
        else
        {
            result = MessageFormat.format( msg, params );
        }
        return result;
    }
    
    private final static Map<Character,Level> LEVELS;
    static
    {
        LEVELS = new HashMap<>();
        LEVELS.put( 'a', Level.ALL );
        LEVELS.put( 'A', Level.ALL );
        LEVELS.put( 'i', Level.INFO );
        LEVELS.put( 'I', Level.INFO );
        LEVELS.put( 'w', Level.WARNING );
        LEVELS.put( 'W', Level.WARNING );
        LEVELS.put( 's', Level.SEVERE );
        LEVELS.put( 'S', Level.SEVERE );
        LEVELS.put( 'c', Level.CONFIG );
        LEVELS.put( 'C', Level.CONFIG );
        LEVELS.put( 'f', Level.FINE );
        LEVELS.put( 'F', Level.FINE );
        LEVELS.put( 'r', Level.FINER );
        LEVELS.put( 'R', Level.FINER );
        LEVELS.put( 't', Level.FINEST );
        LEVELS.put( 'T', Level.FINEST );
    }
}
