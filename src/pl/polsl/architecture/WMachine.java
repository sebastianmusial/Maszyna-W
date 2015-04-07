/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.architecture;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import pl.polsl.servlet.ArchitectureInfo.AvailableRegisters;
import pl.polsl.servlet.ArchitectureInfo.AvailableSignals;


/**
 * W Machine. Is configured by address bit count and operation code
 * bit count. Store all components and signals.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class WMachine {
	/** Bit count for address. */
	private Integer addressBitCount = 5;
	
	/** Bit count for operation code. */
	private Integer opCodeBitCount = 3;
    
    /** Bit count for command (operation + address). */
	private Integer dataBitCount = addressBitCount + opCodeBitCount;
    
    /** Map of registers identified by IDs. */
	private Map<Integer, Register> registers = new HashMap<>();
	
	/** Map of signals identified by IDs. */
	private Map<Integer, Signal> signals = new HashMap<>();
    
	/** List of components storing addresses. */
	private List<WMachineComponent> addressComponents = new LinkedList<>();
	
	/** List of components storing commands. */
	private List<WMachineComponent> dataComponents = new LinkedList<>();
	
	/** Memory instance. */
	private Memory memory;
	
	/** ALU instance. */
	private ArithmeticLogicUnit alu;
	
	/** Script engine used by ScriptSignal instances. */
	ScriptEngine engine;
    
	/**
	 * TODO: move to a separate class
	 * For now creates simplest W Machine architecture.
	 * @param filename - file defining architecture
	 */
    public void readArchitecture(String filename) {
        // Test architecture
        Bus magA = new Bus(addressBitCount);
        Bus magS = new Bus(dataBitCount);
        
        Register A = new Register(addressBitCount);
        Register L = new Register(addressBitCount);
        Register I = new Register(dataBitCount);
        Register S = new Register(dataBitCount);
        Register AK = new Register(dataBitCount);
        
        //components.put("magA", magA);
        //components.put("magS", magS);
        registers.put(AvailableRegisters.MEMORY_ADDRESS.ID, A);
        registers.put(AvailableRegisters.PROGRAM_COUNTER.ID, L);
        registers.put(AvailableRegisters.INSTRUCTION.ID, I);
        registers.put(AvailableRegisters.MEMORY_DATA.ID, S);
        registers.put(AvailableRegisters.ACCUMULATOR.ID, AK);
        
        addressComponents.add(magA);
        addressComponents.add(A);
        addressComponents.add(L);
        
        dataComponents.add(magS);
        dataComponents.add(I);
        dataComponents.add(S);
        dataComponents.add(AK);
        
        memory = new Memory(dataBitCount, A);
        dataComponents.add(memory);
        
        Buffer aluInBuffer = new Buffer(32);
        Buffer aluOutBuffer = new Buffer(32);
        alu = new ArithmeticLogicUnit(aluInBuffer, aluOutBuffer);
        dataComponents.add(alu);
        
        engine = new ScriptEngineManager().getEngineByName("nashorn");
        updateScriptContext();
        
        signals.put(AvailableSignals.PROGRAM_COUNTER_OUT.ID, new Signal(L, magA));
        signals.put(AvailableSignals.PROGRAM_COUNTER_IN.ID, new Signal(magA, L));
        signals.put(AvailableSignals.MEMORY_ADDRESS_IN.ID, new Signal(magA, A));
        signals.put(AvailableSignals.INSTRUCTION_OUT.ID, new Signal(I, magA));
        signals.put(AvailableSignals.MEMORY_DATA_OUT.ID, new Signal(S, magS));
        signals.put(AvailableSignals.MEMORY_DATA_IN.ID, new Signal(magS, S));
        signals.put(AvailableSignals.INSTRUCTION_IN.ID, new Signal(magS, I));
        signals.put(AvailableSignals.MEMORY_READ.ID, new Signal(memory, S));
        signals.put(AvailableSignals.MEMORY_WRITE.ID, new Signal(S, memory));
        
        signals.put(AvailableSignals.ALU_IN.ID, new Signal(magS, alu));
        signals.put(AvailableSignals.ACCUMULATOR_IN.ID, new Signal(alu, AK));
        signals.put(AvailableSignals.ACCUMULATOR_OUT.ID, new Signal(AK, magS));
        signals.put(AvailableSignals.ALU_ADD.ID, new ScriptSignal(aluInBuffer, aluOutBuffer, "ACCUMULATOR+x", engine));
        signals.put(AvailableSignals.ALU_SUBTRACT.ID, new ScriptSignal(aluInBuffer, aluOutBuffer, "ACCUMULATOR-x", engine));
        signals.put(AvailableSignals.ALU_COPY.ID, new ScriptSignal(aluInBuffer, aluOutBuffer, "x", engine));
        
//        try {
//        	AK.setValue(10);
//			magS.setValue(3);
//			updateScriptContext();
//			signals.get(AvailableSignals.ALU_IN.ID).activate();
//			signals.get(AvailableSignals.ALU_COPY.ID).activate();
//			signals.get(AvailableSignals.ACCUMULATOR_IN.ID).activate();
//			System.out.print(AK.getValue());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }
    
    /**
     * Sets new address bit count. Changes bit count for commands
     * and informs address and command storing components about the change.
     * @param count - new address bit count.
     */
    public void setAddressBitCount(Integer count) {
    	if(addressBitCount == count)
    		return;
    	addressBitCount = count;
    	dataBitCount = addressBitCount + opCodeBitCount;
    	for(WMachineComponent component : addressComponents)
    		component.setBitCount(count);
    	for(WMachineComponent component : dataComponents)
    		component.setBitCount(dataBitCount);
    	memory.updateSize();
    }
    
    /**
     * Sets new operation code bit count. Changes bit count for commands
     * and informs command storing components about the change.
     * @param count - new operation code bit count.
     */
    public void setOpCodeBitCount(Integer count) {
    	if(opCodeBitCount == count)
    		return;
    	opCodeBitCount = count;
    	dataBitCount = addressBitCount + opCodeBitCount;
    	for(WMachineComponent component : dataComponents)
    		component.setBitCount(dataBitCount);
    }
    
    /**
     * Function to get W Machine register by its ID.
     * @param registerId - ID of register to be returned.
     * @return Register with ID registerId or null if there
     * is no such register.
     */
    public Register getRegister(Integer registerId) {
    	return registers.get(registerId);
    }
    
    /**
     * Return signal with given ID.
     * @param signalId - ID of the signal to be returned
     * @return Signal with ID <i>signalId</i>.
     */
    public Signal getSignal(Integer signalId) {
    	return signals.get(signalId);
    }
    
    /**
     * Memory getter.
     * @return Memory instance.
     */
    public Memory getMemory() {
    	return memory;
    }
    
    /**
     * Update variables in script context in script engine.
     */
    public void updateScriptContext() {
    	ScriptContext context = engine.getContext();
    	try {
	    	for(AvailableRegisters value : AvailableRegisters.values())
	    		context.setAttribute(value.name(), getRegister(value.ID).getValue(), ScriptContext.GLOBAL_SCOPE);
    	}
    	catch(Exception ex) {
    		// will never enter this catch block
    	}
    }
}
