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
import pl.polsl.runner.Runner;
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
			memory.replaceValue(0, 108);
			memory.replaceValue(1, 43);
			memory.replaceValue(2, 140);
			memory.replaceValue(3, 107);
			memory.replaceValue(4, 74);
			memory.replaceValue(5, 232);
			memory.replaceValue(6, 139);
			memory.replaceValue(7, 160);
			memory.replaceValue(8, 108);
			memory.replaceValue(9, 0);
			memory.replaceValue(10, 1);
			memory.replaceValue(11, 5);
			memory.replaceValue(12, 0);
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
	 * Return runner stored in HTTP session or creates
	 * a new one and save it in the session.
	 * @param session - current HTTP session.
	 * @return Runner instance.
	 */
	protected Runner getCurrentRunner(HttpSession session) {
		WMachine machine = getCurrentWMachine(session);
		Runner runner = (Runner)session.getAttribute("RunnerInstance");
		if(runner == null) {
			runner = new Runner(machine);
			session.setAttribute("RunnerInstance", runner);
		}
		return runner;
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
