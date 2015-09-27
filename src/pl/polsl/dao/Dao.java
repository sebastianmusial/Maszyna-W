package pl.polsl.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pl.polsl.hibernate.DatabaseConnector;

/**
 * Dao class is used to implement access to database using DAO pattern.
 * @author Michal Rakoczy
 * @param <T> type from pl.polsl.storage package that is handled
 */
public class Dao<T> {

	/**
	 * Current Hibernate session.
	 */
	private Session session;

	/**
	 * Constructor - links dao session with Hibernate session
	 */
	public Dao() {
		this.session = DatabaseConnector.getInstance().openSession();
	}
	
	protected void finalize() throws Throwable {
		session.close();
	}

	/**
	 * Insert method.
	 * @param o object to insert
	 * @param <T> type from pl.polsl.storage package that is handled
	 */
	@SuppressWarnings("hiding")
	public <T> void save(final T o) {
		Transaction transaction = session.beginTransaction();
		session.persist(o);
		transaction.commit();
	}

	/**
	 * Insert or update method
	 * @param o object to insert or update
	 * @param <T> type from pl.polsl.storage package that is handled
	 */
	@SuppressWarnings("hiding")
	public <T> void saveOrUpdate(final T o) {
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(o);
		transaction.commit();
	}
	/**
	 * Delete method.
	 * @param type Entity type
	 * @param id id of object to be removed
	 */
	public void delete(final Class<T> type, Long id) {
		Transaction transaction = session.beginTransaction();
		Object persistentInstance = session.load(type, id);
	    if (persistentInstance != null) {
	        session.delete(persistentInstance);
	    }
		transaction.commit();
		
	}

	/**
	 * Select method.
 	 * @param type Entity class.
	 * @param id ID of selected object from database.
	 * @param <T> type from pl.polsl.storage package that is handled
	 * @return Selected object or null.
	 */
	@SuppressWarnings("hiding")
	public <T> T get(final Class<T> type, final Long id) {
		return (T) session.get(type, id);
	}

	/**
	 * Insert method
	 * @param o object to insert
	 * @param <T> type from pl.polsl.storage package that is handled
	 * @return Inserted object (with ID).
	 */
	@SuppressWarnings("hiding")
	public <T> T merge(final T o) {
		return (T) session.merge(o);
	}

	/**
	 * Select all data from table.
	 * @param type Entity type
	 * @param <T> type from pl.polsl.storage package that is handled
	 * @return List of objects or null.
	 */
	@SuppressWarnings("hiding")
	public <T> List<T> getAll(final Class<T> type) {
		final Criteria crit = session.createCriteria(type);
		return crit.list();
	}
}