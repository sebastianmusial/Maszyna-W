package pl.polsl.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.polsl.architecture.WMachine;

/**
 * Parameters constains list of signals to be activated in current tact.
 * These signals are activated in certain order.
 */
@WebServlet("/TactRunner")
public class TactRunner extends WMachineServletBase {
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @see WMachineServletBase#processRequest(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WMachine machine = getCurrentWMachine(request.getSession());
		String signalsString = request.getParameter("signals");
		Map<Integer, String> signals = new HashMap<>();
		
		int count = 0;
		for(String signalName : signalsString.split(",")) {
			// TODO: get signal priority (id) from DB and insert a pair into map
			signals.put(count++, signalName);
		}
		
		try {
//			for(Integer signalId : signals.keySet())
//				machine.activateSignal(signals.get(signalId));
		}
		catch(Exception ex) {
			
		}
	}
}
