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
 *
 * @author Tomasz Rzepka
 */
public class WMachine {
	Integer addressBitCount = 5;
    Integer opCodeBitCount = 3;
    Integer dataBitCount = addressBitCount + opCodeBitCount;
    
	Map<String, WMachineComponent> components = new HashMap<>();
    Map<String, Signal> signals = new HashMap<>();
    
    List<WMachineComponent> addressComponents = new LinkedList<>();
    List<WMachineComponent> dataComponents = new LinkedList<>();
    
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
    
    void activateSignal(String signalName) throws Exception {
        if(!signals.containsKey(signalName))
            throw new Exception("There is no such signal: " + signalName);
        signals.get(signalName).activate();
    }
    
    public void setAddressBitCount(Integer count) {
    	if(addressBitCount == count)
    		return;
    	addressBitCount = count;
    	dataBitCount = addressBitCount + opCodeBitCount;
    	for(WMachineComponent component : addressComponents)
    		component.setBitCount(count);
    }
    
    public void setOpCodeBitCount(Integer count) {
    	if(opCodeBitCount == count)
    		return;
    	opCodeBitCount = count;
    	dataBitCount = addressBitCount + opCodeBitCount;
    	for(WMachineComponent component : dataComponents)
    		component.setBitCount(dataBitCount);
    }
    
    public WMachineComponent getComponent(String componentName) {
    	if(components.containsKey(componentName))
    		return components.get(componentName);
    	else return null;
    }
}
