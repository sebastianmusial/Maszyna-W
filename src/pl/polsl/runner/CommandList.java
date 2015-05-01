package pl.polsl.runner;

import java.util.LinkedList;

import pl.polsl.runner.command.Command;

public class CommandList extends LinkedList<Command> {
	
	public Command getCommandByName(String name) {
		if(name == null || name.equals(""))
			return null;
		for(Command command : this) {
			if(name.equals(command.getName()))
				return command;
		}
		return null;
	}
	
	public Integer getCodeByName(String name) {
		if(name == null || name.equals(""))
			return null;
		for(int code = 0; code < size(); ++code) {
			if(name.equals(get(code).getName()))
				return code;
		}
		return null;
	}
	
	public String getNameByCode(Integer code) {
		if(code == null || code < 0 || code >= size())
			return null;
		return get(code).getName();
	}
}
