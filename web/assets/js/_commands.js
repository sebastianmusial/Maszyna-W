/**
 * Create new command tab.
 */
function CommandTab(data) {
	var mapping = Mappings.Dom.Commands,
		contentHtml = '\
			<div class="tab-pane fade" id="' + data.name + '-content"> \
				<textarea class="form-control form-space" rows="3" placeholder="Kod rozkazu" disabled>' + data.code + '</textarea> \
				<div class="button-group edit-group"> \
					<button type="button" class="btn btn-default tip edit" data-toggle="tooltip" data-original-title="Edytuj"><i class="glyphicon glyphicon-edit"></i></button> \
				</div> \
				<div class="button-group confirm-group"> \
					<button type="button" class="btn btn-default tip save" data-toggle="tooltip" data-original-title="Zapisz"><i class="glyphicon glyphicon-floppy-saved"></i></button> \
					<button type="button" class="btn btn-default tip cancel" data-toggle="tooltip" data-original-title="Anuluj"><i class="glyphicon glyphicon-remove"></i></button> \
				</div> \
			</div>',
		linkHtml = '<li><a href="#' + data.name + '-content" data-toggle="tab">' + data.name + '</a></li>',
		optionHtml = '<option>' + data.name + '</option>';
	mapping.Content.append(contentHtml);
	mapping.Navbar.append(linkHtml);
	mapping.Settings.Command.Select.append(optionHtml);
	
	var content = mapping.Content.children().last(),
		textarea = $("textarea", content),
		editButtonGroup = $(".edit-group", content),
		editButton = $(".edit", editButtonGroup),
		confirmButtonGroup = $(".confirm-group", content),
		saveButton = $(".save", confirmButtonGroup),
		cancelButton = $(".cancel", confirmButtonGroup);
	editButtonGroup.show();
	confirmButtonGroup.hide();
	editButton.click(function() {
		textarea.prop("disabled", false);
		editButton.blur();
		editButtonGroup.hide(200);
		confirmButtonGroup.show(200);
	});
	saveButton.click(function() {
		textarea.prop("disabled", true);
		saveButton.blur();
		editButtonGroup.show(200);
		confirmButtonGroup.hide(200);
	});
	cancelButton.click(function() {
		textarea.prop("disabled", true);
		cancelButton.blur();
		editButtonGroup.show(200);
		confirmButtonGroup.hide(200);
	});
	$(".tip", content).tooltip();
}

/**
 * Update mappings.
 * @returns mappings for "Commands" tab.
 */
