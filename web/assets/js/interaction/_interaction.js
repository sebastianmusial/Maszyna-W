/**
 * 
 */

function restoreState() {
	$.get("WMachineState", {action: "restore"}, function(wmachine) {
		for(registerId in wmachine.registers)
			MW.Registers[registerId].value = wmachine.registers[registerId];
		for(signalId in wmachine.signals)
			MW.Signals[signalId].state = wmachine.signals[signalId];
//		for(var i = 0; i < wmachine.memory.length; ++i)
//			MW.Memory[i].value = wmachine.memory[i];
	});
}


function initInteractions() {
	/**
	 * Edit input value if are changes.
	 */
	var input = $('.js-quick-edit'),
        onlyNumber = $('.js-only-numbers'),
		actualValue;

    //Clearing text input
	input.focusin(function() {
		var $this = $(this);

		actualValue = $this.val();
        $this.val('');
    });

    //Saving changes if new value
    input.focusout(function() {
			var $this = $(this);
			
			if($this.val() == '') {
				$this.val(actualValue);
			}
			
			var pattern = /^\d+$/;
			
	    	if( !pattern.test($this.val()) ) {
				$this.val('0');
			}
		});
    
    input.change(function() {
    	var $this = $(this);
		var pattern = /^\d+$/;
		
    	if( !pattern.test($this.val()) ) {
			$this.val(actualValue);
		}
    });

    //Saving changes on click enter button
	input.keypress(function(e) {
        if (e.keyCode == 13) {
			$(this).blur();
        }
    });

    //accepting only numbers (addtitional keys: '-'' and 'backspace')
    onlyNumber.keypress(function(e) {
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57) && e.which != 45) {
            return false;
        }
    });
};