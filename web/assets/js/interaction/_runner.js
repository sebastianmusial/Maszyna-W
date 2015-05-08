/**
 * Initialize interaction with runner buttons.
 * Allow to run program, command or tact.
 */

/**
 * Runners initializer.
 */
function initRunner() {
//	$("#run-tact").click(function() {
//		var state = {
//			signals: {},
//			registers: {}
//		};
//		
//		for(var signalId in MW.Signals) {
//			var signal = MW.Signals[signalId];
//			if(signal.isVisible)
//				state.signals[signalId] = signal.state;
//		}
//		
//		for(var registerId in MW.Registers) {
//			var register = MW.Registers[registerId];
//			if(register.isVisible)
//				state.registers[registerId] = register.value;
//		}
//		
//		$.get("TactRunner", {state: JSON.stringify(state)}).done(function(data) {
//			result = JSON.parse(data);
//			if(result.status == "ERROR")
//				console.log(result.message)
//			restoreState();
//		});
//	});
//	
//	$("#run-command").click(function() {
//		$.get("CommandRunner").done(restoreState);
//	});
	
	$("#run-program").click(function() {
		$.get("RunnerAccessor", {run: "PROGRAM"}).done(restoreState);
	});
	
	$("#run-command").click(function() {
		$.get("RunnerAccessor", {run: "COMMAND"}).done(restoreState);
	});
	
	$("#run-tact").click(function() {
		if(Mappings.Dom.Settings.ManualControl.prop("checked"))
			$.get("RunnerAccessor", {run: "MANUALLY"}).done(restoreState);
		else
			$.get("RunnerAccessor", {run: "TACT"}).done(restoreState);
	});
	
	$("#reset").click(function() {
		var key, register, signal;
		for(key in MW.Registers) {
			register = MW.Registers[key];
			register.value = 0;
			register.send();
		}
		for(key in MW.Signals) {
			signal = MW.Signals[key];
			signal.state = false;
			signal.send();
		}
	});
	
	$("#stop").click(function() {
		
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