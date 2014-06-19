package util;

import org.hibernate.*;
import org.hibernate.cfg.*;

public class HibernateUtil {
	/*
 	De sessionFactory wordt in de regel ŽŽn keer gemaakt en door de hele
 	applicatie gebruikt.
 	Deze factory is thread-safe en kan worden gedeeld. Een Session is een
 	single-threaded object. 	
 	*/
	
    private static final SessionFactory sessionFactory;

    static {
        try {
        	/*
        	hibernate zoekt in de classpath-root naar hibernate.cfg.xml        	 
        	als die gevonden wordt, worden alle hibernate.* eigenschappen
        	in het configuratie-object geladen.
        	*/
        	
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static void shutDown() {
    	getSessionFactory().close();
    }
 }
