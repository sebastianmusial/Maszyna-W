package pl.polsl.utils;

import java.lang.reflect.Type;

import pl.polsl.architecture.Register;
import pl.polsl.architecture.Signal;
import pl.polsl.architecture.WMachine;
import pl.polsl.servlet.ArchitectureInfo;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
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
		for(Integer registerId : ArchitectureInfo.getRegisterIds()) {
			try {
				Register register = (Register)machine.getRegister(registerId);
				if(register != null)
					registers.addProperty(registerId.toString(), register.getValue());
			} catch (Exception e) {
				// will never enter this catch block
			}
		}
		wmachine.add("registers", registers);
		
		final JsonObject signals = new JsonObject();
		for(Integer signalId : ArchitectureInfo.getSignalIds()) {
			Signal signal = machine.getSignal(signalId);
			if(signal != null)
				signals.addProperty(signalId.toString(), signal.isEnabled());
		}
		wmachine.add("signals", signals);
		
		final JsonArray memory = new JsonArray();
		for(Integer value : machine.getMemory().getValues()) {
			memory.add(new JsonPrimitive(value));
		}
		wmachine.add("memory", memory);
		
		return wmachine;
	}

}
