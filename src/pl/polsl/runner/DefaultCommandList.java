package pl.polsl.runner;

public class DefaultCommandList extends CommandList {

	// public DefaultCommandList(Language) {
	public DefaultCommandList() {
		add(new Command(0, "stp"));
		add(new Command(1, "dod"));
		add(new Command(2, "ode"));
		add(new Command(3, "pob"));
		add(new Command(4, "ład"));
		add(new Command(5, "sob"));
		add(new Command(6, "som"));
		add(new Command(7, "soz"));
		
//		for(DefaultCommand command : DefaultCommand.values()) {
//			add(new Command(command.CODE, language.getCommandName(command)));
//		}
	}
	
	@Override
	public String getNameByCode(Integer code) {
		if(code < 0 || code >= 8)
			return null;
		return get(code).getName();
	}
	
}
