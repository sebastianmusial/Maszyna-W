/**
 * Initialize interaction with runner buttons.
 * Allow to run program, command or tact.
 */

/**
 * Runners initializer.
 */
function initRunner() {
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
			restoreState();
		});
	});
	
	$('#rightTab a').click(function (e) {
		  e.preventDefault();
		  $(this).tab('show');
		});
	
	$('#leftTab a').click(function (e) {
		  e.preventDefault();
		  $(this).tab('show');
		});
	
	$(".tip").tooltip();
};