'use strict';

var MW = MW || {};

MW.Signals = {};

/**
 * Vars
 */
MW.vars = {
    containerCentralUnit: $('#central-unit'),
    busConnection: $('#bus-connection'),
    registerX: $('#register-x'),
    registerY: $('#register-y'),
    extensionRB: $('#extension-rb'),
    extensionG: $('#extension-g'),
    extensionWS: $('#extension-ws'),
    aritmeticalOperations: $('#aritmetical-operations'),
    logicalOperations: $('#logical-operations'),
    extensionIAK: $('#extension-iak'),
    extensionDAK: $('#extension-dak'),
    extensionF: $('#extension-f'),
};



/**
 * Basic attributes which have been used
 */
MW.attr = {
    weCommand: {
        stroke:'#000', 
        'stroke-width': 1.2,
        'arrow-end': 'block-wide-long'
    },
    wyCommand: {
        stroke:'#000', 
        'stroke-width': 1.2,
        'arrow-start': 'block-wide-long'
    },
    textCommand: {
        'font-size': 12,
        'cursor': 'default'
    },
    activeCommand: {
        fill: 'red'
    },
    inactiveCommand: {
        fill: '#000'
    },
    activeArrow: {
        stroke: 'red'
    },
    inactiveArrow: {
        stroke: '#000'
    }
};


/**
 * Run on page init
 */
MW.init = function() {
    if(MW.vars.containerCentralUnit.length) {
        MW.centralUnit(MW.vars.containerCentralUnit);
        MW.additionalElements();
        MW.typeMachine();
        MW.showExtension();
    }
};


/**
 *	Basic machine components.
 */
MW.centralUnit = function(containerCentralUnit) {
    var paper = Raphael("central-unit__base");
    MW.paper = paper;

    //counter
    var counter = function() {
        var welArrow = MW.setArrow(paper, 'M100 0 V50', true),
            wylArrow = MW.setArrow(paper, 'M80 0 V50', false),
            ilArrow = MW.setArrow(paper, 'M20 65 H37', true, true),
            welText = MW.setText(paper, MW.Architecture.Signals.PROGRAM_COUNTER_IN, 120, 25, welArrow),
            wylText = MW.setText(paper, MW.Architecture.Signals.PROGRAM_COUNTER_OUT, 60, 25, wylArrow),
            ilText = MW.setText(paper, MW.Architecture.Signals.PROGRAM_COUNTER_INCREMENT, 10, 65, ilArrow);
    };

    //acumulator
    var acumulator = function() {
        var wejaArrow = MW.setArrow(paper, 'M450 300 V255', true),
            wejaText = MW.setText(paper, MW.Architecture.Signals.ALU_IN, 425, 275, wejaArrow),
            wyakArrow = MW.setArrow(paper, 'M605 300 V65, 65 H500, 500 V100', false),
            wyakText = MW.setText(paper, MW.Architecture.Signals.ACCUMULATOR_OUT, 550, 55, wyakArrow),
            weakArrow = MW.setArrow(paper, 'M345 125 H374', true),
            weakText = MW.setText(paper, MW.Architecture.Signals.ACCUMULATOR_IN, 325, 125, weakArrow),
            dodArrow = MW.setArrow(paper, 'M345 145 H371', true, true),
            dodText = MW.setText(paper, MW.Architecture.Signals.ALU_ADD, 327, 145, dodArrow),
            odeArrow = MW.setArrow(paper, 'M345 165 H371', true, true),
            odeText = MW.setText(paper, MW.Architecture.Signals.ALU_SUBTRACT, 328, 165, odeArrow),
            przepArrow = MW.setArrow(paper, 'M345 185 H371', true, true),
            przepText = MW.setText(paper, MW.Architecture.Signals.ALU_COPY, 324, 185, przepArrow);
    };


    //instruction register
    var instructionRegister = function() {
        var weiArrow = MW.setArrow(paper, 'M125 300 V245', true),
            weiText = MW.setText(paper, MW.Architecture.Signals.INSTRUCTION_IN, 100, 275, weiArrow),
            wyadArrow = MW.setArrow(paper, 'M175 0 V225', false),
            wyadText = MW.setText(paper, MW.Architecture.Signals.INSTRUCTION_OUT, 150, 190, wyadArrow);
    };

    //Memory
    var memory = function() {
        var wesArrow = MW.setArrow(paper, 'M730 300 V245', true),
            wesText = MW.setText(paper, MW.Architecture.Signals.MEMORY_DATA_IN, 750, 275, wesArrow),
            wysArrow = MW.setArrow(paper, 'M690 295 V245', false),
            wysText = MW.setText(paper, MW.Architecture.Signals.MEMORY_DATA_OUT, 670, 275, wysArrow),
            weaArrow = MW.setArrow(paper, 'M710 0 V50', true),
            weaText = MW.setText(paper, MW.Architecture.Signals.MEMORY_ADDRESS_IN, 730, 25, weaArrow),
            czytArrow = MW.setArrow(paper, 'M820 140 H805', true, true),
            czytText = MW.setText(paper, MW.Architecture.Signals.MEMORY_READ, 835, 140, czytArrow),
            piszArrow = MW.setArrow(paper, 'M820 180 H805', true, true),
            piszText = MW.setText(paper, MW.Architecture.Signals.MEMORY_WRITE, 835, 180, piszArrow);
    };

    //Drawing elements
    counter();
    acumulator();
    instructionRegister();
    memory();

	MW.editInputValue();
};


