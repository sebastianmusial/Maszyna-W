package pl.polsl.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pl.polsl.hibernate.DatabaseConnector;

public class Dao<T> {

	private Session session;
	private Transaction transaction;

	public Dao() {
		this.session = DatabaseConnector.getInstance().getSession();
	}

	@SuppressWarnings("hiding")
	public <T> void save(final T o) {
		Transaction transaction = session.beginTransaction();
		session.save(o);
		transaction.commit();
	}

	@SuppressWarnings("hiding")
	public <T> void saveOrUpdate(final T o) {
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(o);
		transaction.commit();
	}

	public void delete(final Object object) {
		Transaction transaction = session.beginTransaction();
		session.delete(object);
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