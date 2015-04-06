package pl.polsl.i18n;

import java.lang.reflect.Type;

import pl.polsl.servlet.ArchitectureInfo;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * Language serializer.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class LanguageSerializer implements JsonSerializer<Language> {
	/** Default application language. */
	private Language defaultLanguage;
	
	/**
	 * Constructor with default language as parameter.
	 * @param defaultLanguage default language. If some text is not specified
	 * in language being serialized that text will have value from default language.
	 */
	public LanguageSerializer(Language defaultLanguage) {
		super();
		this.defaultLanguage = defaultLanguage;
	}

	/**
	 * Overriden method from super class. Serializes language
	 * to a JsonElement object.
	 * @param language - Language instance to be serialized
	 * @param type - unused, for more information see @see JsonSerializer#serialize(Object, Type, JsonSerializationContext)
	 * @param context - unused, for more information see @see JsonSerializer#serialize(Object, Type, JsonSerializationContext)
	 * @return Serialized language. Returned JSON has following structure:
	 * <pre>{
	 *    Registers: { "1": "L", "2": "AK", ... },
	 *    Signals: { "1": "wyak", "2": "weak", ... },
	 *    UI: {"1": "Maszyna W", "2": "Symulator", ... }
	 *};</pre>
	 */
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