/**
 * Additional elements of the machine
 */
MW.additionalElements = function() {

    //Bus connection
    var busConnection = function() {
        var paper = Raphael("bus-connection");

        var asSaArrow = MW.setArrow(paper, 'M10 0 V295, M5 0 V295' , true);
            asSaArrow.attr({'arrow-start': 'block-wide-long'});

        var asSaText = MW.setText(paper, MW.Architecture.Signals.BUS_CONNECTION, 40, 35, asSaArrow);
    };

    //Register X
    var registerX = function() {
        var paper = Raphael("register-x");

        var wyxArrow = MW.setArrow(paper, 'M65 0 V25' , true),
            wyxText = MW.setText(paper, MW.Architecture.Signals.DATA_X_OUT, 85, 12, wyxArrow),
            wexArrow = MW.setArrow(paper, 'M35 0 V25' , false),
            wexText = MW.setText(paper, MW.Architecture.Signals.DATA_X_IN, 15, 12, wexArrow);
    };

    //Register Y
    var registerY = function() {
        var paper = Raphael("register-y");

        var weyArrow = MW.setArrow(paper, 'M65 0 V25' , true),
            weyText = MW.setText(paper, MW.Architecture.Signals.DATA_Y_IN, 85, 12, weyArrow),
            wyyArrow = MW.setArrow(paper, 'M35 0 V25' , false),
            wyyText = MW.setText(paper, MW.Architecture.Signals.DATA_Y_OUT, 15, 12, wyyArrow);
    };

    //extension RB - out/in
    var extensionRB = function() {
        var paper = Raphael("extension-rb");

        var werbArrow = MW.setArrow(paper, 'M65 0 V25' , true),
            werbText = MW.setText(paper, MW.Architecture.Signals.IO_PORT_IN, 85, 12, werbArrow),
            wyrbArrow = MW.setArrow(paper, 'M35 0 V25' , false),
            wyrbText = MW.setText(paper, MW.Architecture.Signals.IO_PORT_OUT, 15, 12, wyrbArrow);
    };

    //extension G - out/in
    var extensionG = function() {
        var paper = Raphael("extension-g");

        var wegArrow = MW.setArrow(paper, 'M65 0 V25' , true),
            wegText = MW.setText(paper, MW.Architecture.Signals.STROBE_START, 85, 12, wegArrow),
            wygArrow = MW.setArrow(paper, 'M35 0 V25' , false),
            wygText = MW.setText(paper, MW.Architecture.Signals.STROBE_OUT, 15, 12, wygArrow);
    };

    //extension WS
    var extensionWS = function() {
        var paper = Raphael("extension-ws");

        var wywsArrow = MW.setArrow(paper, 'M115 22 V47' , true),
            wywsText = MW.setText(paper, MW.Architecture.Signals.STACK_POINTER_OUT, 140, 35, wywsArrow),
            wewsArrow = MW.setArrow(paper, 'M85 22 V47' , false),
            wewsText = MW.setText(paper, MW.Architecture.Signals.STACK_POINTER_IN, 60, 35, wewsArrow),
            iwsArrow = MW.setArrow(paper, 'M30 10 H46' , true, true),
            iwsText = MW.setText(paper, MW.Architecture.Signals.STACK_POINTER_INCREMENT, 15, 10, iwsArrow),
            dwsArrow = MW.setArrow(paper, 'M170 10 H154' , true, true),
            dwsText = MW.setText(paper, MW.Architecture.Signals.STACK_POINTER_DECREMENT, 185, 10, dwsArrow);
    };

    //artimetical operations
    var aritmeticalOperations = function() {
        var paper = Raphael("aritmetical-operations");

        var mnoArrow = MW.setArrow(paper, 'M55 5 H81', true, true),
            mnoText = MW.setText(paper, MW.Architecture.Signals.ALU_MULTIPLY, 37, 5, mnoArrow),
            dzielArrow = MW.setArrow(paper, 'M55 25 H81', true, true),
            dzielText = MW.setText(paper, MW.Architecture.Signals.ALU_DIVIDE, 37, 25, dzielArrow),
            shrArrow = MW.setArrow(paper, 'M55 45 H81', true, true),
            shrText = MW.setText(paper, MW.Architecture.Signals.ALU_SHIFT_RIGHT, 40, 45, shrArrow);
    };

    //logical operations
    var logicalOperations = function() {
        var paper = Raphael("logical-operations");

        var negArrow = MW.setArrow(paper, 'M5 5 H30', false, true),
            negText = MW.setText(paper, MW.Architecture.Signals.ALU_NEGATION, 47, 5, negArrow),
            lubArrow = MW.setArrow(paper, 'M5 25 H30', false, true),
            lubText = MW.setText(paper, MW.Architecture.Signals.ALU_ALTERNATIVE, 45, 25, lubArrow),
            iArrow = MW.setArrow(paper, 'M5 45 H30', false, true),
            iText = MW.setText(paper, MW.Architecture.Signals.ALU_CONJUNCTION, 38, 45, iArrow);
    };

    //inc/dec
    var incDec = function() {
        var paper = Raphael("extension-iak");

        var iakArrow = MW.setArrow(paper, 'M45 20 H74', true),
            iakText = MW.setText(paper, MW.Architecture.Signals.ACCUMULATOR_INCREMENT, 30, 20, iakArrow);

        var paper2 = Raphael("extension-dak");

        var dakArrow = MW.setArrow(paper2, 'M2 20 H30', false),
            dakText = MW.setText(paper2, MW.Architecture.Signals.ACCUMULATOR_DECREMENT, 47, 20, dakArrow);
    };

    //Drawing elements
    busConnection();
    registerX();
    registerY();
    extensionRB();
    extensionG();
    extensionWS();
    aritmeticalOperations();
    logicalOperations();
    incDec();

    
    //Checkbox - activation of additional elements
   $('.js-extension').on('click', function() {
        MW.showExtension();
        $('.js-type').prop('checked', false);
    });

};


