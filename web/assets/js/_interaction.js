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

// Init interaction.
$(document).ready(function() {
	for(var registerName in MW.Registers) {
		MW.readRegisterValue(registerName);
		MW.bindRegisterChangeHandler(registerName);
	}
	
	$('#run_test_interaction').click(function() {
		var activeSignals = [];
		for(key in MW.Signals) {
			var signal = MW.Signals[key];
			if(signal.state)
				activeSignals.push(key);
			signal.state = !signal.state;
		}
		$('#test_interaction').html(activeSignals.toString());
	});
});