package pl.polsl.storage.dao;

import java.util.List;

import javax.persistence.Query;

import pl.polsl.hibernate.DatabaseConnector;
import pl.polsl.storage.CommandsListStorage;
import pl.polsl.storage.UserStorage;

public class CommandsListDAO {

	public static List<CommandsListStorage> getByUser(UserStorage u) {
		Query q = DatabaseConnector.getInstance().getEm()
				.createNamedQuery("CommandsListStorage.findByUserIfPublic");
		q.setParameter("user", u);
		List<CommandsListStorage> list = q.getResultList();

		if (list.isEmpty()) {
			return null;
		} else {
			return list;
		}
	}

	public static CommandsListStorage getStandardList() {
		Query q = DatabaseConnector.getInstance().getEm()
				.createNamedQuery("CommandsListStorage.getStandardList");
		return (CommandsListStorage) q.getResultList().get(0);
	}
}
