package pl.polsl.utils;

import java.lang.reflect.Type;

import pl.polsl.architecture.WMachine;
import pl.polsl.architecture.components.finalized.MemoryCell;
import pl.polsl.architecture.components.finalized.Register;
import pl.polsl.architecture.signals.Signal;
import pl.polsl.runner.CommandList;
import pl.polsl.runner.DefaultCommandList;
import pl.polsl.servlet.ArchitectureInfo.AvailableRegisters;
import pl.polsl.servlet.ArchitectureInfo.AvailableSignals;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * W Machine serializer.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class WMachineSerializer implements JsonSerializer<WMachine> {

	/**
	 * Overriden method from super class. Serializes W Machine state
	 * to a JsonElement object.
	 * @param machine - W Machine instance to be serialized
	 * @param type - unused, for more information see @see JsonSerializer#serialize(Object, Type, JsonSerializationContext)
	 * @param context - unused, for more information see @see JsonSerializer#serialize(Object, Type, JsonSerializationContext)
	 * @return Serialized W Machine state. Returned JSON has following structure:
	 * <pre>{
	 *    registers: { ak: 10, s: 10, ... },
	 *    signals: { wyak: true, weak: false, ... },
	 *    memory: [0, 0, ...]
	 *};</pre>
	 */
	@Override
	public JsonElement serialize(WMachine machine, Type type, JsonSerializationContext context) {
		final JsonObject wmachine = new JsonObject();
		
		final JsonObject registers = new JsonObject();
		for(AvailableRegisters registerId : AvailableRegisters.values()) {
			try {
				Register register = (Register)machine.getRegister(registerId.ID);
				if(register != null)
					registers.addProperty(registerId.toString(), register.getValue());
			} catch (Exception e) {
				// will never enter this catch block
			}
		}
		wmachine.add("registers", registers);
		
		final JsonObject signals = new JsonObject();
		for(AvailableSignals signalId : AvailableSignals.values()) {
			Signal signal = machine.getSignal(signalId.ID);
			if(signal != null)
				signals.addProperty(signalId.toString(), signal.isEnabled());
		}
		wmachine.add("signals", signals);
		
		final CommandList commands = new DefaultCommandList();
		final JsonArray memory = new JsonArray();
		for(MemoryCell cell : machine.getMemory().getCells()) {
			JsonObject jsonCell = new JsonObject();
			jsonCell.addProperty("value", cell.peekValue());
			jsonCell.addProperty("text", commands.getNameByCode(cell.getOpCode()) + " " + cell.getAddress());
			memory.add(jsonCell);
		}
		wmachine.add("memory", memory);
		
		return wmachine;
	}

}
