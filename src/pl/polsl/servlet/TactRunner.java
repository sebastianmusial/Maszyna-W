package pl.polsl.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import pl.polsl.architecture.WMachine;
import pl.polsl.architecture.signals.Signal;
import pl.polsl.servlet.ArchitectureInfo.AvailableRegisters;
import pl.polsl.servlet.ArchitectureInfo.AvailableSignals;

/**
 * Parameters constains list of signals to be activated in current tact.
 * These signals are activated in certain order.
 */
@WebServlet("/TactRunner")
public class TactRunner extends WMachineServletBase {
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;

	private class State {
		public Map<Integer, Boolean> signals;
		public Map<Integer, Integer> registers;
	}
	
	/**
	 * @see WMachineServletBase#processRequest(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		WMachine machine = getCurrentWMachine(request.getSession());
		String st = request.getParameter("state");
		Gson gson = new Gson();
		State state = gson.fromJson(request.getParameter("state"), State.class);
		
		machine.nextTact();
		machine.updateScriptContext();
		
		Integer cellAddress = 0;
		Integer cellValue = 0;
		Map<String, String> result = new HashMap<>();
		try {
			for(Integer signalId : state.signals.keySet()) {
				Signal signal = machine.getSignal(signalId);
				if(signal == null)
					continue;
				Boolean signalState = state.signals.get(signalId);
				signal.setEnabled(signalState);
				if(signalState) {
					if(signalId == AvailableSignals.MEMORY_WRITE.ID) {
						cellAddress = machine.getRegister(AvailableRegisters.MEMORY_ADDRESS.ID).getValue();
						cellValue = machine.getMemory().getValue(cellAddress);
					}
					signal.activate();
				}
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
