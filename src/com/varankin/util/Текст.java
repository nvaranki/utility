package com.varankin.util;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.logging.*;

/**
 * Хранилище локализованных текстов.
 *
 * @author &copy; 2012 Николай Варанкин
 */
public class Текст 
{
    static private final Logger LOGGER = Logger.getLogger( Текст.class.getName() );
    static private final String FILE_BUNDLE = "text";

    static public  final КаталогКлассов КЛАССЫ = new КаталогКлассов();
    static public  final КаталогПакетов ПАКЕТЫ = new КаталогПакетов();

    private final Locale специфика;
    private final ResourceBundle тексты;
    private final String префикс;

    private Текст( Locale специфика, ResourceBundle тексты, String префикс )
    {
        this.специфика = специфика;
        this.тексты = тексты;
        this.префикс = префикс != null && префикс.length() > 0 ? префикс + '.' : "";
    }
    
    public final String текст( String ссылка, Object... args )
    {
        try
        {
            final String формат = тексты.getString( префикс + ссылка );
            return args != null && args.length > 0 ? String.format( специфика, формат, args ) : формат;
        }
        catch( MissingResourceException ex )
        {
            LOGGER.log( Level.CONFIG, "Missing resource bundle key \"{0}:{1}{2}\":\n{3}", 
                    new Object[]{ "", префикс, ссылка, ex.getLocalizedMessage() } ); //TODO пакет.getName()
            return "";
        }
    }
    
    public final ResourceBundle getBundle()
    {
        return тексты;
    }

    static private ResourceBundle getBundle( String name, Locale специфика )
    {
        try
        {
            return ResourceBundle.getBundle( name, специфика );
        }
        catch( MissingResourceException ex )
        {
            LOGGER.log( Level.CONFIG, "Resource was not found for ID \"{0}\" and locale \"{1}\":\n\t{2}",
                    new Object[]{ name, специфика, ex.getLocalizedMessage() } );
            Locale DEFAULT_LOCALE = Locale.getDefault();
            return DEFAULT_LOCALE.equals( специфика ) ? NO_RESOURCE_BUNDLE : getBundle( name, DEFAULT_LOCALE );
        }
    }

    static private ResourceBundle NO_RESOURCE_BUNDLE = new ListResourceBundle()
    {
        private final Object[][] contents = new Object[][]{};

        @Override
        protected Object[][] getContents()
        {
            return contents;
        }
    };

    @Deprecated
    static private Map<Locale.Category, Locale> singletonIndex( Locale специфика )
    {
        Map<Locale.Category,Locale> индекс = new HashMap<>();
        for( Locale.Category category : Locale.Category.values() )
            индекс.put( category, специфика );
        индекс.put( null, специфика );
        return индекс;
    }

    //<editor-fold defaultstate="collapsed" desc="классы">
    
    /**
     * Фабрика источников текста.
     */
    static public class КаталогКлассов
    {
        private final Map<Class,ResourceBundle> каталог
                = Collections.synchronizedMap( new HashMap<Class,ResourceBundle>() );
        
        /**
         * Создает новый источник локализованных текстов для класса.
         * Источник требует полный индекс текста.
         *
         * @param класс     референт.
         * @param специфика спецификации языка текстов, форматов чисел, дат и т.п.
         * @return источник текстов.
         *
         * @exception NullPointerException если спецификаЯзыка - {@code null}.
         * 
         * @since JDK7
         * @see Locale#getDefault()
         * @see Locale#getDefault(java.util.Locale.Category)
         */
        public Текст словарь( Class класс, Map<Locale.Category,Locale> специфика )
        {
            ResourceBundle bundle = каталог.get( класс );
            if( bundle == null )
                каталог.put( класс, bundle = getBundle( класс.getName(), 
                        специфика.get( Locale.Category.DISPLAY ) ) );
            return new Текст( специфика.get( Locale.Category.FORMAT ), bundle, null );
        }
        
