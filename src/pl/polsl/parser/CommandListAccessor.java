package pl.polsl.parser;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import pl.polsl.architecture.WMachine;
import pl.polsl.dao.CommandsListDao;
import pl.polsl.runner.CommandList;
import pl.polsl.servlet.WMachineServletBase;
import pl.polsl.storage.CommandStorage;
import pl.polsl.storage.CommandsListStorage;

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
		WMachine machine = getCurrentWMachine(request.getSession());
		Integer listId = Integer.parseInt(request.getParameter("list"));
		Integer commandId = null;
		if("COMMAND".equalsIgnoreCase(request.getParameter("what")))
			commandId = Integer.parseInt(request.getParameter("command"));
		CommandsListDao commandsListDao = new CommandsListDao();
		CommandsListStorage commandList = commandsListDao.getById(listId);
		// TODO command list
		switch(request.getParameter("action").toUpperCase()) {
			case "GET":
				response.setContentType("application/json;charset=UTF-8");
				List<CommandStorage> commands = commandList.getCommands();
				List<SerializableCommand> serializableCommands = new LinkedList<>();
				for(CommandStorage commandStorage : commands) {
					SerializableCommand cmd = new SerializableCommand();
					cmd.name = commandStorage.getCommandName();
					cmd.code = commandStorage.getCommandCode();
					serializableCommands.add(cmd);
				}
				new Gson().toJson(serializableCommands, response.getWriter());
				break;
				
			case "ADD":
				break;
		}
	}
	
	private class SerializableCommand {
		public String name;
		public String code;
	}
}
