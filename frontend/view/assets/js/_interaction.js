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
	S: $('input.memory__verbal', $('#memory'))
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

MW.Signals = [
    'wyl', 'wel', 'il',
    'wea',
    'czyt', 'pisz',
    'wys', 'wes',
    'weja', 'przep', 'ode', 'dod',
    'weak', 'wyak',
    'wei', 'wyad'
];

MW.toogleSignal = function(signal) {
	var label = MW.paper.getById('signal-' + signal + '-label');
	var arrow = MW.paper.getById('signal-' + signal + '-arrow');
	if (label.data('status') == 1) {
		label.animate({fill: '#000'}).data('status', 0 );
		arrow.animate({stroke: '#000'});
    }
    else {
    	label.animate({fill: 'red'}).data('status', 1 );
    	arrow.animate({stroke: 'red'});
    }
};

MW.getSignalState = function(signal) {
	var label = MW.paper.getById('signal-' + signal + '-label');
	return (label.data('status') == 1);
};


// Init interaction.
$(document).ready(function() {
	for(var registerName in MW.Registers) {
		MW.readRegisterValue(registerName);
		MW.bindRegisterChangeHandler(registerName);
	}
	
	$('#run_test_interaction').click(function() {
		var machineState = {};
		for(key in MW.Signals) {
			var signal = MW.Signals[key];
			machineState[signal] = MW.getSignalState(signal);
		}
		$('#test_interaction').html(JSON.stringify(machineState));
	});
});