/**
 * Register constructor, initializer and translator.
 */

function Register(registerId, input) {
	var div = input.parent();
	var register = {
		id: registerId,
		get isVisible() { return div.is(':visible'); },
		get name() { return div.attr('data-name'); },
		set name(value) { div.attr('data-name', value); },
		get value() { return input.prop('value'); },
		set value(value) { input.prop('value', value); }
	}; 
	input.change(function() {
		var args, pattern = /^\d+$/;
    	if( !pattern.test(register.value) ) {
    		args = {registerId: registerId, action: "get"};
    		$.get("RegisterAccessor", args, function(actualValue) {
    			register.value = actualValue;
    		});
		}
    	else {
    		args = {registerId: registerId, action: "set", value: register.value};
    		$.get("RegisterAccessor", args, function(correctedValue) {
    			register.value = correctedValue;
    		});
    	}
	});
	return register; 
}

function initRegisters() {
	var key,
		names = Mappings.Names.Registers,
		dom = Mappings.Dom.Registers;
	MW.Registers = {};
	for(index in names) {
		key = names[index];
		MW.Registers[key] = Register(key, dom[key]);
	}
};

function retranslateRegisters() {
	var key,
		names = Mappings.Names.Registers;
	for(index in names) {
		key = names[index];
		MW.Registers[key].name = MW.Language.Registers[key];
	}
}
