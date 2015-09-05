package pl.polsl.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pl.polsl.hibernate.DatabaseConnector;

public class Dao<T> {

	private Session session;

	public Dao() {
		this.session = DatabaseConnector.getInstance().getSession();
	}

	public <T> void save(final T o) {
		Transaction transaction = session.beginTransaction();
		session.save(o);
		transaction.commit();
	}

	public <T> void saveOrUpdate(final T o) {
		session.saveOrUpdate(o);
	}

	public void delete(final Object object) {
		session.delete(object);
	}

	public <T> T get(final Class<T> type, final Long id) {
		return (T) session.get(type, id);
	}

	public <T> T merge(final T o) {
		return (T) session.merge(o);
	}

	public <T> List<T> getAll(final Class<T> type) {
		final Criteria crit = session.createCriteria(type);
		return crit.list();
	}
}