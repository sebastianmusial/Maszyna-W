package pl.polsl.architecture;

import static pl.polsl.servlet.ArchitectureInfo.AvailableRegisters.FLAGS;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import pl.polsl.architecture.components.DataSource;
import pl.polsl.architecture.components.DataTarget;
import pl.polsl.architecture.components.finalized.ArithmeticLogicUnit;
import pl.polsl.architecture.components.finalized.Buffer;
import pl.polsl.architecture.components.finalized.Bus;
import pl.polsl.architecture.components.finalized.Memory;
import pl.polsl.architecture.components.finalized.Register;
import pl.polsl.architecture.data.AddressWord;
import pl.polsl.architecture.data.CommandWord;
import pl.polsl.architecture.data.DataWord;
import pl.polsl.architecture.data.FlagWord;
import pl.polsl.architecture.signals.ScriptDataFlowSignal;
import pl.polsl.architecture.signals.DataFlowSignal;
import pl.polsl.architecture.signals.StopSignal;
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
	private Primitive<Integer> opCodeBitCount;

	/**
	 * Begin construction of new WMachine class instance.
	 * @param addressBitCount initial address bit count
	 * @param dataBitCount initial data bit count
	 */
	public void begin(Integer addressBitCount, Integer dataBitCount) {
		this.addressBitCount = new Primitive<>();
		this.addressBitCount.setValue(addressBitCount);
		this.opCodeBitCount = new Primitive<>();
		this.opCodeBitCount.setValue(dataBitCount);
		engine = new ScriptEngineManager().getEngineByName("nashorn");
		machine = new WMachine(this.addressBitCount, this.opCodeBitCount, engine);
	}
	
	/**
	 * Add register to the instance.
	 * @param register enum constant identifying register 
	 * @param bitCount bit count that register will store
	 * @return Added register.
	 */
	public Register addRegister(AvailableRegisters register, Integer bitCount) {
		Register _register = new Register(getDataWord(bitCount));
		machine.addRegister(register.ID, _register);
		return _register;
	}
	
	/**
	 * Add signal to the instance.
	 * @param signal enum constant identifying signal
	 * @param source data source
	 * @param target data target
	 * @return Added signal.
	 */
	public DataFlowSignal addSignal(AvailableSignals signal, DataSource source, DataTarget target) {
		DataFlowSignal _signal = new DataFlowSignal(source, target);
		machine.addSignal(signal.ID, _signal);
		return _signal;
	}
	
	/**
	 * Add script signal to the instance.
	 * @param signal enum constant identifying signal
	 * @param source data source
	 * @param target data target
	 * @param function operation performed when signal is activated
	 * @return Added signal.
	 */
	public DataFlowSignal addScriptSignal(AvailableSignals signal, DataSource source, DataTarget target, String function) {
		DataFlowSignal _signal = new ScriptDataFlowSignal(source, target, function, engine);
		machine.addSignal(signal.ID, _signal);
		return _signal;
	}
	
	/**
	 * Add memory to the instance.
	 * @param addressRegister memory address register
	 * @return Added memory.
	 */
	public Memory addMemory(Register addressRegister) {
		Memory memory = new Memory(addressRegister, addressBitCount, opCodeBitCount);
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
		Bus bus = new Bus(getDataWord(bitCount));
		machine.addComponent(bus);
		return bus;
	}
	
	/**
	 * Add buffer to the instance.
	 * @param bitCount bit count that buffer will store
	 * @return Added buffer.
	 */
	public Buffer addBuffer(Integer bitCount) {
		Buffer buffer = new Buffer(getDataWord(bitCount));
		machine.addComponent(buffer);
		return buffer;
	}
	
	/**
	 * Add flag register to the instance.
	 * @return Added flag register.
	 */
	public Register addFlagRegister() {
		FlagWord flagWord = new FlagWord();
        Register F = new Register(flagWord);
        machine.addRegister(FLAGS.ID, F);
        machine.setFlagWord(flagWord);
        return F;
	}
	
	/**
	 * Add stop signal to the instance.
	 */
	public void addStopSignal() {
		machine.setStopSignal(new StopSignal());
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
	private DataWord getDataWord(Integer bitCount) {
		if(bitCount == addressBitCount.getValue())
			return new AddressWord(addressBitCount);
		else return new CommandWord(opCodeBitCount, addressBitCount);
	}
}
