package pl.polsl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.polsl.architecture.Signal;
import pl.polsl.architecture.WMachine;

/**
 * Allow to access signals from client side.
 * @author Tomasz Rzepka
 * @version 1.0
 */
@WebServlet("/SignalAccessor")
public class SignalAccessor extends WMachineServletBase {
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @see WMachineServletBase#processRequest(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer signalId = Integer.parseInt(request.getParameter("signalId"));
		boolean signalEnabled = Boolean.parseBoolean(request.getParameter("signalEnabled"));
		WMachine machine = getCurrentWMachine(request.getSession());
		Signal signal = machine.getSignal(signalId);
		signal.setEnabled(signalEnabled);
	}
}
