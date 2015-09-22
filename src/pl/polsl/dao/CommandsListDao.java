package pl.polsl.dao;

import java.util.List;

import pl.polsl.storage.CommandsListStorage;

public class CommandsListDao {

	private Dao<CommandsListStorage> dao = new Dao<CommandsListStorage>();
	
	public List<CommandsListStorage> getAll() {
		return dao.getAll(CommandsListStorage.class);
	}
	
	public CommandsListStorage getById(Integer id) {
		if(id == null)
			return null;
		for(CommandsListStorage commandsList : getAll()) {
			if(id == commandsList.getCommandListID())
				return commandsList;
		}
		return null;
	}
	
	public void save(CommandsListStorage u) {
		dao.save(u);
	}
	
}
