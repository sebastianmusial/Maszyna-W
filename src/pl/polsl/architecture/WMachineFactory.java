package pl.polsl.architecture;

import pl.polsl.architecture.components.finalized.ArithmeticLogicUnit;
import pl.polsl.architecture.components.finalized.Buffer;
import pl.polsl.architecture.components.finalized.Bus;
import pl.polsl.architecture.components.finalized.Memory;
import pl.polsl.architecture.components.finalized.Register;

import static pl.polsl.servlet.ArchitectureInfo.AvailableRegisters.*;
import static pl.polsl.servlet.ArchitectureInfo.AvailableSignals.*;

/**
 * Factory to create WMachine class instances.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class WMachineFactory {

	/**
	 * Create and return WMachine class instance.
	 * @return New WMachine class instance.
	 */
	public WMachine getInstance() {
		final Integer addressBitCount = 5;
		final Integer dataBitCount = 8;
		assert addressBitCount < dataBitCount;
		
		WMachineBuilder builder = new WMachineBuilder();
		builder.begin(addressBitCount, dataBitCount);
		
        Bus magA = builder.addBus(addressBitCount);
        Bus magS = builder.addBus(dataBitCount);
        
        Register A = builder.addRegister(MEMORY_ADDRESS, addressBitCount);
        Register L = builder.addRegister(PROGRAM_COUNTER, addressBitCount);
        Register WS = builder.addRegister(STACK_POINTER, addressBitCount);
        Register I = builder.addRegister(INSTRUCTION, dataBitCount);
        Register S = builder.addRegister(MEMORY_DATA, dataBitCount);
        Register AK = builder.addRegister(ACCUMULATOR, dataBitCount);
        Register X = builder.addRegister(DATA_X, dataBitCount);
        Register Y = builder.addRegister(DATA_Y, dataBitCount);
        Register RB = builder.addRegister(IO_PORT, dataBitCount);
        Register G = builder.addRegister(STROBE, dataBitCount);
        builder.addRegister(FLAGS, dataBitCount);
        
        Memory memory = builder.addMemory(A);
        
        Buffer aluInBuffer = builder.addBuffer(dataBitCount);
        Buffer aluOutBuffer = builder.addBuffer(dataBitCount);
        ArithmeticLogicUnit alu = builder.addArithmeticLogicUnit(aluInBuffer, aluOutBuffer);

        builder.addSignal(PROGRAM_COUNTER_OUT, L, magA);
        builder.addSignal(PROGRAM_COUNTER_IN, magA, L);
        builder.addSignal(MEMORY_ADDRESS_IN, magA, A);
        builder.addSignal(INSTRUCTION_OUT, I, magA);
        builder.addSignal(MEMORY_DATA_OUT, S, magS);
        builder.addSignal(MEMORY_DATA_IN, magS, S);
        builder.addSignal(INSTRUCTION_IN, magS, I);
        builder.addSignal(MEMORY_READ, memory, S);
        builder.addSignal(MEMORY_WRITE, S, memory);
        builder.addSignal(PROGRAM_COUNTER_OUT_TO_DATA_BUS, L, magS);
        builder.addSignal(STACK_POINTER_IN, magA, WS);
        builder.addSignal(STACK_POINTER_OUT, WS, magA);
        builder.addSignal(DATA_X_IN, magS, X);
        builder.addSignal(DATA_X_OUT, X, magS);
        builder.addSignal(DATA_Y_IN, magS, Y);
        builder.addSignal(DATA_Y_OUT, Y, magS);
        builder.addSignal(IO_PORT_IN, magS, RB);
        builder.addSignal(IO_PORT_OUT, RB, magS);
        builder.addSignal(STROBE_OUT, G, magS);
        builder.addSignal(STROBE_START, null, null);
        
        builder.addSignal(ALU_IN, magS, alu);
        builder.addSignal(ACCUMULATOR_IN, alu, AK);
        builder.addSignal(ACCUMULATOR_OUT, AK, magS);
        builder.addScriptSignal(ALU_ADD, aluInBuffer, aluOutBuffer, "ACCUMULATOR + x");
        builder.addScriptSignal(ALU_SUBTRACT, aluInBuffer, aluOutBuffer, "ACCUMULATOR - x");
        builder.addScriptSignal(ALU_COPY, aluInBuffer, aluOutBuffer, "x");
        builder.addScriptSignal(ALU_MULTIPLY, aluInBuffer, aluOutBuffer, "ACCUMULATOR * x");
        builder.addScriptSignal(ALU_DIVIDE, aluInBuffer, aluOutBuffer, "ACCUMULATOR / x");
        builder.addScriptSignal(ALU_SHIFT_RIGHT, aluInBuffer, aluOutBuffer, "ACCUMULATOR >> x");
        builder.addScriptSignal(ALU_CONJUNCTION, aluInBuffer, aluOutBuffer, "ACCUMULATOR & x");
        builder.addScriptSignal(ALU_ALTERNATIVE, aluInBuffer, aluOutBuffer, "ACCUMULATOR | x");
        builder.addScriptSignal(ALU_NEGATION, aluInBuffer, aluOutBuffer, "~x");
        
        builder.addScriptSignal(PROGRAM_COUNTER_INCREMENT, L, L, "x + 1");
        builder.addScriptSignal(ACCUMULATOR_INCREMENT, AK, AK, "x + 1");
        builder.addScriptSignal(ACCUMULATOR_DECREMENT, AK, AK, "x - 1");
        builder.addScriptSignal(STACK_POINTER_INCREMENT, WS, WS, "x + 1");
        builder.addScriptSignal(STACK_POINTER_DECREMENT, WS, WS, "x - 1");
        
		return builder.end();
	}
	
}
