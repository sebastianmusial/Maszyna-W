'use strict';

var MW = MW || {};

/**
 * Vars
 */
MW.vars = {
    containerCentralUnit: $('#central-unit')
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
    if( MW.vars.containerCentralUnit.length ) {
        MW.centralUnit(MW.vars.containerCentralUnit);
    }
};


/*
 *	All components for application
 */
MW.centralUnit = function(containerCentralUnit) {
    var paper = Raphael("central-unit");

    //counter
    var counter = function() {
        var welArrow = MW.setArrow(paper, 'M120 0 V50', true),
            wylArrow = MW.setArrow(paper, 'M80 0 V50', false),
            ilArrow = MW.setArrow(paper, 'M30 65 H50', true),
            welText = MW.setText(paper, 'wel', 140, 25, welArrow),
            wylText = MW.setText(paper, 'wyl', 60, 25, wylArrow),
            ilText = MW.setText(paper, 'il', 20, 65, ilArrow);
    };

    //acumulator
    var acumulator = function() {
        var wejaArrow = MW.setArrow(paper, 'M450 300 V250', true),
            wejaText = MW.setText(paper, 'weja', 425, 275, wejaArrow),
            wyakArrow = MW.setArrow(paper, 'M550 300 V75, 75 H450, 450 V100', false),
            wyakText = MW.setText(paper, 'wyak', 575, 180, wyakArrow),
            weakArrow = MW.setArrow(paper, 'M345 150 H370', true),
            weakText = MW.setText(paper, 'weak', 325, 150, weakArrow),
            dodArrow = MW.setArrow(paper, 'M345 170 H370', true),
            dodText = MW.setText(paper, 'dod', 327, 170, dodArrow),
            odeArrow = MW.setArrow(paper, 'M345 190 H370', true),
            odeText = MW.setText(paper, 'ode', 328, 190, odeArrow),
            przepArrow = MW.setArrow(paper, 'M345 210 H370', true),
            przepText = MW.setText(paper, 'przep', 324, 210, przepArrow);
    };


    //instruction register
    var instructionRegister = function() {
        var weiArrow = MW.setArrow(paper, 'M145 500 V250', true),
            weiText = MW.setText(paper, 'wei', 120, 275, weiArrow),
            wyadArrow = MW.setArrow(paper, 'M195 0 V250', false),
            wyadText = MW.setText(paper, 'wyad', 170, 190, wyadArrow);
    };

    //Memory
    var memory = function() {
        var wesArrow = MW.setArrow(paper, 'M730 500 V250', true),
            wesText = MW.setText(paper, 'wes', 750, 275, wesArrow),
            wysArrow = MW.setArrow(paper, 'M690 300 V50', false),
            wysText = MW.setText(paper, 'wys', 670, 275, wysArrow),
            weaArrow = MW.setArrow(paper, 'M710 0 V50', true),
            weaText = MW.setText(paper, 'wea', 730, 25, weaArrow),
            czytArrow = MW.setArrow(paper, 'M820 140 H800', true),
            czytText = MW.setText(paper, 'czyt', 835, 140, czytArrow),
            piszArrow = MW.setArrow(paper, 'M820 180 H800', true),
            piszText = MW.setText(paper, 'pisz', 835, 180, piszArrow);
    };

    //Drawing
    counter();
    acumulator();
    instructionRegister();
    memory();

	MW.editInputValue();
};


/**
 * Set label with commands on central unit canvas. Click and hover action.
 * @param {svg canvas} paper
 * @param {string} command - command name
 * @param {int} posX    
 * @param {int} posY 
 * @param {svg element} arrow for command
 */
MW.setText = function(paper, command, posX, posY, arrow) {
    var label = paper.text(0, 0, command),
        checkbox = $('#handControls');

    //Command attributes
    label
        .attr({ x: posX, y: posY})
        .attr(MW.attr.textCommand);

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
                .hover(hoverIn, hoverOut)
                .click(arrowAction);
        }
        else {
            label
                .attr({'cursor': 'default'})
                .animate(MW.attr.inactiveCommand, 250)
                .unclick(MW.clickHandler)
                .unhover(hoverIn, hoverOut)
                .unclick(arrowAction);

            arrow
                .animate(MW.attr.inactiveArrow, 250);
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
MW.setArrow = function(paper, position, isWe) {
    var arrow = paper.path(position);

    if(isWe) {
        arrow.attr(MW.attr.weCommand);
    }
    else {
        arrow.attr(MW.attr.wyCommand);
    }

    return arrow;
};


/**
 * Click action for commands
 */
MW.clickHandler = function() {
    var attrOff = MW.attr.inactiveCommand;
    var attrOn = MW.attr.activeCommand;

    //animation effect
    if (this.data('status') == 1) {
        this.animate( attrOff, 250 ).data('status', 0 );
    }
    else {
        this.animate( attrOn, 250 ).data('status', 1 );
    }
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


/**
 * Running JS functions.
 */
$(document).ready(function() {
    MW.init();
});
