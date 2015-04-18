/**
 * TODO rozdzielić na pliki _registers.js, _signals.js i _memory.js
 */

function MemoryCell(index) {
	var row = $("#memory-row-" + index);
	var input = $("#memory-cell-" + index + "-value");
	var text = $("#memory-cell-" + index + "-text");
	var cell = {
		get index() { return index; },
		get value() { return input.attr("value"); },
		set value(v) { input.attr("value", v); },
		get text() { return text.html(); },
		set text(v) { text.html(v); }
	};
	return cell;
}

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
function initMemory() {
	for(var i = 0; i < 8; ++i) {
		MemoryCell(i).text = "stp 1";
	}
};