        /**
         * Создает новый источник локализованных текстов для класса.
         * Источник требует полный индекс текста.
         *
         * @param класс     референт.
         * @param специфика спецификация языка текстов и форматов чисел, дат, валют и т.п..
         * @return источник текстов.
         *
         * @exception NullPointerException если специфика - {@code null}.
         * 
         * @see Locale#getDefault()
         * @deprecated JDK7.
         */
        @Deprecated
        public Текст словарь( Class класс, Locale специфика )
        {
            return словарь( класс, singletonIndex( специфика ) );
        }
    }
    
    /**
     * Фабрика источников текста.
     */
    static public class КаталогПакетов
    {
        private final Map<Package,ResourceBundle> каталог
                = Collections.synchronizedMap( new HashMap<Package,ResourceBundle>() );
        
        /**
         * Создает новый источник локализованных текстов для класса.
         * Источник требует частичный индекс текста. В качестве префикса
         * источник использует {@linkplain Class#getSimpleName() краткое название класса}.
         *
         * @param класс     референт.
         * @param специфика спецификации языка текстов, форматов чисел, дат и т.п.
         * @return источник текстов.
         *
         * @exception NullPointerException если спецификаЯзыка - {@code null}.
         * 
         * @since JDK7
         * @see Locale#getDefault()
         * @see Locale#getDefault(java.util.Locale.Category)
         */
        public Текст словарь( Class класс, Map<Locale.Category,Locale> специфика )
        {
            return словарь( класс.getPackage(), префикс( класс ), специфика );
        }
        
        /**
         * Создает новый источник локализованных текстов для класса.
         * Источник требует частичный индекс текста. В качестве префикса
         * источник использует {@linkplain Class#getSimpleName() краткое название класса}.
         *
         * @param класс     референт.
         * @param специфика спецификация языка текстов и форматов чисел, дат, валют и т.п..
         * @return источник текстов.
         *
         * @exception NullPointerException если специфика - {@code null}.
         * 
         * @see Locale#getDefault()
         * @deprecated JDK7.
         */
        @Deprecated
        public Текст словарь( Class класс, Locale специфика )
        {
            return словарь( класс, singletonIndex( специфика ) );
        }
        
        /**
         * Создает новый источник локализованных текстов для пакета.
         * Источник требует частичный индекс текста. В качестве префикса
         * источник использует заданное значение.
         *
         * @param пакет     референт.
         * @param префикс   префикс ключей текстов.
         * @param специфика спецификации языка текстов, форматов чисел, дат и т.п.
         * @return источник текстов.
         *
         * @exception NullPointerException если спецификаЯзыка - {@code null}.
         * 
         * @since JDK7
         * @see Locale#getDefault()
         * @see Locale#getDefault(java.util.Locale.Category)
         */
        public Текст словарь( Package пакет, String префикс, Map<Locale.Category,Locale> специфика )
        {
            ResourceBundle bundle = каталог.get( пакет );
            if( bundle == null )
                каталог.put( пакет, bundle = getBundle( пакет.getName() + '.' + FILE_BUNDLE, 
                        специфика.get( Locale.Category.DISPLAY ) ) );
            return new Текст( специфика.get( Locale.Category.FORMAT ), bundle, префикс );
        }
        
        /**
         * Создает новый источник локализованных текстов для пакета.
         * Источник требует частичный индекс текста. В качестве префикса
         * источник использует заданное значение.
         * 
         * @param пакет     референт.
         * @param префикс   префикс ключей текстов.
         * @param специфика спецификация языка текстов и форматов чисел, дат, валют и т.п..
         * @return источник текстов.
         *
         * @exception NullPointerException если специфика - {@code null}.
         * 
         * @see Locale#getDefault()
         * @deprecated JDK7.
         */
        @Deprecated
        public Текст словарь( Package пакет, String префикс, Locale специфика )
        {
            return словарь( пакет, префикс, singletonIndex( специфика ) );
        }

        static private String префикс( Class<?> класс )
        {
            String name = класс.getSimpleName();
            for( Annotation a : класс.getAnnotations() )
                if( a instanceof Preference )
                    name = ((Preference)a).alias();
            return name;
        }
        
    }
    
    //</editor-fold>
}
