package pl.polsl.parser;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pl.polsl.runner.command.Command;
import pl.polsl.runner.tact.Tact;

public class CommandParserTest {

	private CommandParser parser;
	
	private final String ADD_COMMAND =
		"// Add value from memory at address stored in Ad to value in Ak\n" +
		"// and save result in AK.\n" +
		"// (Ak)+((Ad))->Ak\n" +
		"COMMAND DOD;\n" +
		"czyt wys wei il;\n" +
		"wyad wea;\n" +
		"czyt wys weja dod weak wyl wea;";

	
	@Before
	public void setUp() throws Exception {
		parser = new CommandParser();
	}

	@Test
	public void canParseCommandDescription() {
		Command command = parser.parse(ADD_COMMAND);
		String exprectedDescription = "Add value from memory at address stored in Ad to value in Ak and save result in AK. (Ak)+((Ad))->Ak";
		assertEquals(exprectedDescription, command.getDescription());
	}

	@Test
	public void canParseCommandDefinition() {
		Command command = parser.parse(ADD_COMMAND);
		assertEquals("DOD", command.getName());
	}
	
	@Test
	public void canParseCommandTacts() {
		Command command = parser.parse(ADD_COMMAND);
		List<Tact> tacts = command.getTacts();
		assertEquals(3, tacts.size());
	}
}
