package pl.polsl.runner;

import java.util.LinkedList;

public class CommandList extends LinkedList<Command> {
	
	public Integer getCodeByName(String name) {
		if(name == null || name.equals(""))
			return null;
		for(Command command : this) {
			if(name.equals(command.getName()))
				return command.getCode();
		}
		return null;
	}
	
	public String getNameByCode(Integer code) {
		if(code == null)
			return null;
		for(Command command : this) {
			if(code == command.getCode())
				return command.getName();
		}
		return null;
	}
}
