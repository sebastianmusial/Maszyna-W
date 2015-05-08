package pl.polsl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.polsl.architecture.WMachine;
import pl.polsl.architecture.signals.Signal;
import pl.polsl.servlet.ArchitectureInfo.AvailableSignals;

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
		response.setContentType("text/plain");
		Integer signalId = AvailableSignals.valueOf(request.getParameter("signalId")).ID;
		boolean signalEnabled = Boolean.parseBoolean(request.getParameter("signalEnabled"));
		WMachine machine = getCurrentWMachine(request.getSession());
		Signal signal = machine.getSignal(signalId);
		if(signal != null)
			signal.setEnabled(signalEnabled);
	}
}
