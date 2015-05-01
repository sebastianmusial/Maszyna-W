/**
 * 
 */
package pl.polsl.parser;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.polsl.i18n.keywords.DefaultKeywordsLanguage;
import pl.polsl.parser.line.CommandCommentLine;
import pl.polsl.parser.line.CommandDefinitionLine;
import pl.polsl.parser.line.CommandTactLine;
import pl.polsl.runner.tact.branch.ConditionalStatement;

/**
 * @author Tomasz Rzepka
 *
 */
public class CommandLineParserTest {

	private CommandLineParser parser;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		parser = new CommandLineParser(new DefaultKeywordsLanguage());
	}

	@Test
	public void canParseCommentLine() {
		String line = "// Komentarz rozkazu";
		CommandCommentLine commentLine = (CommandCommentLine)parser.parse(line);
		assertEquals("Komentarz rozkazu", commentLine.getComment());
	}

	@Test
	public void canParseDefinitionLine() {
		String line = "COMMAND DOD";
		CommandDefinitionLine definitionLine = (CommandDefinitionLine)parser.parse(line);
		assertEquals("DOD", definitionLine.getName());
	}
	
	@Test
	public void canParseLineWithOnlySignals() {
		String line = "czyt   wys wei il";
		CommandTactLine tactLine = (CommandTactLine)parser.parse(line);
		String[] expectedSignals = { "czyt", "wys", "wei", "il" };
		assertArrayEquals(expectedSignals, tactLine.getSignals());
	}
	
	@Test
	public void canParseLineWithOnlyBranch() {
		String line = "IF Z THEN @gotowe ELSE @czekaj";
		CommandTactLine tactLine = (CommandTactLine)parser.parse(line);
		ConditionalStatement branch = (ConditionalStatement)tactLine.getBranch();
		assertEquals("Z", branch.getCondition());
		assertEquals("@gotowe", branch.getTrueLabel());
		assertEquals("@czekaj", branch.getFalseLabel());
	}
	
	@Test
	public void canParseLineWithLabelAndSignals() {
		String line = "@start czyt wys wei il";
		CommandTactLine tactLine = (CommandTactLine)parser.parse(line);
		String[] expectedSignals = { "czyt", "wys", "wei", "il" };
		assertArrayEquals(expectedSignals, tactLine.getSignals());
		assertEquals("@start", tactLine.getLabel());
	}
	
	@Test
	public void canParseLineWithSignalsAndBranch() {
		String line = "czyt   wys   wei il IF Z THEN @gotowe ELSE @czekaj";
		CommandTactLine tactLine = (CommandTactLine)parser.parse(line);
		ConditionalStatement branch = (ConditionalStatement)tactLine.getBranch();
		String[] expectedSignals = { "czyt", "wys", "wei", "il" };
		assertArrayEquals(expectedSignals, tactLine.getSignals());
		assertEquals("Z", branch.getCondition());
		assertEquals("@gotowe", branch.getTrueLabel());
		assertEquals("@czekaj", branch.getFalseLabel());
	}
	
	@Test
	public void canParseLineWithLabelAndSignalsAndBranch() {
		String line = "@start czyt   wys   wei il IF Z THEN @gotowe ELSE @czekaj;";
		CommandTactLine tactLine = (CommandTactLine)parser.parse(line);
		ConditionalStatement branch = (ConditionalStatement)tactLine.getBranch();
		String[] expectedSignals = { "czyt", "wys", "wei", "il" };
		assertArrayEquals(expectedSignals, tactLine.getSignals());
		assertEquals("@start", tactLine.getLabel());
		assertEquals("Z", branch.getCondition());
		assertEquals("@gotowe", branch.getTrueLabel());
		assertEquals("@czekaj", branch.getFalseLabel());
	}
	
	@Test
	public void canParseLineEndedKeyword() {
		String line = "czyt wys wei il END;";
		CommandTactLine tactLine = (CommandTactLine)parser.parse(line);
		String[] expectedSignals = { "czyt", "wys", "wei", "il" };
		assertArrayEquals(expectedSignals, tactLine.getSignals());
	}
}
