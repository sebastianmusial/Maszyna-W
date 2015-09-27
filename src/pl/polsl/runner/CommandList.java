package pl.polsl.runner;

import java.util.LinkedList;
import java.util.List;

import pl.polsl.parser.CommandParser;
import pl.polsl.runner.command.Command;
import pl.polsl.storage.CommandStorage;
import pl.polsl.storage.CommandsListStorage;

/**
 * Commands list used by WMachine instance.
 * @author Tomasz Rzepka
 *
 */
public class CommandList extends LinkedList<Command> {
	
	/**
	 * Parse commands that came from database.
	 * @param commandsList data from DB
	 */
	public void parse(CommandsListStorage commandsList) {
		CommandParser parser = new CommandParser();
		if(commandsList != null) {
			List<CommandStorage> commands = commandsList.getCommands();
			for(CommandStorage command : commands) {
				add(parser.parse(command.getCommandCode()));
			}
		}
	}
	
	/**
	 * Get command by its name.
	 * @param name command name
	 * @return Command with given name or null.
	 */
	public Command getCommandByName(String name) {
		if(name == null || name.equals(""))
			return null;
		for(Command command : this) {
			if(name.equals(command.getName()))
				return command;
		}
		return null;
	}
	
	/**
	 * Get command index in list by its name.
	 * @param name command name
	 * @return Index of command with given name or null.
	 */
	public Integer getCodeByName(String name) {
		if(name == null || name.equals(""))
			return null;
		for(int code = 0; code < size(); ++code) {
			if(name.equals(get(code).getName()))
				return code;
		}
		return null;
	}
	
	/**
	 * Get command by its index in list.
	 * @param code command index
	 * @return Command with given index or null.
	 */
	public String getNameByCode(Integer code) {
		if(code == null || code < 0 || code >= size())
			return null;
		return get(code).getName();
	}
}
