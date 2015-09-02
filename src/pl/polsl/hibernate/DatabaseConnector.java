package pl.polsl.hibernate;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DatabaseConnector {
	private static DatabaseConnector instance;
	private static EntityManagerFactory emFactory;
	private static EntityManager em;

	public static DatabaseConnector getInstance() {
		if (instance == null) {
			instance = new DatabaseConnector();
		}
		return instance;
	}

	public void connect() {
		emFactory = Persistence.createEntityManagerFactory("WMachine");
		em = emFactory.createEntityManager();
	}
	
	public void beginTransaction() {
		em.getTransaction().begin();
	}
	
	public void endTransaction() {
		em.getTransaction().commit();
	}

	public void disconnect() {
		em.close();
		emFactory.close();
	}

	public  EntityManagerFactory getEmFactory() {
		return emFactory;
	}

	public  void setEmFactory(EntityManagerFactory emFactory) {
		DatabaseConnector.emFactory = emFactory;
	}

	public  EntityManager getEm() {
		return em;
	}

	public  void setEm(EntityManager em) {
		DatabaseConnector.em = em;
	}

	public DatabaseConnector() {
		connect();
	}
	
	public <T> void  mergeTransaction(T item) {
		em.merge(item);
	}
	
	public Query createQuery(String query) {
		return em.createQuery(query);		
	}
}
