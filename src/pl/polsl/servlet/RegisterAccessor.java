package pl.polsl.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import pl.polsl.architecture.WMachine;
import pl.polsl.architecture.components.finalized.Register;
import pl.polsl.servlet.ArchitectureInfo.AvailableRegisters;

/**
 * Allow to access registers from client side.
 * @author Tomasz Rzepka
 * @version 1.0
 */
@WebServlet("/RegisterAccessor")
public class RegisterAccessor extends WMachineServletBase {
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
    
	/** Current WMachine instance. */
	private WMachine machine;
	
	/** Current output writer. */
	private PrintWriter out;
	
	/** Current register ID. */
	private String textId;
	
	/**
	 * @see WMachineServletBase#processRequest(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		machine = getCurrentWMachine(request.getSession());
		out = response.getWriter();
		textId = request.getParameter("registerId");
		switch(request.getParameter("action")) {
			case "get":
				if(textId == null || textId.equals(""))
					getAllRegisters(request, response);
				else
					getSingleRegister(request, response);
				break;
				
			case "set":
				setSingleRegister(request, response);
				break;
				
			default:
				response.setContentType("text/plain");
		}
	}
	
	/**
	 * Return in response all registers values.
	 * @param request servlet request
     * @param response servlet response
	 */
	protected void getAllRegisters(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/json");
		HashMap<AvailableRegisters, Integer> registers = new HashMap<>();
		for(AvailableRegisters registerId : AvailableRegisters.values()) {
			try {
				Register register = (Register)machine.getRegister(registerId.ID);
				if(register != null)
					registers.put(registerId, register.getValue());
			} catch (Exception e) {
				// will never enter this catch block
			}
		}
		new Gson().toJson(registers, out);
	}
	
	/**
	 * Return in response single registers value.
	 * @param request servlet request
     * @param response servlet response
	 */
	protected void getSingleRegister(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/plain");
		Register register = getRegister();
		if(register == null)
			return;
		try {
			out.println(register.getValue());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Set single register value and return masked value in response.
	 * @param request servlet request
     * @param response servlet response
	 */
	protected void setSingleRegister(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/plain");
		Register register = getRegister();
		if(register == null)
			return;
		String value = request.getParameter("value");
		try {
			if(value != null)
				register.setValue(Integer.parseInt(value));
			out.println(register.getValue());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Helper to get register depending on request parameter.
	 * @return W Machine register pointed in request.
	 */
	protected Register getRegister() {
		AvailableRegisters registerId = AvailableRegisters.valueOf(textId);
		return (Register)machine.getRegister(registerId.ID);
	}
}
