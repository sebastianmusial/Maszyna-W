package pl.polsl.settings;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public enum Architecture {
	USER_DEFINED,
	W,
	W_PLUS,
	L,
	EW;
	
	private static Map<Architecture, Extension[]> extensions = new HashMap<>();
	
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
	
	public static Extension[] getExtensions(Architecture architecture) {
		return extensions.get(architecture);
	}
}
