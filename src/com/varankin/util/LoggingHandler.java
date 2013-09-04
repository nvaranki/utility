package com.varankin.util;

import java.text.*;
import java.util.ResourceBundle;
import java.util.logging.*;

/**
 * Агент печати сообщений на {@linkplain Appendable консоли}.
 *
 * @author &copy; 2013 Николай Варанкин
 */
public class LoggingHandler extends Handler
{
    private final Appendable консоль;
    private volatile boolean alive;

    /**
     * @param консоль модель документа.
     */
    public LoggingHandler( Appendable консоль )
    {
        this.консоль = консоль;
        this.alive = true;
    }
    
    @Override
    public void publish( LogRecord record )
    {
        if( alive && isLoggable( record ) )
        {
            String message = record.getMessage();
            ResourceBundle rb = record.getResourceBundle();
            if( rb != null && rb.containsKey( message ) )
                message = rb.getString( message );
            
            Object[] parameters = record.getParameters();
            String текст = parameters != null && parameters.length > 0 ? 
                    MessageFormat.format( message, parameters ) : message;
            
            консоль.append( текст + '\n', record.getLevel() );
            publish( record.getThrown() );
        }
    }
    
    private void publish( Throwable throwable )
    {
        if( throwable != null )
        {
            String текст = throwable.getClass().getSimpleName() + ": " + throwable.getLocalizedMessage();
            консоль.append( текст + '\n', Level.SEVERE );
            Throwable cause = throwable.getCause();
            if( cause != null && cause != throwable ) publish( cause );
        }
    }

    @Override
    public void flush()
    {
    }

    @Override
    public void close() throws SecurityException
    {
        alive = false;
    }
    
    public interface Appendable
    {
        void append( String текст, Level level );
    }

}
