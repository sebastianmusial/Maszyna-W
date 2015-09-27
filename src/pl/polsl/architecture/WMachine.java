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
import pl.polsl.architecture.data.FlagWord;
import pl.polsl.architecture.signals.DataFlowSignal;
import pl.polsl.architecture.signals.Signal;
import pl.polsl.architecture.signals.StopSignal;
import pl.polsl.runner.CommandList;
import pl.polsl.runner.DefaultCommandList;
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
	private Primitive<Integer> opCodeBitCount;
    
    /** Map of registers identified by IDs. */
	private Map<Integer, Register> registers = new HashMap<>();
	
	/** Map of signals identified by IDs. */
	private Map<Integer, Signal> signals = new HashMap<>();

	/** Memory instance. */
	private Memory memory;
	
	/** List of all components in this architecture. */
	private List<WMachineComponent> components = new LinkedList<>();
	
	/** Script engine used by ScriptSignal instances. */
	private ScriptEngine engine;
	
	/** Address of the input port. */
	private Integer inputPortAddress = 1;
	
	/** Address of the output port. */
	private Integer outputPortAddress = 2;
	
	/** Current flags. */
	private FlagWord flags = null;
	
	/** Signal indicating if W Machine is stopped. */
	private StopSignal stopSignal = null;
	
	/** Current command list. */
	private CommandList commandList;
    
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
		commandList = new DefaultCommandList();
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
	public void addSignal(Integer signalId, DataFlowSignal signal) {
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
    	nextTact(false);
    }
    
    /**
     * Informs all components that new tact started.
     * @param keepSignals if true signals status will not be reset
     */
    public void nextTact(boolean keepSignals) {
    	for(WMachineComponent component : components) {
    		component.nextTact();
    	}
    	if(!keepSignals) {
	    	for(Signal signal : signals.values())
	    		signal.setEnabled(false);
    	}
    	updateScriptContext();
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
    
    /**
     * Allow to set W Machine flag word.
     * @param flagWord flag word to be set
     */
    public void setFlagWord(FlagWord flagWord) {
    	this.flags = flagWord;
    }
    
    /**
	 * Single flag state getter.
	 * @param flag flag which state will be returned
	 * @return State of given flag.
	 */
    public Boolean getFlag(Flag flag) {
    	return flags.getFlag(flag);
    }
    
    /**
     * Allow to set stop signal, i.e. signal that
     * indicates if W Machine is stopped or not.
     * @param stopSignal stop signal to be set
     */
    public void setStopSignal(StopSignal stopSignal) {
    	this.stopSignal = stopSignal;
    	signals.put(AvailableSignals.STOP.ID, stopSignal);
    }
    
    /**
     * Check is W Machine is stopped.
     * @return True if W Machine is stopped, i.e. stop signal
     * is enabled, false otherwise. If stop signal is not
     * available true will be retuned.
     */
    public Boolean isStopped() {
    	if(stopSignal != null)
    		return stopSignal.isEnabled();
    	return true;
    }
    
    /**
     * Command list getter.
     * @return Current command list.
     */
    public CommandList getCommandList() {
    	return commandList;
    }
    
    /**
     * Command list setter.
     * @param commandList New command list.
     */
    public void getCommandList(CommandList commandList) {
    	this.commandList = commandList;
    }
}
