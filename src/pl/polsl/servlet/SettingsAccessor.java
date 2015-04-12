package pl.polsl.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.polsl.architecture.WMachine;
import pl.polsl.architecture.components.finalized.Register;
import pl.polsl.settings.Architecture;
import pl.polsl.settings.Extension;
import pl.polsl.settings.Settings;
import pl.polsl.settings.TrackLevel;

import com.google.gson.Gson;

/**
 * Allow to access settings from client side.
 * @author Tomasz Rzepka
 * @version 1.0
 */
@WebServlet("/SettingsAccessor")
public class SettingsAccessor extends WMachineServletBase implements Servlet {
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;

    
	/**
	 * @see WMachineServletBase#processRequest(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		WMachine machine = getCurrentWMachine(session);
		Settings settings = getCurrentSettings(session);
		String action = request.getParameter("action");
		
		if("get".equalsIgnoreCase(action)) {
			Map<String, Object> settingsMap = new HashMap<String, Object>();
			settingsMap.put("ManualControl", settings.isManualControlEnabled());
			settingsMap.put("TrackLevel", settings.getTrackLevel());
			settingsMap.put("Architecture", settings.getArchitecture());
			settingsMap.put("Extensions", settings.getExtensions());
			settingsMap.put("AddressBitCount", machine.getAddressBitCount());
			settingsMap.put("OpCodeBitCount", machine.getDataBitCount() - machine.getAddressBitCount());
			settingsMap.put("InputPortAddress", machine.getInputPortAddress());
			settingsMap.put("OutputPortAddress", machine.getOutputPortAddress());
			
			new Gson().toJson(settingsMap, response.getWriter());
		}
		
		else if("set".equalsIgnoreCase(action)) {
			String value = request.getParameter("value");
			switch(request.getParameter("what")) {
				case "ManualControl":
					settings.setManualControlEnabled(Boolean.parseBoolean(value));
					break;
				case "TrackLevel":
					settings.setTrackLevel(TrackLevel.valueOf(value));
					break;
				case "Architecture":
					Architecture architecture = Architecture.valueOf(value); 
					settings.setArchitecture(architecture);
					if(architecture != Architecture.USER_DEFINED) {
						List<Extension> architectureExtensions = Arrays.asList(Architecture.getExtensions(architecture));
						for(Extension extension : Extension.values())
							settings.setExtensionEnabled(extension, architectureExtensions.contains(extension));
						new Gson().toJson(settings.getExtensions(), response.getWriter());
					}
					break;
				case "Extensions":
					String option = request.getParameter("option");
					settings.setExtensionEnabled(Extension.valueOf(option), Boolean.parseBoolean(value));
					break;
				case "AddressBitCount":
					machine.setAddressBitCount(Integer.parseInt(value));
					new Gson().toJson(getRegisterValues(machine), response.getWriter());
					break;
				case "OpCodeBitCount":
					machine.setDataBitCount(Integer.parseInt(value) + machine.getAddressBitCount());
					new Gson().toJson(getRegisterValues(machine), response.getWriter());
					break;
				case "InputPortAddress":
					machine.setInputPortAddress(Integer.parseInt(value));
					break;
				case "OutputPortAddress":
					machine.setOutputPortAddress(Integer.parseInt(value));
					break;
				default: break;
			}
		}
	}
	
	/**
	 * Helper function to obtain map where key is ID of register
	 * and mapped value is value stored in the register.
	 * @param machine W Machine instance with registers
	 * @return Collection mapping register ID to value stored in that register.
	 */
	protected Map<Integer, Integer> getRegisterValues(WMachine machine) {
		Map<Integer, Integer> values = new HashMap<>();
		for(Integer registerId : ArchitectureInfo.getRegisterIds()) {
			Register register = machine.getRegister(registerId);
			if(register != null) {
				try {
					values.put(registerId, register.getValue());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return values;
	}
}
