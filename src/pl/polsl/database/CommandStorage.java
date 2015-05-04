package pl.polsl.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.*;

import pl.polsl.storage.StoredCommand;

public class CommandStorage {
	private Connection connection;
	
	private final String SELECT_COMMAND_LIST = "SELECT * FROM Commands WHERE commandListId = ? ORDER BY indexInCommandList";
	
	private final String SELECT_COMMAND = "SELECT commandName, commandDefinition FROM Commands WHERE commandListId = ? AND indexInCommandList = ?";
	
	private final String SELECT_DEFINITION = "SELECT commandDefinition FROM Commands WHERE commandListId = ? AND indexInCommandList = ?";
	
	private Session session;
	
	public CommandStorage() {
		connection = DatabaseConnection.getInstance();
	}
	
	@SuppressWarnings("unchecked")
	public List<StoredCommand> readCommandList(Integer listId) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction txn = session.beginTransaction();
		List<StoredCommand> commandList = session.createCriteria(StoredCommand.class)
												 .add(Restrictions.eq("commandListId", listId))
												 .list();
		txn.commit();
		return commandList;
	}

	public StoredCommand readCommand(Integer listId, Integer commandIndex) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction txn = session.beginTransaction();
		StoredCommand command = (StoredCommand)session.createCriteria(StoredCommand.class)
													  .add(Restrictions.eq("commandListId", listId))
													  .add(Restrictions.eq("indexInCommandList", commandIndex))
													  .uniqueResult();
		txn.commit();
		return command;
	}
}
