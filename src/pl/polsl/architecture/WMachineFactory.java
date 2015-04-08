package pl.polsl.architecture;

import javax.script.ScriptEngineManager;

import pl.polsl.architecture.components.finalized.ArithmeticLogicUnit;
import pl.polsl.architecture.components.finalized.Buffer;
import pl.polsl.architecture.components.finalized.Bus;
import pl.polsl.architecture.components.finalized.Memory;
import pl.polsl.architecture.components.finalized.Register;
import pl.polsl.servlet.ArchitectureInfo.AvailableRegisters;
import pl.polsl.servlet.ArchitectureInfo.AvailableSignals;

public class WMachineFactory {

	public WMachine getInstance() {
		Integer addressBitCount = 5;
		Integer dataBitCount = 8;
		
		WMachineBuilder builder = new WMachineBuilder();
		builder.begin(addressBitCount, dataBitCount);
		
        Bus magA = builder.addBus(addressBitCount);
        Bus magS = builder.addBus(dataBitCount);
        
        Register A = builder.addRegister("MEMORY_ADDRESS", addressBitCount);
        Register L = builder.addRegister("PROGRAM_COUNTER", addressBitCount);
        Register I = builder.addRegister("INSTRUCTION", dataBitCount);
        Register S = builder.addRegister("MEMORY_DATA", dataBitCount);
        Register AK = builder.addRegister("ACCUMULATOR", dataBitCount);
        
        Memory memory = builder.addMemory(A);
        
        Buffer aluInBuffer = builder.addBuffer(dataBitCount);
        Buffer aluOutBuffer = builder.addBuffer(dataBitCount);
        ArithmeticLogicUnit alu = builder.addArithmeticLogicUnit(aluInBuffer, aluOutBuffer);

        builder.addSignal("PROGRAM_COUNTER_OUT", L, magA);
        builder.addSignal("PROGRAM_COUNTER_IN", magA, L);
        builder.addSignal("MEMORY_ADDRESS_IN", magA, A);
        builder.addSignal("INSTRUCTION_OUT", I, magA);
        builder.addSignal("MEMORY_DATA_OUT", S, magS);
        builder.addSignal("MEMORY_DATA_IN", magS, S);
        builder.addSignal("INSTRUCTION_IN", magS, I);
        builder.addSignal("MEMORY_READ", memory, S);
        builder.addSignal("MEMORY_WRITE", S, memory);
        
        builder.addSignal("ALU_IN", magS, alu);
        builder.addSignal("ACCUMULATOR_IN", alu, AK);
        builder.addSignal("ACCUMULATOR_OUT", AK, magS);
        builder.addScriptSignal("ALU_ADD", aluInBuffer, aluOutBuffer, "ACCUMULATOR + x");
        builder.addScriptSignal("ALU_SUBTRACT", aluInBuffer, aluOutBuffer, "ACCUMULATOR - x");
        builder.addScriptSignal("ALU_COPY", aluInBuffer, aluOutBuffer, "x");
        builder.addScriptSignal("ALU_MULTIPLY", aluInBuffer, aluOutBuffer, "ACCUMULATOR * x");
        builder.addScriptSignal("ALU_DIVIDE", aluInBuffer, aluOutBuffer, "ACCUMULATOR / x");
        builder.addScriptSignal("ALU_SHIFT_RIGHT", aluInBuffer, aluOutBuffer, "ACCUMULATOR >> x");
        builder.addScriptSignal("ALU_CONJUNTION", aluInBuffer, aluOutBuffer, "ACCUMULATOR & x");
        builder.addScriptSignal("ALU_ALTERNATIVE", aluInBuffer, aluOutBuffer, "ACCUMULATOR | x");
        builder.addScriptSignal("ALU_NEGATION", aluInBuffer, aluOutBuffer, "~x");
		return builder.end();
	}
	
}
