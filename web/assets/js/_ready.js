/**
 * 
 */


/**
 * Running JS functions.
 */
$(document).ready(function() {
	$.get("LanguageAccessor", {lang: "pl"}, function(data) {
		MW.Language = JSON.parse(data);
		$.get("ArchitectureInfo", function(data) {
			MW.Architecture = JSON.parse(data);
			MW.init();
			MW.initInteraction();

			$("#run-tact").click(function() {
				var state = {
					signals: {},
					registers: {}
				};
				
				for(var signalId in MW.Signals) {
					var signal = MW.Signals[signalId];
					if(signal.isVisible)
						state.signals[signalId] = signal.state;
				}
				
				for(var registerId in MW.Registers) {
					var register = MW.Registers[registerId];
					if(register.isVisible)
						state.registers[registerId] = register.value;
				}
				
				$.get("TactRunner", {state: JSON.stringify(state)}).done(function(data) {
					result = JSON.parse(data);
					if(result.status == "ERROR")
						console.log(result.message)
					MW.restoreState();
				});
			});
		});
	});
});
