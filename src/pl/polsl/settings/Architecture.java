package pl.polsl.settings;

import java.util.Map;
import java.util.HashMap;

/**
 * Informative enum containing all predefined architectures.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public enum Architecture {
	/** User defined, any extension combination. */
	USER_DEFINED,
	/** Basic architecture, no extensions. */
	W,
	/** W architecture with bus connection. */
	W_PLUS,
	/** All extensions except interrupts, I/O port and flag register. */
	L,
	/** Full architecture, all extensions. */
	EW;
	
	/**
	 * Pass architecture to get extensions for that architecture.
	 * USER_DEFINED architecture has no extensions related. 
	 * @param architecture architecture which extensions will be returned
	 * @return Collection of extensions related to given architecture.
	 */
	public static Extension[] getExtensions(Architecture architecture) {
		return extensions.get(architecture);
	}
	
	/** Collection mapping Architecture to Extension array for that architecture. */
	private static Map<Architecture, Extension[]> extensions = new HashMap<>();
	
	/** Initialization of extensions mapping. */
	static {
		extensions.put(USER_DEFINED, new Extension[0]);
		extensions.put(W, new Extension[0]);
		extensions.put(W_PLUS, new Extension[] {
			Extension.BUS_CONNECTION
		});
		extensions.put(L, new Extension[] {
			Extension.BUS_CONNECTION,
			Extension.ACCUMULATOR_MODIFIERS,
			Extension.ALU_LOGICAL_OPERATIONS,
			Extension.ALU_ARITHMETIC_OPERATIONS,
			Extension.STACK,
			Extension.DATA_REGISTER_X,
			Extension.DATA_REGISTER_Y
		});
		extensions.put(EW, Extension.values());
	}
}