/**
 * Wybór typu maszyny
 */
MW.typeMachine = function() {
    var type  = [
            $('#type0'),
            $('#type1'),
            $('#type2'),
            $('#type3'),
        ];;

    var extensions = [
            $('#extension0'),
            $('#extension1'),
            $('#extension2'),
            $('#extension3'),
            $('#extension4'),
            $('#extension5'),
            $('#extension6'),
            $('#extension7'),
            $('#extension8')
        ];


    type[0].on('click', function() {
        var $this = $(this);
        if($this.prop('checked')) {
            for (var i = extensions.length - 1; i >= 0; i--) {
                extensions[i].prop('checked', false);  
            };
            MW.showExtension();
        }
    });

    type[1].on('click', function() {
        var $this = $(this);
        if($this.prop('checked')) {
            for (var i = extensions.length - 1; i >= 0; i--) {
                if(i == 0) {
                    extensions[i].prop('checked', true);
                }
                else {
                    extensions[i].prop('checked', false);
                }
            };
            MW.showExtension();
        }
    });

    type[2].on('click', function() {
        var $this = $(this);
        if($this.prop('checked')) {
            for (var i = extensions.length - 1; i >= 0; i--) {
                if(i == 3 || i== 8) {
                    extensions[i].prop('checked', false);
                }
                else {
                    extensions[i].prop('checked', true);
                }
            };
            MW.showExtension();
        }
    });

    type[3].on('click', function() {
        var $this = $(this);
        if($this.prop('checked')) {
            for (var i = extensions.length - 1; i >= 0; i--) {
                extensions[i].prop('checked', true);  
            };
            MW.showExtension();
        }
    });
};


/**
 * Pokazywanie zaznaczonych elementów
 */
