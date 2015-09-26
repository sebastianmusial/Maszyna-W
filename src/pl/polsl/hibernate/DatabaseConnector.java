package pl.polsl.hibernate;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class DatabaseConnector {
	private static DatabaseConnector instance;
	private static Configuration configuration;
	private static StandardServiceRegistryBuilder ssrb;
	private static SessionFactory sessionFactory;
	private static Session session;
	private static Transaction transaction;

	public static DatabaseConnector getInstance() {
		if (instance == null) {
			instance = new DatabaseConnector();
		}
		return instance;
	}

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


	private DatabaseConnector() {
		connect();
	}

	public Query createQuery(String query) {	
		return session.createQuery(query);
	}
}
