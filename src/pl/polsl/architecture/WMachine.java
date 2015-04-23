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

import pl.polsl.architecture.components.WMachineComponent;
import pl.polsl.architecture.components.finalized.Memory;
import pl.polsl.architecture.components.finalized.Register;
import pl.polsl.architecture.signals.Signal;
import pl.polsl.servlet.ArchitectureInfo.AvailableRegisters;
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
	private Primitive<Integer> opCodeBitCount;
    
    /** Map of registers identified by IDs. */
	private Map<Integer, Register> registers = new HashMap<>();
	
	/** Map of signals identified by IDs. */
	private Map<Integer, Signal> signals = new HashMap<>();

	/** Memory instance. */
	private Memory memory;
	
//	/** ALU instance. */
//	private ArithmeticLogicUnit alu;
	
	/** List of all components in this architecture. */
	private List<WMachineComponent> components = new LinkedList<>();
	
	/** Script engine used by ScriptSignal instances. */
	private ScriptEngine engine;
	
	/** Address of the input port. */
	private Integer inputPortAddress = 1;
	
	/** Address of the output port. */
	private Integer outputPortAddress = 2;
    
	/**
	 * Constructs W Machine configured with given parameters.
	 * @param addressBitCount bit count for address components
	 * @param opCodeBitCount bit count for data components
	 * @param engine script engine used to execute script signals
	 */
	public WMachine(Primitive<Integer> addressBitCount, Primitive<Integer> opCodeBitCount, ScriptEngine engine) {
		this.addressBitCount = addressBitCount;
		this.opCodeBitCount = opCodeBitCount;
		this.engine = engine;
	}
	
	/**
	 * Add register identified by given ID.
	 * @param registerId ID of register to be added
	 * @param register register to be added
	 */
	public void addRegister(Integer registerId, Register register) {
		registers.put(registerId, register);
		addComponent(register);
	}
	
	/**
	 * Add signal identified by given ID.
	 * @param signalId ID of signal to be added
	 * @param signal signal to be added
	 */
	public void addSignal(Integer signalId, Signal signal) {
		signals.put(signalId, signal);
	}
	
	/**
	 * Add component without ID.
	 * @param component component to be added, if it is memory
	 * or arithmetic logic unit, references are saved in fields. 
	 */
	public void addComponent(WMachineComponent component) {
		components.add(component);
		if(component instanceof Memory)
			memory = (Memory)component;
//		else if(component instanceof ArithmeticLogicUnit)
//			alu = (ArithmeticLogicUnit)component;
	}
	
	/**
	 * Address bit count getter.
	 * @return Address bit count.
	 */
	public Integer getAddressBitCount() {
    	return addressBitCount.getValue();
    }
	
    /**
     * Sets new address bit count. Changes bit count for commands
     * and informs address and command storing components about the change.
     * @param count - new address bit count.
     */
    public void setAddressBitCount(Integer count) {
    	if(addressBitCount.getValue() == count)
    		return;
    	addressBitCount.setValue(count);
    }
    
    /**
	 * Data bit count getter.
	 * @return Data bit count.
	 */
	public Integer getOpCodeBitCount() {
    	return opCodeBitCount.getValue();
    }
    
    /**
     * Sets new operation code bit count. Changes bit count for commands
     * and informs command storing components about the change.
     * @param count - new operation code bit count.
     */
    public void setOpCodeBitCount(Integer count) {
    	if(opCodeBitCount.getValue() == count)
    		return;
    	opCodeBitCount.setValue(count);
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
    
    /**
     * Informs all components that new tact started.
     */
    public void nextTact() {
    	for(WMachineComponent component : components) {
    		component.nextTact();
    	}
    }
    
    /**
     * Address of the input port getter.
     * @return Address of the input port.
     */
    public Integer getInputPortAddress() {
    	return inputPortAddress;
    }
    
    /**
     * Address of the input port setter.
     * @param address address of the input port to be set
     */
    public void setInputPortAddress(Integer address) {
    	inputPortAddress = address;
    }
    
    /**
     * Address of the output port getter.
     * @return Address of the output port.
     */
    public Integer getOutputPortAddress() {
    	return outputPortAddress;
    }
    
    /**
     * Address of the output port setter.
     * @param address address of the output port to be set
     */
    public void setOutputPortAddress(Integer address) {
    	outputPortAddress = address;
    }
}
