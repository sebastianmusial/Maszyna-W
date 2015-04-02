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
    
    /** Map of components identified by names. */
	private Map<String, WMachineComponent> components = new HashMap<>();
	
	/** Map of signals identified by names. */
	private Map<String, Signal> signals = new HashMap<>();
    
	/** List of components storing addresses. */
	private List<WMachineComponent> addressComponents = new LinkedList<>();
	
	/** List of components storing commands. */
	private List<WMachineComponent> dataComponents = new LinkedList<>();
    
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
        
        components.put("magA", magA);
        components.put("magS", magS);
        components.put("A", A);
        components.put("L", L);
        components.put("I", I);
        components.put("S", S);
        components.put("AK", AK);
        
        addressComponents.add(magA);
        addressComponents.add(A);
        addressComponents.add(L);
        
        dataComponents.add(magS);
        dataComponents.add(I);
        dataComponents.add(S);
        dataComponents.add(AK);
        
        signals.put("wyl", new Signal(L, magA));
        signals.put("wel", new Signal(magA, L));
        signals.put("wea", new Signal(magA, A));
        signals.put("wyad", new Signal(I, magA));
        signals.put("wys", new Signal(S, magS));
        signals.put("wes", new Signal(magS, S));
        signals.put("wei", new Signal(magS, I));
    }
    
    /**
     * Activates signal identified by signalName.
     * @param signalName - name of the signal to be activated.
     * @throws Exception when signalName is not a valid signal name
     * or when signal cannot be activated.
     */
    public void activateSignal(String signalName) throws Exception {
        if(!signals.containsKey(signalName))
            throw new Exception("There is no such signal: " + signalName);
        signals.get(signalName).activate();
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
     * Function to get W Machine component by its name.
     * @param componentName - name of component to be returned.
     * @return Component with name componentName or null if there
     * is no such component.
     */
    public WMachineComponent getComponent(String componentName) {
    	if(components.containsKey(componentName))
    		return components.get(componentName);
    	else return null;
    }
}
