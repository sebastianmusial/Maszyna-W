/**
 * Signal constructor, initializer and translator.
 */

/**
 * Create DOM wrapper that can manipulate signal.
 */
function Signal(signalId, objects) {
	var label = objects.label,
		arrow = objects.arrow,
		parentDiv = objects.parent;
	var signal = {
		id: signalId,
		get isVisible() { return (parentDiv.style.display != "none"); },
		get name() { return label.attr('text'); },
		set name(value) {
			label.attr('text', value);
			$('tspan', label.node).attr('dy', 4);
		},
		get state() { return (label.data('status') == 1); },
    	set state(value) {
			if(value) {
				label.animate( MW.attr.activeCommand, 250 ).data('status', 1);
		        arrow.animate( MW.attr.activeArrow, 250 ).data('status', 1);
			}
			else {
				label.animate( MW.attr.inactiveCommand, 250 ).data('status', 0);
		        arrow.animate( MW.attr.inactiveArrow, 250 ).data('status', 0);
			}
		},
		send: function() { $.get("SignalAccessor", {signalId: signalId, signalEnabled: signal.state}); }
	};
    label.click(function() {
    	if(Mappings.Dom.Settings.ManualControl.prop("checked")) {
    		signal.state = !signal.state;
    		signal.send();
    	}
	});
    return signal;
}

/**
 * Create signal wrappers.
 */
function initSignals() {
	MW.Signals = {};
	var signals = Mappings.Raphael.Signals;
	for(key in signals) {
		MW.Signals[key] = Signal(key, signals[key]);
	}
}

/**
 * Change signals' language.
 */
function retranslateSignals() {
	var signals = Mappings.Raphael.Signals;
	for(key in signals) {
		MW.Signals[key].name = MW.Language.Signals[key];
	}
}

