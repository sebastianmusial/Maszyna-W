/**
 * TODO rozdzielić na pliki _registers.js, _signals.js i _memory.js
 */

var MW = MW || {};

MW.Memory = [];

MW.restoreState = function() {
	$.get("WMachineState", {action: "restore"}, function(data) {
		var wmachine = JSON.parse(data);
		for(registerId in wmachine.registers)
			MW.Registers[registerId].value = wmachine.registers[registerId];
		for(signalId in wmachine.signals)
			MW.Signals[signalId].state = wmachine.signals[signalId];
		for(var i = 0; i < wmachine.memory.length; ++i)
			MW.Memory[i].value = wmachine.memory[i];
	});
}

// Init interaction.
MW.initInteraction = function() {
	var initRegisters = function() {
		var initRegister = function(registerId, divSelector, inputSelector, nameAttribute) {
			var div = $(divSelector);
			var input = $(inputSelector, div);
			var attributeName = 'data-' + nameAttribute;
			var register = {
				id: registerId,
				get isVisible() { return div.is(':visible'); },
				get name() { return div.attr(attributeName); },
				set name(value) { div.attr(attributeName, value); },
				get value() { return input.prop('value'); },
				set value(value) { input.prop('value', value); }
			};
			input.change(function() {
				var args = {registerId: registerId, value: register.value};
				$.get("RegisterAccessor", args, function(corrected) {
					register.value = corrected;
				});
			});
			MW.Registers[registerId] = register;
		};
		
		var IDs = MW.Architecture.Registers;
		MW.Registers = {};
		initRegister(IDs.PROGRAM_COUNTER, "#counter", "input", "name");
		initRegister(IDs.INSTRUCTION, "#address", "input", "name");
		initRegister(IDs.ACCUMULATOR, "#acumulator", "input", "name");
		initRegister(IDs.MEMORY_ADDRESS, "#memory", "input.memory__address", "top-name");
		initRegister(IDs.MEMORY_DATA, "#memory", "input.memory__verbal", "down-name");
		initRegister(IDs.DATA_X, "#register-x", "input", "name");
		initRegister(IDs.DATA_Y, "#register-y", "input", "name");
		initRegister(IDs.IO_PORT, "#extension-rb", "input", "name");
		initRegister(IDs.STROBE, "#extension-g", "input", "name");
		initRegister(IDs.STACK_POINTER, "#extension-ws", "input", "name");
		initRegister(IDs.FLAGS, "#extension-f", "input", "name");
	};
	
	initRegisters();

	var initSignal = function(signalId, state) {
		var signal = MW.Signals[signalId];
		signal.state = state;
	}
	
	$.get("WMachineState", {action: "restore"}, function(data) {
		var wmachine = JSON.parse(data);
		for(registerId in wmachine.registers) {
			MW.Registers[registerId].name = MW.Language.Registers[registerId];
			MW.Registers[registerId].value = wmachine.registers[registerId];
		}
		for(signalId in wmachine.signals)
			initSignal(signalId, wmachine.signals[signalId]);
		
		var initMemoryCell = function(index) {
			var row = $("#memory-row-" + index);
			var input = $("#memory-cell-" + index + "-value");
			var text = $("#memory-cell-" + index + "-text");
			MW.Memory[i] = {
				row: row,
				get value() { return input.prop('value'); },
				set value(v) { input.prop('value', v); }
			};
			input.change(function() {
				$.get("MemoryAccessor", {index: index, value: input.prop("value")});
			});
		}
		
		for(var i = 0; i < wmachine.memory.length; ++i) {
			initMemoryCell(i);
			MW.Memory[i].value = wmachine.memory[i];
		}
		
//		for(var i = wmachine.memory.length; i < 64; ++i)
//			MW.Memory[i].row.hide();
	});
};