package pl.polsl.dao;

import java.util.List;
import java.util.stream.Collectors;

import pl.polsl.storage.CommandStorage;
import pl.polsl.storage.CommandsListStorage;

public class CommandDao {

	private Dao<CommandStorage> dao = new Dao<CommandStorage>();
	
	public List<CommandStorage> getAll() {
		return dao.getAll(CommandStorage.class);
	}
	
	public CommandStorage getById(Long id) {
		if(id == null)
			return null;
		for(CommandStorage command : getAll()) {
			if(id == command.getCommandID())
				return command;
		}
		return null;
	}
	
	public void save(CommandStorage u) {
		dao.save(u);
	}
	
	public void delete(CommandStorage command) {
		dao.delete(CommandStorage.class, command.getCommandID());
	}
}
