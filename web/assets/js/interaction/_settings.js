/**
 * Initialize interaction with settings.
 * Enable saving settings on server side
 * and restoring it after refresh.
 */

/**
 * Settings initializer.
 */
function initSettings() {
	return $.get("SettingsAccessor", {action: "get"}, function(settings) {
		var setProperty = function(property, value, option) {
			var args = {
				action: "set",
				what: property,
				option: option,
				value: value
			};
			return $.get("SettingsAccessor", args);
		}
		
		var initInput = function(input, property) {
			input.prop("value", settings[property]);
			
			if(property === "AddressBitCount" || property === "OpCodeBitCount") {
				var previousValue;
				input.on("focus", function() {
					previousValue = input.prop("value"); 
				}).change(function() {
					input.prop("disabled", true);
				}).change(function() {
					setProperty(property, input.val()).done(function(values) {
						for(var registerId in values)
							MW.Registers[registerId].value = values[registerId];
					});
					
					var currentValue = input.prop("value");
					if(previousValue == currentValue)
						return;
					
					var i,
						oldMemorySize = (1 << previousValue),
						newMemorySize = (1 << currentValue);
					
					if(oldMemorySize < newMemorySize) {
						for(i = oldMemorySize; i < newMemorySize; ++i) {
							MW.Memory[i].isVisible = true;
							MW.Memory[i].value = 0;
							MW.Memory[i].text = "stp 0";
						}
					}
					else {
						for(i = newMemorySize; i < oldMemorySize; ++i)
							MW.Memory[i].isVisible = false;
					}
					previousValue = currentValue;
				}).change(function() {
					input.prop("disabled", false);
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
		
		var initRadio = function(radio, property, value, responseHandler) {
			radio.prop("checked", settings[property] == value);
			radio.change(function() {
				if(radio.prop("checked")) {
					setProperty(property, value).done(responseHandler);
				}
			});
		};
		
		var initCheckBox = function(checkBox, property, option, handler) {
			if(property == "ManualControl") {
				if(checkBox.prop("checked") != settings[property])
					checkBox.click();
			}
			else checkBox.prop("checked", settings[property][option]);
			
			checkBox.change(function() {
				setProperty(property, checkBox.prop("checked"), option);
				MW.showExtension();
			});
			if(property == "Extensions") {
				checkBox.change(function() {
					setProperty("Architecture", "USER_DEFINED", option);
					checkBox.change(function() {
						MW.showExtension();
						var key, mapping = Mappings.Dom.Settings.Types; 
						for(var key in mapping)
							mapping[key].prop('checked', false);
					});
				});
			}
		};
		
		var updateExtensions = function(extensions) {
			var mapping = Mappings.Dom.Settings.Extensions;
			for(var extension in extensions) {
				mapping[extension].prop("checked", extensions[extension]);
			}
			MW.showExtension();
		};
		
		var key, mapping = Mappings.Dom.Settings;
		initCheckBox(mapping.ManualControl, "ManualControl")
		initInput(mapping.AddressBitCount, "AddressBitCount");
		initInput(mapping.OpCodeBitCount, "OpCodeBitCount");
		initInput(mapping.InputPortAddress, "InputPortAddress");
		initInput(mapping.OutputPortAddress, "OutputPortAddress");
		
		mapping = Mappings.Dom.Settings.TrackLevels;
		for(key in mapping)
			initRadio(mapping[key], "TrackLevel", key);
		mapping = Mappings.Dom.Settings.Types;
		for(key in mapping)
			initRadio(mapping[key], "Architecture", key, updateExtensions);
		mapping = Mappings.Dom.Settings.Extensions;
		for(key in mapping)
			initCheckBox(mapping[key], "Extensions", key);
		MW.showExtension();
	});
};