MW.showExtension = function() {
    var components = [
            MW.vars.busConnection,
            MW.vars.registerX,
            MW.vars.registerY,
            MW.vars.extensionRB,
            MW.vars.extensionG,
            MW.vars.extensionWS,
            MW.vars.aritmeticalOperations,
            MW.vars.logicalOperations,
            MW.vars.extensionIAK,
            MW.vars.extensionDAK,
            MW.vars.extensionF
        ];

    var extensions = [
            $('#extension0'),
            $('#extension1'),
            $('#extension2'),
            $('#extension3'),
            null,
            $('#extension4'),
            $('#extension5'),
            $('#extension6'),
            $('#extension7'),
            null,
            $('#extension8')
        ];

    //Chowanie dodatkowych elementów
    for (var i = components.length - 1; i >= 0; i--) {
        components[i].fadeOut(0);
    }

    //Checkbox - activation of additional elements
    for (var i = 0; i <= components.length -1; i++) {
        if(i == 3 || i == 8) {
            if(extensions[i].prop('checked')) {
                components[i].fadeIn(0);
                components[i+1].fadeIn(0);
            }
            else {
                components[i].fadeOut(0);
                components[i+1].fadeOut(0);
            }
            i++;
            continue;
        }

        if(extensions[i].prop('checked')) {
            components[i].fadeIn(0);
        }
        else {
            components[i].fadeOut(0);
        }
    }
    
};


/**
 * Set label with commands on central unit canvas. Click and hover action.
 * @param {svg canvas} paper
 * @param {string} command - command name
 * @param {int} posX    
 * @param {int} posY 
 * @param {svg element} arrow for command
 */
MW.setText = function(paper, signalId, posX, posY, arrow) {
	
	var command = MW.Language.Signals[signalId];
	
    var label = paper.text(0, 0, command),
        checkbox = $('#handControls');
    
    //Command attributes
    label
        .attr({ x: posX, y: posY})
        .attr(MW.attr.textCommand);
    
    var signal = {
		id: signalId,
		get name() { return label.attr('text'); },
		set name(value) { label.attr('text', value); },
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
		}
	};
    
    label.click(function() {
		$.get("SignalAccessor", {signalId: signalId, signalEnabled: !signal.state});
	});
    label.signalId = signalId;
    arrow.signalId = signalId;

    MW.Signals[signalId] = signal;

    //Click action for arrows
    var arrowAction = function() {
        var attrOff = MW.attr.inactiveArrow,
            attrOn = MW.attr.activeArrow;

        if (arrow.data('status') == 1) {
            arrow.animate(attrOff, 150).data('status', 0 );
        }
        else {
            arrow.animate(attrOn, 150).data('status', 1 );
        }
    };

    //Hover(in) action for commands and arrows
    var hoverIn = function() {
        var attrOn = MW.attr.activeCommand,
            attrArrowOn = MW.attr.activeArrow;

        this.animate( attrOn, 250 );
        arrow.animate( attrArrowOn, 250 );
    };

    //Hover(out) action for commands and arrows
    var hoverOut = function() {
        var attrOff = MW.attr.inactiveCommand,
            attrArrowOff = MW.attr.inactiveArrow;
    
        if (!this.data('status') == 1) {
            this.animate( attrOff, 250 );
        }

        if (!arrow.data('status') == 1) {
            arrow.animate( attrArrowOff, 250 );
        }
    };

    //Hand controls
    checkbox.click(function() {
        if(checkbox.prop('checked')) {
            label
                .attr({'cursor': 'pointer'})
                .click(MW.clickHandler)
                .hover(hoverIn, hoverOut);
        }
        else {
            label
                .attr({'cursor': 'default'})
                .unclick(MW.clickHandler)
                .unhover(hoverIn, hoverOut);
        }
    });

    return label;
};


/**
 * Basic property for arrows.
 * @param {svg canvas}  paper
 * @param {string}  position property for line
 * @param {Boolean} isWe - if is true - arrowhead at the end
 */
MW.setArrow = function(paper, position, isWe, isOtherArrow) {
    var arrow = paper.path(position);

    if(isWe) {
        arrow.attr(MW.attr.weCommand);
    }
    else {
        arrow.attr(MW.attr.wyCommand);
    }

    if(isOtherArrow) {
        if(isWe) {
            arrow.attr({'arrow-end': 'diamond-wide-long'});
        }
        else {
            arrow.attr({'arrow-start': 'diamond-wide-long'});
        }  
    }
    return arrow;
};


/**
 * Click action for commands - toggle state
 */
MW.clickHandler = function() {
	var signal = MW.Signals[this.signalId];  
	signal.state = !signal.state;
};


/**
 * Edit input value if are changes.
 */
MW.editInputValue = function() {
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

