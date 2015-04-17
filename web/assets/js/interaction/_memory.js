/**
 * TODO rozdzielić na pliki _registers.js, _signals.js i _memory.js
 */

var initMemoryCell = function(index) {
	var row = $("#memory-row-" + index);
	var input = $("#memory-cell-" + index + "-value");
	var text = $("#memory-cell-" + index + "-text");
	MW.Memory[i] = {
		row: row,
		get value() { return input.prop('value'); },
		set value(v) { input.prop('value', v); }
	};
	input.change(function() {
		$.get("MemoryAccessor", {index: index, value: input.prop("value")});
	});
};


// Init interaction.
var initMemory = function() {
};