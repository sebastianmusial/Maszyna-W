package pl.polsl.utils;

import java.lang.reflect.Type;

import pl.polsl.architecture.Register;
import pl.polsl.architecture.Signal;
import pl.polsl.architecture.WMachine;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * W Machine serializer.
 * @author Tomasz Rzepka
 * @verison 1.0
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
		for(String registerName : machine.getRegisterNames()) {
			Register register = (Register)machine.getComponent(registerName);
			try {
				registers.addProperty(registerName.toUpperCase(), register.getValue());
			} catch (Exception e) {
				// will never enter this catch block
			}
		}
		wmachine.add("registers", registers);
		
		final JsonObject signals = new JsonObject();
		for(String signalName : machine.getSignalNames()) {
			Signal signal = machine.getSignal(signalName);
			signals.addProperty(signalName.toLowerCase(), signal.isEnabled());
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
