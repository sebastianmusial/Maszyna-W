package pl.polsl.i18n;

import java.lang.reflect.Type;

import pl.polsl.architecture.Register;
import pl.polsl.architecture.Signal;
import pl.polsl.servlet.ArchitectureInfo;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class LanguageSerializer implements JsonSerializer<Language> {
	
	private Language defaultLanguage;
	
	public LanguageSerializer(Language defaultLanguage) {
		super();
		this.defaultLanguage = defaultLanguage;
	}

	@Override
	public JsonElement serialize(Language language, Type type, JsonSerializationContext context) {
		final JsonObject lang = new JsonObject();
		
		final JsonObject registers = new JsonObject();
		for(ArchitectureInfo.AvailableRegisters register : ArchitectureInfo.AvailableRegisters.values()) {
			Integer id = register.ID;
			String name = language.getRegisterName(id);
			if(name == null || "".equals(name))
				name = defaultLanguage.getRegisterName(id);
			registers.addProperty(id.toString(), name);
		}
		lang.add("Registers", registers);
		
		final JsonObject signals = new JsonObject();
		for(ArchitectureInfo.AvailableSignals signal : ArchitectureInfo.AvailableSignals.values()) {
			Integer id = signal.ID;
			String name = language.getSignalName(id);
			if(name == null || "".equals(name))
				name = defaultLanguage.getSignalName(id);
			signals.addProperty(id.toString(), name);
		}
		lang.add("Signals", signals);
		
		final JsonObject userInterface = new JsonObject();
//		for(ArchitectureInfo.AvailableRegisters register : ArchitectureInfo.AvailableRegisters.values()) {
//			Integer id = register.ID;
//			String name = language.getRegisterName(id);
//			if(name == null || "".equals(name))
//				name = defaultLanguage.getUIText(id);
//			userInterface.addProperty(id.toString(), name);
//		}
		lang.add("UI", userInterface);
		
		return lang;
	}

}
