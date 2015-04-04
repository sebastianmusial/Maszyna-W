/**
 * 
 */

var MW = MW || {};

// Set of available registers.
MW.Registers = {
	L: $('input', $('#counter')),
	I: $('input', $('#address')),
	AK: $('input', $('#acumulator')),
	A: $('input.memory__address', $('#memory')),
	S: $('input.memory__verbal', $('#memory')),
	X: $('#register-x'),
	Y: $('#register-y'),
	RB: $('#extension-rb'),
	G: $('#extension-g'),
	WS: $('#extension-ws'),
	F: $('#extension-f')
};

// Read value of the register from the server.
MW.readRegisterValue = function(registerName) {
	$.get("RegisterAccessor", {componentName: registerName})
	 .done(function(value) {
		 MW.Registers[registerName].prop('value', value);
	 });
}

// Write value of the register to the server.
// Server responds with value adjusted to proper bit count.
// Returned value is set as new value of the register.
MW.writeRegisterValue = function(registerName) {
	var _value = MW.Registers[registerName].prop('value');
	$.get("RegisterAccessor", {componentName: registerName, value: _value})
	 .done(function(corrected) {
		 MW.Registers[registerName].prop('value', corrected);
	 });
}

// Bind WriteValue function as regiter's <input> tag change handler.
MW.bindRegisterChangeHandler = function(registerName) {
	MW.Registers[registerName].bind('change', function(event) {
		MW.writeRegisterValue(registerName);
	});
}

MW.Memory = [];

// Init interaction.
$(document).ready(function() {
	for(var registerName in MW.Registers) {
		MW.bindRegisterChangeHandler(registerName);
	}

	var initSignal = function(signalName, state) {
		var signal = MW.Signals[signalName];
		signal.state = state;
		signal.label.click(function() {
			$.get("SignalAccessor", {signalName: signalName, signalEnabled: !signal.state});
		});
	}
	
	$.get("WMachineState", {action: "restore"}, function(data) {
		wmachine = JSON.parse(data);
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
});