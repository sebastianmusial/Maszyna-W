/**
 * 
 */

var MW = MW || {};

MW.initSettings = function() {
	
	$.get("SettingsAccessor", {action: "get"}, function(data) {
		var settings = JSON.parse(data);
		
		var setProperty = function(property, value, option) {
			var args = {
				action: "set",
				what: property,
				option: option,
				value: value
			};
			return $.get("SettingsAccessor", args);
		}
		
		var initInput = function(selector, property) {
			var input = $(selector);
			input.prop("value", settings[property]);
			
			if(property === "AddressBitCount" || property === "OpCodeBitCount") {
				input.change(function() {
					setProperty(property, input.val()).done(function(data) {
						var values = JSON.parse(data);
						for(var registerId in values)
							MW.Registers[registerId].value = values[registerId];
					});
				});
			}
			else {
				input.change(function() {
					setProperty(property, input.val());
				});
			}
			//Saving changes on click enter button
			input.keypress(function(e) {
		        if (e.keyCode == 13)
					input.blur();
		    });
		};
		
		var initRadio = function(selector, property, value, responseHandler) {
			var radio = $(selector);
			radio.prop("checked", settings[property] == value);
			radio.change(function() {
				if(radio.prop("checked")) {
					setProperty(property, value).done(responseHandler);
				}
			});
		};
		
		var initCheckBox = function(selector, property, option, handler) {
			var checkBox = $(selector);
			if(property == "ManualControl") {
				if(checkBox.prop("checked") != settings[property])
					checkBox.click();
			}
			else checkBox.prop("checked", settings[property][option]);
			
			checkBox.change(function() {
				setProperty(property, checkBox.prop("checked"), option);
			});
			if(property == "Extensions") {
				checkBox.change(function() {
					setProperty("Architecture", "USER_DEFINED", option);
					checkBox.change(MW.showExtension);
				});
			}
		};
		
		var updateExtensions = function(data) {
			var mapping = {
				BUS_CONNECTION: $("#extension0"),
				ACCUMULATOR_MODIFIERS: $("#extension7"),
				ALU_LOGICAL_OPERATIONS: $("#extension6"),
				ALU_ARITHMETIC_OPERATIONS: $("#extension5"),
				STACK: $("#extension4"),
				DATA_REGISTER_X: $("#extension1"),
				DATA_REGISTER_Y: $("#extension2"),
				INPUT_OUTPUT: $("#extension3"),
				FLAGS: $("#extension8")
			};
			var extensions = JSON.parse(data);
			for(var extension in extensions) {
				mapping[extension].prop("checked", extensions[extension]);
			}
			MW.showExtension();
		};
		
		initCheckBox("#handControls", "ManualControl")
		initInput("#address-bit-count", "AddressBitCount");
		initInput("#op-code-bit-count", "OpCodeBitCount");
		initInput("#input-port-address", "InputPortAddress");
		initInput("#output-port-address", "OutputPortAddress");
		initRadio("#track-tact", "TrackLevel", "LOW");
		initRadio("#track-command", "TrackLevel", "MEDIUM");
		initRadio("#track-program", "TrackLevel", "HIGH");
		initRadio("#architecture-w", "Architecture", "W", updateExtensions);
		initRadio("#architecture-w-plus", "Architecture", "W_PLUS", updateExtensions);
		initRadio("#architecture-l", "Architecture", "L", updateExtensions);
		initRadio("#architecture-ew", "Architecture", "EW", updateExtensions);
		initCheckBox("#extension0", "Extensions", "BUS_CONNECTION");
		initCheckBox("#extension7", "Extensions", "ACCUMULATOR_MODIFIERS");
		initCheckBox("#extension6", "Extensions", "ALU_LOGICAL_OPERATIONS");
		initCheckBox("#extension5", "Extensions", "ALU_ARITHMETIC_OPERATIONS");
		initCheckBox("#extension4", "Extensions", "STACK");
		initCheckBox("#extension1", "Extensions", "DATA_REGISTER_X");
		initCheckBox("#extension2", "Extensions", "DATA_REGISTER_Y");
		initCheckBox("#extension3", "Extensions", "INPUT_OUTPUT");
		initCheckBox("#extension8", "Extensions", "FLAGS");
		
		MW.showExtension();
	});
};
