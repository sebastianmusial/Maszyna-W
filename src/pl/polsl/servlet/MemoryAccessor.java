package pl.polsl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.polsl.architecture.Memory;
import pl.polsl.architecture.WMachine;

/**
 * Allow to access memory from client side.
 * @author Tomasz Rzepka
 * @version 1.0
 */
@WebServlet("/MemoryAccessor")
public class MemoryAccessor extends WMachineServletBase {
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @see WMachineServletBase#processRequest(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer index = Integer.parseInt(request.getParameter("index"));
		Integer value = Integer.parseInt(request.getParameter("value"));
		WMachine machine = getCurrentWMachine(request.getSession());
		Memory memory = machine.getMemory();
		memory.setValue(index, value);
	}
}
