package pl.polsl.architecture;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import pl.polsl.architecture.components.DataSource;
import pl.polsl.architecture.components.DataTarget;
import pl.polsl.architecture.components.finalized.ArithmeticLogicUnit;
import pl.polsl.architecture.components.finalized.Buffer;
import pl.polsl.architecture.components.finalized.Bus;
import pl.polsl.architecture.components.finalized.Memory;
import pl.polsl.architecture.components.finalized.Register;
import pl.polsl.architecture.signals.ScriptSignal;
import pl.polsl.architecture.signals.Signal;
import pl.polsl.servlet.ArchitectureInfo.AvailableRegisters;
import pl.polsl.servlet.ArchitectureInfo.AvailableSignals;
import pl.polsl.utils.Primitive;

/**
 * WMachine class instance builder.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class WMachineBuilder {

	/** Instance under construction. */
	private WMachine machine = null;
	
	/** Script engine used by the instance. */
	private ScriptEngine engine = null;
	
	/** Address bit count primitive. */
	private Primitive<Integer> addressBitCount;
	
	/** Data bit count primitive. */
	private Primitive<Integer> dataBitCount;

	/**
	 * Begin construction of new WMachine class instance.
	 * @param addressBitCount initial address bit count
	 * @param dataBitCount initial data bit count
	 */
	public void begin(Integer addressBitCount, Integer dataBitCount) {
		this.addressBitCount = new Primitive<>();
		this.addressBitCount.setValue(addressBitCount);
		this.dataBitCount = new Primitive<>();
		this.dataBitCount.setValue(dataBitCount);
		engine = new ScriptEngineManager().getEngineByName("nashorn");
		machine = new WMachine(this.addressBitCount, this.dataBitCount, engine);
	}
	
	/**
	 * Add register to the instance.
	 * @param registerName universal name of the register
	 * @param bitCount bit count that register will store
	 * @return Added register.
	 */
	public Register addRegister(String registerName, Integer bitCount) {
		try {
			Integer registerId = AvailableRegisters.valueOf(registerName).ID;
			Register register = new Register(getBitCountPrimitive(bitCount));
			machine.addRegister(registerId, register);
			return register;
		}
		catch(IllegalArgumentException ex) {
			return null;
		}
	}
	
	/**
	 * Add signal to the instance.
	 * @param signalName universal name of the signal
	 * @param source data source
	 * @param target data target
	 * @return Added signal.
	 */
	public Signal addSignal(String signalName, DataSource source, DataTarget target) {
		try {
			Integer signalId = AvailableSignals.valueOf(signalName).ID;
			Signal signal = new Signal(source, target);
			machine.addSignal(signalId, signal);
			return signal;
		}
		catch(IllegalArgumentException ex) {
			return null;
		}
	}
	
	/**
	 * Add script signal to the instance.
	 * @param signalName universal name of the signal
	 * @param source data source
	 * @param target data target
	 * @param function operation performed when signal is activated
	 * @return Added signal.
	 */
	public Signal addScriptSignal(String signalName, DataSource source, DataTarget target, String function) {
		try {
			Integer signalId = AvailableSignals.valueOf(signalName).ID;
			Signal signal = new ScriptSignal(source, target, function, engine);
			machine.addSignal(signalId, signal);
			return signal;
		}
		catch(IllegalArgumentException ex) {
			return null;
		}
	}
	
	/**
	 * Add memory to the instance.
	 * @param addressRegister memory address register
	 * @return Added memory.
	 */
	public Memory addMemory(Register addressRegister) {
		Memory memory = new Memory(addressRegister, addressBitCount, dataBitCount);
		addressBitCount.addChangeListener(memory);
		machine.addComponent(memory);
		return memory;
	}
	
	/**
	 * Add arithmetic logic unit to the instance.
	 * @param aluInBuffer ALU input buffer
	 * @param aluOutBuffer ALU output buffer
	 * @return Added arithmetic logic unit.
	 */
	public ArithmeticLogicUnit addArithmeticLogicUnit(Buffer aluInBuffer, Buffer aluOutBuffer) {
        ArithmeticLogicUnit alu = new ArithmeticLogicUnit(aluInBuffer, aluOutBuffer);
		machine.addComponent(alu);
		return alu;
	}
	
	/**
	 * Add bus to the instance.
	 * @param bitCount bit count that register will store
	 * @return Added bus.
	 */
	public Bus addBus(Integer bitCount) {
		Bus bus = new Bus(getBitCountPrimitive(bitCount));
		machine.addComponent(bus);
		return bus;
	}
	
	/**
	 * Add buffer to the instance.
	 * @param bitCount bit count that buffer will store
	 * @return Added buffer.
	 */
	public Buffer addBuffer(Integer bitCount) {
		Buffer buffer = new Buffer(getBitCountPrimitive(bitCount));
		machine.addComponent(buffer);
		return buffer;
	}
	
	/**
	 * Stop construction of the instance and return it.
	 * @return Constructed instance.
	 */
	public WMachine end() {
		WMachine wmachine = machine;
		machine = null;
		engine = null;
				
        wmachine.updateScriptContext();
		return wmachine;
	}
	
	/**
	 * Helper function used to differ between address and data bit count values.
	 * @param bitCount some bit count
	 * @return Primitive that represents given bit count.
	 */
	private Primitive<Integer> getBitCountPrimitive(Integer bitCount) {
		if(bitCount == addressBitCount.getValue())
			return addressBitCount;
		else return dataBitCount;
	}
}
