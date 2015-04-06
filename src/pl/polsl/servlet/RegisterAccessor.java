package pl.polsl.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.polsl.architecture.Register;
import pl.polsl.architecture.WMachine;

/**
 * Allow to access registers from client side.
 * @author Tomasz Rzepka
 * @version 1.0
 */
@WebServlet("/RegisterAccessor")
public class RegisterAccessor extends WMachineServletBase {
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see WMachineServletBase#processRequest(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WMachine machine = getCurrentWMachine(request.getSession());
		PrintWriter out = response.getWriter();
		Integer registerId = Integer.parseInt(request.getParameter("registerId"));
		String value = request.getParameter("value");
		
		Register register = (Register)machine.getRegister(registerId);
		if(register == null)
			return;
		
		try {
			if(value != null)
				register.setValue(Integer.parseInt(value));
			out.println(register.getValue());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
