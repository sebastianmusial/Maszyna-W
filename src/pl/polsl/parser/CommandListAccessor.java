package pl.polsl.parser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import pl.polsl.architecture.WMachine;
import pl.polsl.database.CommandStorage;
import pl.polsl.runner.CommandList;
import pl.polsl.runner.command.Command;
import pl.polsl.servlet.WMachineServletBase;
import pl.polsl.settings.Settings;

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
		CommandList commandList = machine.getCommandList();
		Integer listId = Integer.parseInt(request.getParameter("list"));
		Integer commandId = null;
		if("COMMAND".equalsIgnoreCase(request.getParameter("what")))
			commandId = Integer.parseInt(request.getParameter("command"));
		
		switch(request.getParameter("action").toUpperCase()) {
			case "GET":
				CommandStorage storage = new CommandStorage();
				response.setContentType("application/json;charset=UTF-8");
				new Gson().toJson(storage.readCommandList(listId), response.getWriter());
				break;
				
			case "ADD":
				break;
		}
	}
}
