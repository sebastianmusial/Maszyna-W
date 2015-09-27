package pl.polsl.dao;

import java.util.List;
import java.util.stream.Collectors;

import pl.polsl.storage.CommandStorage;
import pl.polsl.storage.CommandsListStorage;
import pl.polsl.storage.UserStorage;

/**
 * Implementation of Dao for coommands list's table.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class CommandsListDao {

	private Dao<CommandsListStorage> dao = new Dao<CommandsListStorage>();
	
	public List<CommandsListStorage> getAll() {
		return dao.getAll(CommandsListStorage.class);
	}
	
	public CommandsListStorage getById(Long id) {
		if(id == null)
			return null;
		for(CommandsListStorage commandsList : getAll()) {
			if(id == commandsList.getCommandListID())
				return commandsList;
		}
		return null;
	}
	
	public List<CommandsListStorage> getUserCommandsLists(Long userId) {
		List<CommandsListStorage> lists = dao.getAll(CommandsListStorage.class);
		for(CommandsListStorage list : lists) {
			UserStorage user = list.getUser();
			Long _userId = user.getUserID();
		}
		return lists.stream().filter((list) -> list.getUser().getUserID() == userId).collect(Collectors.toList());
	}
	
	public void save(CommandsListStorage u) {
		dao.save(u);
	}
	
	public void saveOrUpdate(CommandsListStorage u) {
		dao.saveOrUpdate(u);
	}
	
	public void delete(CommandsListStorage commandsList) {
		dao.delete(CommandsListStorage.class, commandsList.getCommandListID());
	}
	
	public void deleteCommand(CommandsListStorage commandsList, String commandName) {
		for(CommandStorage command : commandsList.getCommands()) {
			if (command.getCommandName().equals(commandName)) {
				CommandDao commandDao = new CommandDao();
				commandDao.delete(command);
				return;
			}
		}
	}
}
