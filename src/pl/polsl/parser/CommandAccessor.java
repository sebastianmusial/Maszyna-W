package pl.polsl.parser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.polsl.architecture.WMachine;
import pl.polsl.runner.CommandList;
import pl.polsl.runner.command.Command;
import pl.polsl.servlet.WMachineServletBase;
import pl.polsl.settings.Settings;

/**
 * Servlet implementation class CommandAccessor
 */
@WebServlet("/CommandAccessor")
public class CommandAccessor extends WMachineServletBase {
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @see WMachineServletBase#processRequest(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WMachine machine = getCurrentWMachine(request.getSession());
		Settings settings = getCurrentSettings(request.getSession());
		CommandList commandList = settings.getCommandList();
		
		switch(request.getParameter("action")) {
			case "run":
				String name = request.getParameter("command");
				Command command = commandList.getCommandByName(name);
				command.run(machine);
				break;
				
			case "add":
//				CommandParser parser = new CommandParser();
//				Command command = parser.parseText(request.getParameter("command"));
//				commandList.add(command);
				break;
		}
	}
}
