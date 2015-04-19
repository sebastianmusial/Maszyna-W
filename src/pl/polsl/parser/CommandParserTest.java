/**
 * 
 */
package pl.polsl.parser;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Dawid Poloczek
 *
 */
public class CommandParserTest {

	@Test
	public void test() {
		CommandParser parser = new CommandParser();
		String text = "czyt wys wei il;\n"
				+ "IF zak THEN @zero IF NOT @niezero;\n"
				+ "@zero wyad wea wel;\n"
				+ "@niezero wyl wea;";
		Command command = parser.parseText(text);
		assertTrue(true);
	}

}
