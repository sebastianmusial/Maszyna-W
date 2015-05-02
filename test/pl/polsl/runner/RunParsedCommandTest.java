package pl.polsl.runner;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.polsl.architecture.WMachine;
import pl.polsl.architecture.WMachineFactory;
import pl.polsl.architecture.components.finalized.Memory;
import pl.polsl.architecture.components.finalized.MemoryCell;
import pl.polsl.architecture.components.finalized.Register;
import pl.polsl.parser.CommandParser;
import pl.polsl.runner.command.Command;
import pl.polsl.servlet.ArchitectureInfo.AvailableRegisters;

public class RunParsedCommandTest {

	private WMachine machine;
	private CommandParser parser;
	private Command command;
	
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
		WMachineFactory factory = new WMachineFactory();
		machine = factory.getInstance();
		parser = new CommandParser();
		command = parser.parse(ADD_COMMAND);
	}

	@Test
	public void runAddCommand() {
		Register AK = machine.getRegister(AvailableRegisters.ACCUMULATOR.ID);
		Register L  = machine.getRegister(AvailableRegisters.PROGRAM_COUNTER.ID);
		Register A  = machine.getRegister(AvailableRegisters.MEMORY_ADDRESS.ID);
		Memory memory = machine.getMemory();
		
		assertEquals(0, AK.peekValue().intValue());
		assertEquals(0, L.peekValue().intValue());
		assertEquals(0, A.peekValue().intValue());
		assertEquals(0, memory.peekValue().intValue());
		
		int counter = 0;
		for(MemoryCell cell : memory.getCells())
			cell.replaceValue(counter++);
		
		memory.replaceValue(15);
		assertEquals(15, memory.peekValue().intValue());
		
		command.run(machine);
		
		assertEquals(15, AK.peekValue().intValue());
		assertEquals(1, L.peekValue().intValue());
		assertEquals(1, A.peekValue().intValue());
		assertNotEquals(15, memory.peekValue().intValue());
		
		memory.replaceValue(25);
		assertEquals(25, memory.peekValue().intValue());
		
		command.run(machine);
		
		assertEquals(40, AK.peekValue().intValue());
		assertEquals(2, L.peekValue().intValue());
		assertEquals(2, A.peekValue().intValue());
		assertNotEquals(25, memory.peekValue().intValue());
	}

}
