'use strict';

var Mappings = Mappings || {};

/**
 * Names used to identify signals and registers.
 * Used to iterate over all of registers and/or signals.
 */
Mappings.Names = {
	Registers: [
		"PROGRAM_COUNTER",
		"INSTRUCTION",
		"ACCUMULATOR",
		"MEMORY_ADDRESS",
		"MEMORY_DATA",
		"DATA_X",
		"DATA_Y",
		"IO_PORT",
		"STROBE",
		"STACK_POINTER",
		"FLAGS"
	],
	Signals: [
		"PROGRAM_COUNTER_IN",
		"PROGRAM_COUNTER_OUT",
		"PROGRAM_COUNTER_INCREMENT",
		"PROGRAM_COUNTER_OUT_TO_DATA_BUS",
		"INSTRUCTION_IN",
		"INSTRUCTION_OUT",
		"ACCUMULATOR_IN",
		"ACCUMULATOR_OUT",
		"ACCUMULATOR_INCREMENT",
		"ACCUMULATOR_DECREMENT",
		"MEMORY_ADDRESS_IN",
		"MEMORY_DATA_IN",
		"MEMORY_DATA_OUT",
		"MEMORY_WRITE",
		"MEMORY_READ",
		"STACK_POINTER_IN",
		"STACK_POINTER_OUT",
		"STACK_POINTER_INCREMENT",
		"STACK_POINTER_DECREMENT",
		"BUS_CONNECTION",
		"ALU_ADD",
		"ALU_SUBTRACT",
		"ALU_MULTIPLY",
		"ALU_DIVIDE",
		"ALU_COPY",
		"ALU_SHIFT_RIGHT",
		"ALU_CONJUNCTION",
		"ALU_ALTERNATIVE",
		"ALU_NEGATION",
		"DATA_X_IN",
		"DATA_X_OUT",
		"DATA_Y_IN",
		"DATA_Y_OUT",
		"IO_PORT_IN",
		"IO_PORT_OUT",
		"STROBE_START",
		"STROBE_OUT",
		"ALU_IN"
	]
};

/**
 * Mappings used to directly manipulate DOM elements.
 */
Mappings.Dom = {
	Registers: {
		PROGRAM_COUNTER: $("#counter input"),
		INSTRUCTION: $("#address input"),
		ACCUMULATOR: $("#acumulator input"),
		MEMORY_ADDRESS: $("#memory input.memory__address"),
		MEMORY_DATA: $("#memory input.memory__verbal"),
		DATA_X: $("#register-x input"),
		DATA_Y: $("#register-y input"),
		IO_PORT: $("#extension-rb input"),
		STROBE: $("#extension-g input"),
		STACK_POINTER: $("#extension-ws input"),
		FLAGS: $("#extension-f input"),
	},
	View: {
		containerCentralUnit: $('#central-unit'),
	    busConnection: $('#bus-connection'),
	    registerX: $('#register-x'),
	    registerY: $('#register-y'),
	    extensionRB: $('#extension-rb'),
	    extensionG: $('#extension-g'),
	    extensionWS: $('#extension-ws'),
	    aritmeticalOperations: $('#aritmetical-operations'),
	    logicalOperations: $('#logical-operations'),
	    extensionIAK: $('#extension-iak'),
	    extensionDAK: $('#extension-dak'),
	    extensionF: $('#extension-f')
	},
	Settings: {
		ManualControl: $("#handControls"),
		TrackLevels: {
			LOW: $("#track-program"),
			MEDIUM: $("#track-command"),
			HIGH: $("#track-tact"),
		},
		Types: {
			W: $('#architecture-w'),
			W_PLUS: $('#architecture-w-plus'),
			L: $('#architecture-l'),
			EW: $('#architecture-ew')
		},
		Extensions: {
			BUS_CONNECTION: $("#extension0"),
			DATA_REGISTER_X: $("#extension1"),
			DATA_REGISTER_Y: $("#extension2"),
			INPUT_OUTPUT: $("#extension3"),
			STACK: $("#extension4"),
			ALU_ARITHMETIC_OPERATIONS: $("#extension5"),
			ALU_LOGICAL_OPERATIONS: $("#extension6"),
			ACCUMULATOR_MODIFIERS: $("#extension7"),
			FLAGS: $("#extension8")
		},
		AddressBitCount: $("#address-bit-count"),
		OpCodeBitCount: $("#op-code-bit-count"),
		InputPortAddress: $("#input-port-address"),
		OutputPortAddress: $("#output-port-address")
	}
};

Mappings.Raphael = { Signals: {} };
