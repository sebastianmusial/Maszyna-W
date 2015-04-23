'use strict';

var MW = MW || {};

/**
 * Vars
 */
MW.vars = Mappings.Dom.View;

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
MW.initView = function() {
    if(MW.vars.containerCentralUnit.length) {
        MW.centralUnit();
        MW.additionalElements();
        MW.showExtension();
    }
};


/**
 *	Basic machine components.
 */
MW.centralUnit = function() {
    var paper = Raphael("central-unit__base");

    //counter
    var counter = function() {
        var welArrow = MW.setArrow(paper, 'M100 0 V50', true),
            wylArrow = MW.setArrow(paper, 'M80 0 V50', false),
            ilArrow = MW.setArrow(paper, 'M20 65 H37', true, true),
            welText = MW.setText(paper, "PROGRAM_COUNTER_IN", 120, 25, welArrow),
            wylText = MW.setText(paper, "PROGRAM_COUNTER_OUT", 60, 25, wylArrow),
            ilText = MW.setText(paper, "PROGRAM_COUNTER_INCREMENT", 10, 65, ilArrow);
    };

    //acumulator
    var acumulator = function() {
        var wejaArrow = MW.setArrow(paper, 'M450 300 V255', true),
            wejaText = MW.setText(paper, "ALU_IN", 425, 275, wejaArrow),
            wyakArrow = MW.setArrow(paper, 'M605 295 V65, 65 H500, 500 V100', false),
            wyakText = MW.setText(paper, "ACCUMULATOR_OUT", 550, 55, wyakArrow),
            weakArrow = MW.setArrow(paper, 'M345 125 H374', true),
            weakText = MW.setText(paper, "ACCUMULATOR_IN", 325, 125, weakArrow),
            dodArrow = MW.setArrow(paper, 'M345 145 H371', true, true),
            dodText = MW.setText(paper, "ALU_ADD", 327, 145, dodArrow),
            odeArrow = MW.setArrow(paper, 'M345 165 H371', true, true),
            odeText = MW.setText(paper, "ALU_SUBTRACT", 328, 165, odeArrow),
            przepArrow = MW.setArrow(paper, 'M345 185 H371', true, true),
            przepText = MW.setText(paper, "ALU_COPY", 324, 185, przepArrow);
    };


    //instruction register
    var instructionRegister = function() {
        var weiArrow = MW.setArrow(paper, 'M125 300 V245', true),
            weiText = MW.setText(paper, "INSTRUCTION_IN", 100, 275, weiArrow),
            wyadArrow = MW.setArrow(paper, 'M175 0 V225', false),
            wyadText = MW.setText(paper, "INSTRUCTION_OUT", 150, 190, wyadArrow);
    };

    //Memory
    var memory = function() {
        var wesArrow = MW.setArrow(paper, 'M730 300 V245', true),
            wesText = MW.setText(paper, "MEMORY_DATA_IN", 750, 275, wesArrow),
            wysArrow = MW.setArrow(paper, 'M690 295 V245', false),
            wysText = MW.setText(paper, "MEMORY_DATA_OUT", 670, 275, wysArrow),
            weaArrow = MW.setArrow(paper, 'M710 0 V50', true),
            weaText = MW.setText(paper, "MEMORY_ADDRESS_IN", 730, 25, weaArrow),
            czytArrow = MW.setArrow(paper, 'M820 140 H805', true, true),
            czytText = MW.setText(paper, "MEMORY_READ", 835, 140, czytArrow),
            piszArrow = MW.setArrow(paper, 'M820 180 H805', true, true),
            piszText = MW.setText(paper, "MEMORY_WRITE", 835, 180, piszArrow);
    };

    //Drawing elements
    counter();
    acumulator();
    instructionRegister();
    memory();
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

        var asSaText = MW.setText(paper, "BUS_CONNECTION", 40, 35, asSaArrow);
    };

    //Register X
    var registerX = function() {
        var paper = Raphael("register-x");

        var wyxArrow = MW.setArrow(paper, 'M65 0 V25' , true),
            wyxText = MW.setText(paper, "DATA_X_OUT", 85, 12, wyxArrow),
            wexArrow = MW.setArrow(paper, 'M35 0 V25' , false),
            wexText = MW.setText(paper, "DATA_X_IN", 15, 12, wexArrow);
    };

    //Register Y
    var registerY = function() {
        var paper = Raphael("register-y");

        var weyArrow = MW.setArrow(paper, 'M65 0 V25' , true),
            weyText = MW.setText(paper, "DATA_Y_IN", 85, 12, weyArrow),
            wyyArrow = MW.setArrow(paper, 'M35 0 V25' , false),
            wyyText = MW.setText(paper, "DATA_Y_OUT", 15, 12, wyyArrow);
    };

    //extension RB - out/in
    var extensionRB = function() {
        var paper = Raphael("extension-rb");

        var werbArrow = MW.setArrow(paper, 'M65 0 V25' , true),
            werbText = MW.setText(paper, "IO_PORT_IN", 85, 12, werbArrow),
            wyrbArrow = MW.setArrow(paper, 'M35 0 V25' , false),
            wyrbText = MW.setText(paper, "IO_PORT_OUT", 15, 12, wyrbArrow);
    };

    //extension G - out/in
    var extensionG = function() {
        var paper = Raphael("extension-g");

        var wegArrow = MW.setArrow(paper, 'M65 0 V25' , true),
            wegText = MW.setText(paper, "STROBE_START", 85, 12, wegArrow),
            wygArrow = MW.setArrow(paper, 'M35 0 V25' , false),
            wygText = MW.setText(paper, "STROBE_OUT", 15, 12, wygArrow);
    };

    //extension WS
    var extensionWS = function() {
        var paper = Raphael("extension-ws");

        var wywsArrow = MW.setArrow(paper, 'M115 22 V47' , true),
            wywsText = MW.setText(paper, "STACK_POINTER_OUT", 140, 35, wywsArrow),
            wewsArrow = MW.setArrow(paper, 'M85 22 V47' , false),
            wewsText = MW.setText(paper, "STACK_POINTER_IN", 60, 35, wewsArrow),
            iwsArrow = MW.setArrow(paper, 'M30 10 H46' , true, true),
            iwsText = MW.setText(paper, "STACK_POINTER_INCREMENT", 15, 10, iwsArrow),
            dwsArrow = MW.setArrow(paper, 'M170 10 H154' , true, true),
            dwsText = MW.setText(paper, "STACK_POINTER_DECREMENT", 185, 10, dwsArrow);
        	
    	var paperWyls = Raphael("extension-wyls");
    	
    	var wylsArrow = MW.setArrow(paperWyls, 'M100 0 V220' , true),
        	wylsText = MW.setText(paperWyls, "PROGRAM_COUNTER_OUT_TO_DATA_BUS", 75, 65, wylsArrow);
    };

    //artimetical operations
    var aritmeticalOperations = function() {
        var paper = Raphael("aritmetical-operations");

        var mnoArrow = MW.setArrow(paper, 'M55 5 H81', true, true),
            mnoText = MW.setText(paper, "ALU_MULTIPLY", 37, 5, mnoArrow),
            dzielArrow = MW.setArrow(paper, 'M55 25 H81', true, true),
            dzielText = MW.setText(paper, "ALU_DIVIDE", 37, 25, dzielArrow),
            shrArrow = MW.setArrow(paper, 'M55 45 H81', true, true),
            shrText = MW.setText(paper, "ALU_SHIFT_RIGHT", 40, 45, shrArrow);
    };

    //logical operations
    var logicalOperations = function() {
        var paper = Raphael("logical-operations");

        var negArrow = MW.setArrow(paper, 'M5 5 H30', false, true),
            negText = MW.setText(paper, "ALU_NEGATION", 47, 5, negArrow),
            lubArrow = MW.setArrow(paper, 'M5 25 H30', false, true),
            lubText = MW.setText(paper, "ALU_ALTERNATIVE", 45, 25, lubArrow),
            iArrow = MW.setArrow(paper, 'M5 45 H30', false, true),
            iText = MW.setText(paper, "ALU_CONJUNCTION", 38, 45, iArrow);
    };

    //inc/dec
    var incDec = function() {
        var paper = Raphael("extension-iak");

        var iakArrow = MW.setArrow(paper, 'M45 20 H74', true),
            iakText = MW.setText(paper, "ACCUMULATOR_INCREMENT", 30, 20, iakArrow);

        var paper2 = Raphael("extension-dak");

        var dakArrow = MW.setArrow(paper2, 'M2 20 H30', false),
            dakText = MW.setText(paper2, "ACCUMULATOR_DECREMENT", 47, 20, dakArrow);
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
    var label = paper.text(0, 0, ""),
        checkbox = Mappings.Dom.Settings.ManualControl;
    
    //Command attributes
    label
        .attr({ x: posX, y: posY})
        .attr(MW.attr.textCommand);
    
    label.signalId = signalId;
    arrow.signalId = signalId;
    
    Mappings.Raphael.Signals[signalId] = {
    	label: label,
    	arrow: arrow,
    	parent: paper.canvas.parentNode
    };

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


var CentralUnit = {
	hide: function() {
		Mappings.Dom.View.containerCentralUnit.animate({opacity: 0.0}, 0);
		Mappings.Dom.View.loader.animate({opacity: 1.0}, 0);
	},
	show: function() {
		Mappings.Dom.View.containerCentralUnit.animate({opacity: 1.0}, 0);
		Mappings.Dom.View.loader.animate({opacity: 0.0}, 0);
	}
};
