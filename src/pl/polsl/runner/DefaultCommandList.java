package pl.polsl.runner;

import java.util.List;

import pl.polsl.dao.CommandsListDao;
import pl.polsl.parser.CommandParser;
import pl.polsl.runner.command.Command;
import pl.polsl.storage.CommandStorage;
import pl.polsl.storage.CommandsListStorage;

/**
 * Default commands list. If database is available
 * it is read from the database otherwise
 * is is created in constructor.
 * @author Tomasz Rzepka
 *
 */
public class DefaultCommandList extends CommandList {

	public DefaultCommandList() {
		CommandParser parser = new CommandParser();
		CommandsListDao dao = new CommandsListDao();
		CommandsListStorage def = dao.getById(1L);
		if(def != null) {
			parse(def);
		}
		else {
			String[] commands =  {
				STP_COMMAND,
				ADD_COMMAND,
				SUB_COMMAND,
				LD_COMMAND,
				ST_COMMAND,
				JMP_COMMAND,
				JMPN_COMMAND,
				JMPZ_COMMAND
			};
			for(String command : commands) {
				add(parser.parse(command));
			}
		}
	}

	private final String STP_COMMAND =
			"// zakończenie programu\n" +
			"COMMAND SToP;\n" +
			"ARGUMENTS 0;\n" +
			"czyt wys wei il;\n" +
			"stop;";
	
	private final String ADD_COMMAND =
			"// (Ak)+((Ad))->Ak\n" +
			"COMMAND ADD;\n" +
			"czyt wys wei il;\n" +
			"wyad wea;\n" +
			"czyt wys weja dod weak wyl wea;";
	
	private final String SUB_COMMAND =
			"// (Ak)-((Ad))->Ak\n" +
			"COMMAND SUB;\n" +
			"czyt wys wei il;\n" +
			"wyad wea;\n" +
			"czyt wys weja ode weak wyl wea;";

	private final String LD_COMMAND =
			"// ((Ad))->Ak\n" +
			"COMMAND LD;\n" +
			"czyt wys wei il;\n" +
			"wyad wea;\n" +
			"czyt wys weja przep weak wyl wea;";

	private final String ST_COMMAND =
			"// ((Ad))->Ak\n" +
			"COMMAND ST;\n" +
			"czyt wys wei il;\n" +
			"wyad wea wyak wes;\n" +
			"pisz wyl wea;";
	
	private final String JMP_COMMAND =
			"// skok bezwarunkowy\n" +
			"COMMAND JMP;\n" +
			"czyt wys wei il;\n" +
			"wyad wea wel;\n";
	
	private final String JMPN_COMMAND =
			"// skok gdy (AK) < 0\n" +
			"COMMAND JMPN;\n" +
			"czyt wys wei il;\n" +
			"IF SIGN THEN @ujemne ELSE @dodatnie;\n" +
			"@ujemne wyad wea wel END;\n" +
			"@dodatnie wyl wea;";
	
	private final String JMPZ_COMMAND =
			"// skok gdy (AK) < 0\n" +
			"COMMAND JMPZ;\n" +
			"czyt wys wei il;\n" +
			"IF ZERO THEN @zero ELSE @niezero;\n" +
			"@zero wyad wea wel END;\n" +
			"@niezero wyl wea;";
}
