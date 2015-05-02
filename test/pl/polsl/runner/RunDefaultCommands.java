package pl.polsl.runner;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.polsl.architecture.Flag;
import pl.polsl.architecture.WMachine;
import pl.polsl.architecture.WMachineFactory;
import pl.polsl.architecture.components.finalized.Memory;
import pl.polsl.architecture.components.finalized.MemoryCell;
import pl.polsl.architecture.components.finalized.Register;
import pl.polsl.runner.CommandList;
import pl.polsl.runner.DefaultCommandList;
import pl.polsl.servlet.ArchitectureInfo.AvailableRegisters;

public class RunDefaultCommands {

	private WMachine machine;
	private CommandList commandList;
	
	@Before
	public void setUp() throws Exception {
		WMachineFactory factory = new WMachineFactory();
		machine = factory.getInstance();
		commandList = new DefaultCommandList();
	}

	@Test
	public void test() {
		Register AK = machine.getRegister(AvailableRegisters.ACCUMULATOR.ID);
		Register L  = machine.getRegister(AvailableRegisters.PROGRAM_COUNTER.ID);
		Register A  = machine.getRegister(AvailableRegisters.MEMORY_ADDRESS.ID);
		Register I  = machine.getRegister(AvailableRegisters.INSTRUCTION.ID);
		Memory memory = machine.getMemory();
		
		assertEquals(0, AK.peekValue().intValue());
		assertEquals(0, L.peekValue().intValue());
		assertEquals(0, A.peekValue().intValue());
		assertEquals(0, memory.peekValue().intValue());
		
		int counter = 0;
		for(MemoryCell cell : memory.getCells())
			cell.replaceValue(counter++);
		
		memory.replaceValue(0, 15);
		memory.replaceValue(1, 10);
		memory.replaceValue(2, 7);
		memory.replaceValue(3, 2);
		memory.replaceValue(4, 0);
		assertEquals(15, memory.peekValue(0).intValue());
		assertEquals(10, memory.peekValue(1).intValue());
		assertEquals(7, memory.peekValue(2).intValue());
		assertEquals(2, memory.peekValue(3).intValue());
		assertEquals(0, memory.peekValue(4).intValue());
		
		commandList.getCommandByName("ADD").run(machine);
		assertEquals(15, AK.peekValue().intValue());
		assertEquals(1, A.peekValue().intValue());
		
		commandList.getCommandByName("LD").run(machine);
		assertEquals(10, AK.peekValue().intValue());
		assertEquals(2, A.peekValue().intValue());
		
		commandList.getCommandByName("SUB").run(machine);
		assertEquals(3, AK.peekValue().intValue());
		assertEquals(3, A.peekValue().intValue());
		
		commandList.getCommandByName("ST").run(machine);
		assertEquals(3, AK.peekValue().intValue());
		assertEquals(4, A.peekValue().intValue());
		assertEquals(2, I.peekValue().intValue());
		assertEquals(3, memory.peekValue(2).intValue());
		
		commandList.getCommandByName("JMP").run(machine);
		assertEquals(0, A.peekValue().intValue());
		
		// will not jump
		A.replaceValue(0);
		L.replaceValue(0);
		AK.replaceValue(1);
		commandList.getCommandByName("JMPZ").run(machine);
		assertEquals(1, A.peekValue().intValue());
		
		// will jump
		AK.replaceValue(0);
		assertTrue(machine.getFlag(Flag.ZERO));
		commandList.getCommandByName("JMPZ").run(machine);
		assertEquals(10, A.peekValue().intValue());
		
		// will not jump
		A.replaceValue(0);
		L.replaceValue(0);
		AK.replaceValue(1);
		commandList.getCommandByName("JMPN").run(machine);
		assertEquals(1, A.peekValue().intValue());
		
		// will jump
		AK.replaceValue(-1);
		assertTrue(machine.getFlag(Flag.SIGN));
		commandList.getCommandByName("JMPN").run(machine);
		assertEquals(10, A.peekValue().intValue());
	}

}
