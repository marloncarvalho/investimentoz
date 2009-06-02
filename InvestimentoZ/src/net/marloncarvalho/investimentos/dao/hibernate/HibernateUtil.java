package net.marloncarvalho.investimentos.dao.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * Classe utilitária para utilização do hibernate.
 * 
 * @author Marlon S. Carvalho
 * @since 01/06/2009
 */
final public class HibernateUtil {
	    private static final SessionFactory sessionFactory;

	    static {
	        try {
	            // Create the SessionFactory from hibernate.cfg.xml
	        	AnnotationConfiguration cfg = new AnnotationConfiguration();
	            sessionFactory = cfg.configure().buildSessionFactory();
	            //new SchemaExport(cfg).create(true, true);
	        } catch (Throwable ex) {
	            ex.printStackTrace();
	            throw new ExceptionInInitializerError(ex);
	        }
	    }

	    public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }

}