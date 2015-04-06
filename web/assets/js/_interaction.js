/**
 * 
 */

var MW = MW || {};

MW.Memory = [];

// Init interaction.
MW.initInteraction = function() {
	var initRegisters = function() {
		MW.Registers = {};
		MW.Registers[MW.Architecture.Registers.PROGRAM_COUNTER] = $('input', $('#counter'));
		MW.Registers[MW.Architecture.Registers.INSTRUCTION] = $('input', $('#address'));
		MW.Registers[MW.Architecture.Registers.ACCUMULATOR] = $('input', $('#acumulator'));
		MW.Registers[MW.Architecture.Registers.MEMORY_ADDRESS] = $('input.memory__address', $('#memory'));
		MW.Registers[MW.Architecture.Registers.MEMORY_DATA] = $('input.memory__verbal', $('#memory'));
		MW.Registers[MW.Architecture.Registers.DATA_X] = $('#register-x');
		MW.Registers[MW.Architecture.Registers.DATA_Y] = $('#register-y');
		MW.Registers[MW.Architecture.Registers.IO_PORT] = $('#extension-rb');
		MW.Registers[MW.Architecture.Registers.STROBE] = $('#extension-g');
		MW.Registers[MW.Architecture.Registers.STACK_POINTER] = $('#extension-ws');
		MW.Registers[MW.Architecture.Registers.FLAGS] = $('#extension-f');
		
		var bindRegisterChangeHandler = function(registerId) {
			var register = MW.Registers[registerId];
			register.change(function() {
				var _value = register.prop('value');
				var args = {registerId: registerId, value: _value};
				$.get("RegisterAccessor", args, function(corrected) {
					register.prop('value', corrected);
				});
			});
		}
		
		for(var registerId in MW.Registers) {
			bindRegisterChangeHandler(registerId)
		}
	};
	
	initRegisters();

	var initSignal = function(signalId, state) {
		var signal = MW.Signals[signalId];
		signal.state = state;
		signal.label.click(function() {
			$.get("SignalAccessor", {signalId: signalId, signalEnabled: !signal.state});
		});
	}
	
	$.get("WMachineState", {action: "restore"}, function(data) {
		var wmachine = JSON.parse(data);
		for(registerName in wmachine.registers)
			MW.Registers[registerName].prop('value', wmachine.registers[registerName]);
		for(signalName in wmachine.signals) {
			initSignal(signalName, wmachine.signals[signalName]);
		}
		
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
		
		for(var i = 0; i < 64; ++i)
			initMemoryCell(i);
		
		for(var i = 0; i < wmachine.memory.length; ++i)
			MW.Memory[i].value = wmachine.memory[i];
		
		for(var i = wmachine.memory.length; i < 64; ++i)
			MW.Memory[i].row.hide();
	});
	
//	$.get("ArchitectureInfo", function(data) {
//		document.write("<pre>" + JSON.stringify(JSON.parse(data), null, 4) + "</pre>");
//	});
	
//	$.get("LanguageAccessor", {lang: "pl"}, function(data) {
//		var lang = JSON.parse(data);
//		document.write("<pre>" + JSON.stringify(lang, null, 4) + "</pre>");
//		
//		console.log(lang.registers[11]);
//	});
};