function CommandsTab() {
	var mapping = Mappings.Dom.Commands,
		navbar = mapping.Navbar,
		content = mapping.Content,
		commands = mapping.Settings.Command.Select,
		lists = mapping.Settings.CommandList.Select;
	
	var commandList = {
		New: {
			get Name() { return mapping.New.Name.val(); },
			get Definition() { return mapping.New.Definition.val(); }
		},
		Settings: {
			Command: {
				get Name() { return commands.val(); },
				get CurrentIndex() { return commands.prop("data-selected"); },
				set CurrentIndex(id) { commands.prop("data-selected", id); },
				clear: function() {
					content.children("div:not(.fixed)").remove();
					navbar.children("li:not(.fixed)").remove();
					commands.children("option:not(.fixed)").remove();
				},
				add: CommandTab
			},
			CommandList: {
				get Name() { return lists.val(); },
				get Id() {
					index = lists.prop("data-selected");
					option = $("option", lists).eq(index)
					console.log("option data-index is " + option.attr("data-index"))
					return option.attr("data-index") || 1;
				},
				get CurrentIndex() { return lists.prop("data-selected"); },
				set CurrentIndex(id) { lists.prop("data-selected", id); },
				clear: function() {
					select = Mappings.Dom.Commands.Settings.CommandList.Select;
					select.children().remove();
				}
			}
		}
	};
	
	mapping.New.Add.click(function() {
		var name = commandList.New.Name,
			def = commandList.New.Definition;
		if(name != "" && def != "") {
			var args = {
				action: "add",
				what: "command",
				list: commandList.Settings.CommandList.Id,
				name: name,
				code: def
			};
			
			$.get("CommandListAccessor", args, function() {
				commandList.Settings.Command.clear();
				readCurrentCommandList();
			});
			//commandList.addCommand({name: name, definition: def});
		}
	});
	
	mapping.Settings.Command.Select.change(function(e) {
		commandList.Settings.Command.CurrentIndex = e.target.selectedIndex;
	});
	
	mapping.Settings.Command.Delete.click(function() {
		var args = {
			action: "delete",
			list: MW.Commands.Settings.CommandList.Id,
			what: "command",
			command: commandList.Settings.Command.Name
		};
	    $.get("CommandListAccessor", args, function() {
	    	commandList.Settings.Command.clear();
	    	readCurrentCommandList();
	    });
	});
	
	mapping.Settings.CommandList.Select.change(function(e) {
		commandList.Settings.CommandList.CurrentIndex = e.target.selectedIndex;
		commandList.Settings.Command.clear();
		readCurrentCommandList();
	});
	
	mapping.Settings.CommandList.Delete.click(function() {
		var args = {
			action: "delete",
			list: MW.Commands.Settings.CommandList.Id,
			what: "list"
		};
	    $.get("CommandListAccessor", args, function() {
	    	commandList.Settings.Command.clear();
    		commandList.Settings.CommandList.clear();
    		readCommandsLists().done(readCurrentCommandList);
    		MW.Commands.Settings.CommandList.CurrentIndex = 0;
    		MW.Commands.Settings.Command.CurrentIndex = 0;
	    });
	});
	
	mapping.Settings.CommandList.Empty.click(function() {
		var name = mapping.Settings.CommandList.Name.val();
		if(name != "") {
			var args = {
				action: "add",
				what: "list",
				name: name
			};
			$.get("CommandListAccessor", args, function() {
				commandList.Settings.Command.clear();
	    		commandList.Settings.CommandList.clear();
	    		readCommandsLists().done(readCurrentCommandList); 
	    		MW.Commands.Settings.CommandList.CurrentIndex = $("option", mapping.Settings.CommandList.Select).size() - 1;
	    		MW.Commands.Settings.Command.CurrentIndex = 0;
			});
		}
	});
	
	mapping.Settings.CommandList.Clone.click(function() {
		var name = mapping.Settings.CommandList.Name.val();
		if(name != "") {
			var args = {
				action: "clone",
				what: "list",
				list: commandList.Settings.CommandList.Id,
				name: name
			};
			$.get("CommandListAccessor", args, function() {
				commandList.Settings.Command.clear();
	    		commandList.Settings.CommandList.clear();
	    		readCommandsLists().done(readCurrentCommandList); 
	    		MW.Commands.Settings.CommandList.CurrentIndex = $("option", mapping.Settings.CommandList.Select).size() - 1;
	    		MW.Commands.Settings.Command.CurrentIndex = 0;
			});
		}
	});
	
	return commandList;
}

/**
 * Read currently selected commands list from server
 * and show it in browser.
 */
function readCurrentCommandList() {
	var args = {
		action: "get",
		what: "list",
		list: String(MW.Commands.Settings.CommandList.Id)
	};
	console.log(MW.Commands.Settings.CommandList.Id)
	
	$.get("CommandListAccessor", args, function(commandList) {
		var command;
		for(key in commandList) {
			command = commandList[key];
			MW.Commands.Settings.Command.add(command);
		}
	});
}

/**
 * Read all available commands lists from server
 * and show them in browser.
 */
function readCommandsLists() {
	var args = {
		action: "enum",
		what: "list"
	};

	return $.get("CommandListAccessor", args, function(commandLists) {
		var commandList,
			select = Mappings.Dom.Commands.Settings.CommandList.Select;
		for(key in commandLists) {
			commandList = commandLists[key];
			optionHtml = '<option data-index="' + commandList.id + '">' + commandList.name + '</option>';
			select.append(optionHtml);
		}
	});
}

/**
 * Create mappings, load available lists and show default list.
 */
function initCommands() {
	var commandsTab = $("#commands"),
		newCommandTab = $("#newCommand", commandsTab),
		settingsTab = $("#settingsCommand", commandsTab);
	
	Mappings.Dom.Commands = {
		Navbar: $(".nav-tabs", commandsTab),
		Content: $(".tab-content", commandsTab),
		New: {
			Name: $("input", newCommandTab),
			Definition: $("textarea", newCommandTab),
			Add: $("button", newCommandTab)
		},
		Settings: {
			Command: {
				Select: $("#selectCommand", settingsTab),
				Delete: $("#deleteCommand", settingsTab)
			},
			CommandList: {
				Select: $("#selectCommandsList", settingsTab),
				Delete: $("#deleteCommandsList", settingsTab),
				Clone: $("#cloneCurrentCommandsList", settingsTab),
				Empty: $("#createEmptyCommandsList", settingsTab),
				Name: $("#newCommandsListName", settingsTab)
			}
		}
	};
	
	MW.Commands = CommandsTab();
	readCommandsLists().done(function() {
		MW.Commands.Settings.CommandList.CurrentIndex = 0
		readCurrentCommandList();
	});
}