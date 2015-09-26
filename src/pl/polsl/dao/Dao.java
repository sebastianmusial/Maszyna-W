package pl.polsl.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pl.polsl.hibernate.DatabaseConnector;

public class Dao<T> {

	private Session session;

	public Dao() {
		this.session = DatabaseConnector.getInstance().openSession();
	}
	
	protected void finalize() throws Throwable {
		session.close();
	}

	@SuppressWarnings("hiding")
	public <T> void save(final T o) {
		Transaction transaction = session.beginTransaction();
		session.persist(o);
		transaction.commit();
	}

	@SuppressWarnings("hiding")
	public <T> void saveOrUpdate(final T o) {
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(o);
		transaction.commit();
	}

	public void delete(final Class<T> type, Long id) {
		Transaction transaction = session.beginTransaction();
		Object persistentInstance = session.load(type, id);
	    if (persistentInstance != null) {
	        session.delete(persistentInstance);
	    }
		transaction.commit();
		
	}

	@SuppressWarnings("hiding")
	public <T> T get(final Class<T> type, final Long id) {
		return (T) session.get(type, id);
	}

	@SuppressWarnings("hiding")
	public <T> T merge(final T o) {
		return (T) session.merge(o);
	}

	@SuppressWarnings("hiding")
	public <T> List<T> getAll(final Class<T> type) {
		final Criteria crit = session.createCriteria(type);
		return crit.list();
	}
}