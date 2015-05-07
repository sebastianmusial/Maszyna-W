package pl.polsl.storage.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import pl.polsl.database.HibernateUtil;
import pl.polsl.hibernate.DatabaseConnector;
import pl.polsl.storage.CommandStorage;
import pl.polsl.storage.CommandsListStorage;
import pl.polsl.storage.UserStorage;

public class CommandsListDAO {
	private static Session session;
	
	public static List<CommandsListStorage> getByUser(UserStorage u) {
//		Query q = DatabaseConnector.getInstance().getEm()
//				.createNamedQuery("CommandsListStorage.findByUserIfPublic");
//		q.setParameter("user", u);
//		List<CommandsListStorage> list = q.getResultList();
//
//		if (list.isEmpty()) {
//			return null;
//		} else {
//			return list;
//		}
		return null;
	}

	public static List<CommandStorage> getStandardList() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try
        {
            //Update record using named query
            Query query = session.getNamedQuery("CommandsListStorage.getStandardList");
            CommandsListStorage commandList = (CommandsListStorage)query.uniqueResult();
            List<CommandStorage> commands = commandList.getCommands();
            String code = commands.get(0).getCommandCode();
            System.out.println(code);
            return commands;
        }
        finally
        {
            session.getTransaction().commit();
        }
		
		
//		Transaction txn = session.beginTransaction();
//		CommandsListStorage commandList = (CommandsListStorage)session.createCriteria(CommandsListStorage.class)
//												 .add(Restrictions.eq("user", "admin"))
//												 .uniqueResult();
//		txn.commit();
//		return commandList;
//		
//		Query q = DatabaseConnector.getInstance().getEm()
//				.createNamedQuery();
//		return (CommandsListStorage) q.getResultList().get(0);
	}
}
