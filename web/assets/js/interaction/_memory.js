/**
 * TODO rozdzielić na pliki _registers.js, _signals.js i _memory.js
 */

function MemoryCell(index) {
	var row = $("#memory-row-" + index);
	var input = $("#memory-cell-" + index + "-value");
	var text = $("#memory-cell-" + index + "-text");
	var cell = {
		get isVisible() { return row[0].style.display != "none"; },
		set isVisible(v) { 
			if(v)
				row[0].style.display = "block";
			else
				row[0].style.display = "none";
		},
		get index() { return index; },
		get value() { return input.prop("value"); },
		set value(v) { input.prop("value", v); },
		get text() { return text.html(); },
		set text(v) { text.html(v); }
	};
	input.change(function() {
		var args = { action: "set", index: index, value: cell.value };
		$.get("MemoryAccessor", args, function(cellData) {
			cell.value = cellData.value;
			cell.text = cellData.text;
		});
	});
	MW.Memory[index] = cell;
	return cell;
}

var lastInitializedMemoryCellIndex = 0;
function initMemoryPreview() {
	MW.Memory = [];
	var i;
	for(i = 0; i < 8; ++i) {
		MW.Memory[i] = MemoryCell(i);
	}
	lastInitializedMemoryCellIndex = i;
};

/**
 * Initialize rest of memory cells (those that are not visible).
 * Must be called after settings are initialized because
 * it depends on address bit count set in settings.
 */
function initMemoryLeft() {
	var memorySize = (1 << Mappings.Dom.Settings.AddressBitCount.prop('value'));
	var i, start = lastInitializedMemoryCellIndex, cell;
	for(i = start; i < start + 16 && i < 512; ++i) {
		appendMemoryCell(i);
		cell = MemoryCell(i);
		MW.Memory[i] = cell;
		cell.isVisible = (i < memorySize);
	}
	lastInitializedMemoryCellIndex = i;
	if(i < 512)
		setTimeout(initMemoryLeft, 25);
};

function appendMemoryCell(index) {
	var cellHtml =
		"<div id=\"memory-row-" + index + "\" class=\"memory__table--row\">" +
		"	<div class=\"memory__table--cell\">" + index + "</div>" +
		"	<input id=\"memory-cell-" + index + "-value\" class=\"memory__table__data js-only-numbers js-quick-edit\" value=\"0\">" +
		"	<div id=\"memory-cell-" + index + "-text\" class=\"memory__table--cell\">stp 0</div>" +
		"</div>";
	Mappings.Dom.View.memoryTable.append(cellHtml);
}