/**
 * 
 */

function MemoryCell(index) {
	var row = $("#memory-row-" + index);
	var input = $(".memory-cell-value", row);
	var text = $(".memory-cell-text", row);
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
	return cell;
}

function memoryCellHtml(index) {
	return	"<div id='memory-row-" + index + "' class='memory__table--row'> \
				<div class='memory__table--cell memory-cell-index'>" + index + "</div> \
				<input class='memory__table__data js-only-numbers js-quick-edit memory-cell-value' value='0'> \
				<div class='memory__table--cell memory-cell-text'>stp 0</div> \
			</div>";
}

function initMemory() {
	var actualSize = 32;
	MW.Memory = {
		get ActualSize() { return actualSize; },
		get DesiredSize() { return (1 << Mappings.Dom.Settings.AddressBitCount.prop('value')); },
		get MaxSize() { return 512; },
		Cells: [],
		resize: function(newMemorySize) {
			var i, oldMemorySize = actualSize;
			if(oldMemorySize < newMemorySize) {
				for(i = oldMemorySize; i < newMemorySize; ++i) {
					MW.Memory.Cells[i].isVisible = true;
					MW.Memory.Cells[i].value = 0;
					MW.Memory.Cells[i].text = "stp 0";
				}
				actualSize = newMemorySize;
			}
			else if(oldMemorySize > newMemorySize) {
				for(i = newMemorySize; i < oldMemorySize; ++i)
					MW.Memory.Cells[i].isVisible = false;
				actualSize = newMemorySize;
			}
		}
	};

	var maxMemorySize = MW.Memory.MaxSize,
		desiredMemorySize = MW.Memory.DesiredSize;
		index = 0,
		memoryHtml = [],
		initializer = {
			step: function(value) {
				var i;
				for(i = value; i < value + 8 && i < maxMemorySize; ++i)
					memoryHtml.push(memoryCellHtml(i));
				return i;
			},
			finished: function(value) { return value >= maxMemorySize; },
		};
	
	return runInBackground(initializer, 0, 10).done(function() {
		Mappings.Dom.View.memoryTable.html(memoryHtml.join("\n"));
		var i, cell;
		for(i = 0; i < 512; ++i) {
			cell = MemoryCell(i);
			cell.isVisible = false;
			MW.Memory.Cells[i] = cell;
		}
	});
}
