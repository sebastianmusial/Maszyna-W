/**
 * 
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

function CommandList() {
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
				get Id() { return commands.prop("data-selected"); },
				set Id(id) { return commands.prop("data-selected", id); }
			},
			CommandList: {
				get Name() { return lists.val(); },
				get Id() { return lists.prop("data-selected"); },
				set Id(id) { return lists.prop("data-selected", id); }
			}
		},
		CommandTabs: {},
		addCommand: CommandTab,
		removeCommand: function(index) {
			content.children("div:not(.fixed)").slice(index, index + 1).remove();
			navbar.children("li:not(.fixed)").slice(index, index + 1).remove();
			commands.children("option:not(.fixed)").slice(index, index + 1).remove();
		},
		clearCommands: function() {
			content.children("div:not(.fixed)").remove();
			navbar.children("li:not(.fixed)").remove();
			commands.children("option:not(.fixed)").remove();
		}
	};
	
	mapping.New.Add.click(function() {
		var name = commandList.New.Name,
			def = commandList.New.Definition;
		if(name != "" && def != "")
			commandList.addCommand({name: name, definition: def});
	});
	
	mapping.Settings.Command.Select.change(function(e) {
		commandList.Settings.Command.Id = (e.target.selectedIndex - 1);
	});
	
	mapping.Settings.Command.Delete.click(function() {
		var commandId = commandList.Settings.Command.Id;
	    if (commandId == -1)
	    	commandList.clearCommands();
	    else {
	    	commandList.removeCommand(commandId);
	    	commands.change();
	    }
	});
	
	return commandList;
}

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
				Select: $("#selectCommandList", settingsTab),
				Load: $("#loadCommandList", settingsTab)
			}
		}
	};
	
	MW.Commands = CommandList();
	
	var args = {
		action: "get",
		what: "list",
		list: 0
	};
	
	$.get("CommandListAccessor", args, function(commandList) {
		var command;
		for(key in commandList) {
			command = commandList[key];
			MW.Commands.addCommand(command);
		}
	});
}