package pl.polsl.parser;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import pl.polsl.architecture.WMachine;
import pl.polsl.dao.CommandDao;
import pl.polsl.dao.CommandsListDao;
import pl.polsl.dao.UserDao;
import pl.polsl.runner.CommandList;
import pl.polsl.servlet.WMachineServletBase;
import pl.polsl.storage.CommandStorage;
import pl.polsl.storage.CommandsListStorage;
import pl.polsl.storage.UserStorage;

/**
 * Servlet implementation class CommandAccessor
 */
@WebServlet("/CommandListAccessor")
public class CommandListAccessor extends WMachineServletBase {
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see WMachineServletBase#processRequest(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		CommandsListDao commandsListDao = new CommandsListDao();
		switch(request.getParameter("action").toUpperCase()) {
			case "GET": {
				response.setContentType("application/json;charset=UTF-8");
				Long listId = Long.parseLong(request.getParameter("list"));
				CommandsListStorage commandList = commandsListDao.getById(listId);
				List<SerializableCommand> serializableCommands = new LinkedList<>();
				for(CommandStorage commandStorage : commandList.getCommands()) {
					SerializableCommand cmd = new SerializableCommand();
					cmd.name = commandStorage.getCommandName();
					cmd.code = commandStorage.getCommandCode();
					serializableCommands.add(cmd);
				}
				new Gson().toJson(serializableCommands, response.getWriter());
			} break;
				
			case "ENUM": {
				response.setContentType("application/json;charset=UTF-8");
				Long userId = getCurrentUserId();
				CommandsListStorage defaultCommandsList = commandsListDao.getById(1L);
				List<CommandsListStorage> commandsLists = new LinkedList<CommandsListStorage>();
				if(defaultCommandsList != null && (userId == null || (userId != null && userId != 1L)))
					commandsLists.add(defaultCommandsList);
				if(userId != null) {
					commandsLists.addAll(commandsListDao.getUserCommandsLists(userId));
				}
				List<SerializableCommandList> serializableCommandsLists = new LinkedList<>();
				for(CommandsListStorage commandsListStorage : commandsLists) {
					SerializableCommandList cmd = new SerializableCommandList();
					cmd.name = commandsListStorage.getName();
					cmd.id = commandsListStorage.getCommandListID();
					serializableCommandsLists.add(cmd);
				}
				new Gson().toJson(serializableCommandsLists, response.getWriter());
			} break;
				
			case "DELETE": {
				Long listId = Long.parseLong(request.getParameter("list"));
				if(listId == 1L) // ignore default list changes
					return;
				String what = request.getParameter("what").toUpperCase();
				CommandsListStorage commandsList = commandsListDao.getById(listId);
				if(what.equals("LIST")) {
					commandsListDao.delete(commandsList);
				}
				else if(what.equals("COMMAND")) {
					String commandName = request.getParameter("command");
					commandsListDao.deleteCommand(commandsList, commandName);
				}
			} break;
			
			case "ADD": {
				UserStorage user = getCurrentUser();
				if(user == null)
					return;
				Long userId = user.getUserID();
				String what = request.getParameter("what").toUpperCase();
				switch(what) {
					case "COMMAND": {
						Long listId = Long.parseLong(request.getParameter("list"));
						String name = request.getParameter("name");
						String code = request.getParameter("code");
						CommandsListStorage commandsList = commandsListDao.getById(listId);
						commandsList.getCommandListID();
						List<CommandStorage> commands = commandsList.getCommands();
						CommandStorage newCommand = new CommandStorage();
						newCommand.setArguments((byte)0);
						newCommand.setCommandCode(code);
						newCommand.setCommandName(name);
						newCommand.setCommandIndex(commands.size());
						newCommand.setCommandsList(commandsList);
						new CommandDao().save(newCommand);
						commandsList.getCommands();
						//commands.add(newCommand);
						//commandsList.setCommands(commands);
						//commandsListDao.save(commandsList);
					} break;
					case "LIST": {
//						var args = {
//								action: "add",
//								what: "list",
//								name: name
//							};
						String name = request.getParameter("name");
						CommandsListStorage emptyCommandsList = new CommandsListStorage();
						emptyCommandsList.setIsPublic((byte)0);
						emptyCommandsList.setName(name);
						emptyCommandsList.setUser(user);
						commandsListDao.save(emptyCommandsList);
					} break;
				}
			} break;
			
			case "CLONE": {
				UserStorage user = getCurrentUser();
				if(user == null)
					return;
				switch(request.getParameter("what").toUpperCase()) {
					case "LIST": {
//						var args = {
//								action: "clone",
//								what: "list",
//								list: commandList.Settings.CommandList.Id,
//								name: name
//							};
						String name = request.getParameter("name");
						Long listId = Long.parseLong(request.getParameter("list"));
						CommandsListStorage sourceCommandsList = commandsListDao.getById(listId);
						CommandsListStorage clonedCommandsList = new CommandsListStorage();
						clonedCommandsList.setIsPublic((byte)0);
						clonedCommandsList.setName(name);
						clonedCommandsList.setUser(user);
						List<CommandStorage> clonedCommands = new LinkedList<CommandStorage>();
						for(CommandStorage sourceCommand : sourceCommandsList.getCommands()) {
							CommandStorage clonedCommand = new CommandStorage();
							clonedCommand.setArguments(sourceCommand.getArguments());
							clonedCommand.setCommandCode(sourceCommand.getCommandCode());
							clonedCommand.setCommandIndex(sourceCommand.getCommandIndex());
							clonedCommand.setCommandName(sourceCommand.getCommandName());
							clonedCommand.setCommandsList(clonedCommandsList);
							clonedCommands.add(clonedCommand);
						}
						clonedCommandsList.setCommands(clonedCommands);
						commandsListDao.save(clonedCommandsList);
					} break;
				}
			} break;
		}
	}
	
	private class SerializableCommand {
		public String name;
		public String code;
	}
	
	private class SerializableCommandList {
		public String name;
		public Long id;
	}
}
