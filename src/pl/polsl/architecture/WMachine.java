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

import pl.polsl.architecture.components.WMachineComponent;
import pl.polsl.architecture.components.finalized.ArithmeticLogicUnit;
import pl.polsl.architecture.components.finalized.Memory;
import pl.polsl.architecture.components.finalized.Register;
import pl.polsl.architecture.signals.Signal;
import pl.polsl.servlet.ArchitectureInfo.AvailableRegisters;
import pl.polsl.servlet.ArchitectureInfo.AvailableSignals;
import pl.polsl.utils.Primitive;


/**
 * W Machine. Is configured by address bit count and operation code
 * bit count. Store all components and signals.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class WMachine {
	/** Bit count for address. */
	private Primitive<Integer> addressBitCount;
    
    /** Bit count for command (operation + address). */
	private Primitive<Integer> dataBitCount;
    
    /** Map of registers identified by IDs. */
	private Map<Integer, Register> registers = new HashMap<>();
	
	/** Map of signals identified by IDs. */
	private Map<Integer, Signal> signals = new HashMap<>();

	/** Memory instance. */
	private Memory memory;
	
	/** ALU instance. */
	private ArithmeticLogicUnit alu;
	
	/** List of all components in this architecture. */
	private List<WMachineComponent> components = new LinkedList<>();
	
	/** Script engine used by ScriptSignal instances. */
	private ScriptEngine engine;
    
	public WMachine(Primitive<Integer> addressBitCount, Primitive<Integer> dataBitCount, ScriptEngine engine) {
		this.addressBitCount = addressBitCount;
		this.dataBitCount = dataBitCount;
		this.engine = engine;
	}
	
	public void addRegister(Integer registerId, Register register) {
		registers.put(registerId, register);
		addComponent(register);
	}
	
	public void addSignal(Integer signalId, Signal signal) {
		signals.put(signalId, signal);
	}
	
	public void addComponent(WMachineComponent component) {
		components.add(component);
		if(component instanceof Memory)
			memory = (Memory)component;
		else if(component instanceof ArithmeticLogicUnit)
			alu = (ArithmeticLogicUnit)component;
	}
	
    /**
     * Sets new address bit count. Changes bit count for commands
     * and informs address and command storing components about the change.
     * @param count - new address bit count.
     */
    public void setAddressBitCount(Integer count) {
    	if(addressBitCount.getValue() == count)
    		return;
    	Integer opCodeBitCount = dataBitCount.getValue() - addressBitCount.getValue();
    	addressBitCount.setValue(count);
    	dataBitCount.setValue(count + opCodeBitCount);
    }
    
    /**
     * Sets new operation code bit count. Changes bit count for commands
     * and informs command storing components about the change.
     * @param count - new operation code bit count.
     */
    public void setDataBitCount(Integer count) {
    	if(dataBitCount.getValue() == count)
    		return;
    	dataBitCount.setValue(count);
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
