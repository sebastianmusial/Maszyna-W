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
		};
		
		var initInput = function(input, property) {
			input.prop("value", settings[property]);
			input.change(function() {
				setProperty(property, input.val());
			});
			//Saving changes on click enter button
			input.keypress(function(e) {
		        if (e.keyCode == 13)
					input.blur();
		    });
		};
		
		var initBitCountInput = function(input, property) {
			var changed, acceptButton = Mappings.Dom.Settings.AcceptBitCountButton;
			input.prop("value", settings[property]);
			input.OldValue = input.val();
			if(property === "AddressBitCount")
				input.change(function() {
					changed = (input.OldValue != input.val());
					acceptButton.AddressBitCountChanged = changed;
				});
			else
				input.change(function() {
					changed = (input.OldValue != input.val());
					acceptButton.OpCodeBitCountChanged = changed;
				});
			input.change(function() {
				changed = (acceptButton.AddressBitCountChanged || acceptButton.OpCodeBitCountChanged); 
				acceptButton.prop("disabled", !changed);
			});
			//Saving changes on click enter button
			input.keypress(function(e) {
		        if (e.keyCode == 13)
					input.blur();
		    });
		}
		
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
		initBitCountInput(mapping.AddressBitCount, "AddressBitCount");
		initBitCountInput(mapping.OpCodeBitCount, "OpCodeBitCount");
		initInput(mapping.InputPortAddress, "InputPortAddress");
		initInput(mapping.OutputPortAddress, "OutputPortAddress");
		
		mapping.AcceptBitCountButton.click(function() {
			var mapping = Mappings.Dom.Settings;
			mapping.AcceptBitCountButton.prop("disabled", true);
			
			var newAddressBitCount = mapping.AddressBitCount.val();
			var oldAddressBitCount = mapping.AddressBitCount.OldValue;
			if(newAddressBitCount != oldAddressBitCount) {
				mapping.AddressBitCount.OldValue = newAddressBitCount;
				MW.Memory.resize(MW.Memory.DesiredSize);
				setProperty("AddressBitCount", newAddressBitCount).done(restoreState);
			}
			
			var newOpCodeBitCount = mapping.OpCodeBitCount.val();
			var oldOpCodeBitCount = mapping.OpCodeBitCount.OldValue;
			if(newOpCodeBitCount != oldOpCodeBitCount) {
				mapping.OpCodeBitCount.OldValue = newOpCodeBitCount;
				setProperty("AddressBitCount", newOpCodeBitCount).done(restoreState);
			}
		}).prop("disabled", true);
		
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
