package pl.polsl.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.polsl.architecture.DataSource;
import pl.polsl.architecture.DataTarget;
import pl.polsl.architecture.Register;
import pl.polsl.architecture.WMachine;
import pl.polsl.architecture.WMachineComponent;

/**
 * Servlet implementation class RegisterAccessor
 */
@WebServlet("/RegisterAccessor")
public class RegisterAccessor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterAccessor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		WMachine machine = (WMachine)session.getAttribute("WMachineInstance");
		if(machine == null) {
			machine = new WMachine();
			machine.readArchitecture("");
			session.setAttribute("WMachineInstance", machine);
		}
		
		PrintWriter out = response.getWriter();
		String componentName = request.getParameter("componentName");
		String value = request.getParameter("value");
		
		Register register = (Register)machine.getComponent(componentName);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
