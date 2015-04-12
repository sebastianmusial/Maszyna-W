package pl.polsl.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.polsl.architecture.WMachine;
import pl.polsl.architecture.signals.Signal;

import com.google.gson.Gson;

/**
 * Parameters constains list of signals to be activated in current tact.
 * These signals are activated in certain order.
 */
@WebServlet("/TactRunner")
public class TactRunner extends WMachineServletBase {
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Helper class to deserialize state of W Machine read from client.
	 * @author Tomasz Rzepka
	 * @version 1.0
	 */
	private class State {
		/** Signal states. */
		public Map<Integer, Boolean> signals;
		/** Register values. */
		@SuppressWarnings("unused")
		public Map<Integer, Integer> registers;
	}
	
	/**
	 * @see WMachineServletBase#processRequest(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		WMachine machine = getCurrentWMachine(request.getSession());
		Gson gson = new Gson();
		State state = gson.fromJson(request.getParameter("state"), State.class);
		
		machine.nextTact();
		machine.updateScriptContext();
		
		Map<String, String> result = new HashMap<>();
		try {
			for(Integer signalId : state.signals.keySet()) {
				Signal signal = machine.getSignal(signalId);
				if(signal == null)
					continue;
				Boolean signalState = state.signals.get(signalId);
				signal.setEnabled(signalState);
				if(signalState)
					signal.activate();
			}
			result.put("status", "OK");
		}
		catch(Exception ex) {
			// TODO wycofać zmiany wprowadzone w tym takcie
			result.put("status", "ERROR");
			result.put("message", ex.getMessage());
		}
		new Gson().toJson(result, response.getWriter());
	}
}
