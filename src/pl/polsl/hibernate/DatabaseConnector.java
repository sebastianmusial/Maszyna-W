package pl.polsl.hibernate;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * This class is used for managing connection with database (connect, disconnect, creating session).
 * @author Michal Rakoczy
 *
 */
public class DatabaseConnector {
	
	/**
	 * Singleton field - only one copy of DatabaseConnector can be created
	 */
	private static DatabaseConnector instance;
	
	/**
	 * Holds configuration form hibernate.cfg.xml file.
	 */
	private static Configuration configuration;
	
	/**
	 * Builder for standard ServiceRegistry instances.
	 */
	private static StandardServiceRegistryBuilder ssrb;
	
	/**
	 * Build hibernate session
	 */
	private static SessionFactory sessionFactory;
	/**
	 * Hibernate session (connection with database).
	 */
	private static Session session;
	/**
	 * Session transaction.
	 */
	private static Transaction transaction;

	
	/**
	 * Singleton method - only one copy of DatabaseConnector can be created
	 * @return instance
	 */
	public static DatabaseConnector getInstance() {
		if (instance == null) {
			instance = new DatabaseConnector();
		}
		return instance;
	}

	/**
	 * Creates connection to database.
	 */
	private void connect() {
		configuration = new Configuration();
    	configuration.configure();
    	ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(ssrb.build());
        session = sessionFactory.openSession();
	}	
	
	public Session getSession() {
		return session;
	}
	
	public Session openSession() {
		return sessionFactory.openSession();
	}

	public static void setSession(Session session) {
		DatabaseConnector.session = session;
	}

	public void beginTransaction() {
		transaction = session.beginTransaction();
	}
	
	public void endTransaction() {
		transaction.commit();
	}

	public void disconnect() {
		session.close();
	}

	/**
	 * Constructor
	 */
	private DatabaseConnector() {
		connect();
	}

//	public Query createQuery(String query) {	
//		return session.createQuery(query);
//	}
}
