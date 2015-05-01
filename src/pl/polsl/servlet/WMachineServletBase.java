package pl.polsl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.polsl.architecture.WMachine;
import pl.polsl.architecture.WMachineFactory;
import pl.polsl.architecture.components.finalized.Memory;
import pl.polsl.settings.Settings;

/**
 * Base class for servlets manipulating W Machine state.
 * @author Tomasz Rzepka
 * @version 1.0
 */
@WebServlet("/WMachineServletBase")
public class WMachineServletBase extends HttpServlet {
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Return W Machine stored in HTTP session or creates
	 * a new one and save it in the session.
	 * @param session - current HTTP session.
	 * @return W Machine instance.
	 */
	protected WMachine getCurrentWMachine(HttpSession session) {
		WMachine machine = (WMachine)session.getAttribute("WMachineInstance");
		if(machine == null) {
			machine = new WMachineFactory().getInstance();
			session.setAttribute("WMachineInstance", machine);
			
			/**
			 *  Load simple program:
			    pętla:  pob wynik
				        dod n
				        ład wynik
				        pob n
				        ode jeden
				        soz koniec
				        ład n
				        sob pętla
				koniec: pob wynik
				        stp
				jeden:  rst 1
				n:      rst 5
				wynik:  rst 0
			 */
			Memory memory = machine.getMemory();
			memory.setValue(0, 108);
			memory.setValue(1, 43);
			memory.setValue(2, 140);
			memory.setValue(3, 107);
			memory.setValue(4, 74);
			memory.setValue(5, 232);
			memory.setValue(6, 139);
			memory.setValue(7, 160);
			memory.setValue(8, 108);
			memory.setValue(9, 0);
			memory.setValue(10, 1);
			memory.setValue(11, 5);
			memory.setValue(12, 0);
		}
		return machine;
	}
	
	/**
	 * Return settings stored in HTTP session or creates
	 * a new one and save it in the session.
	 * @param session - current HTTP session.
	 * @return Settings instance.
	 */
	protected Settings getCurrentSettings(HttpSession session) {
		Settings settings = (Settings)session.getAttribute("SettingsInstance");
		if(settings == null) {
			settings = new Settings();
			session.setAttribute("SettingsInstance", settings);
		}
		return settings;
	}
    
	/**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> method.
     * This function should be overriden in subclasses.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	
	/**
     * Processes requests for HTTP <code>GET</code> method.
     * By default calls processRequest method.
     * This method should not be overriden unless different
     * behaviour is needed for GET and POST methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
     * Processes requests for HTTP <code>POST</code> methods.
     * By default calls processRequest method.
     * This method should not be overriden unless different
     * behaviour is needed for GET and POST methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}
