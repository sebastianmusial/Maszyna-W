package pl.polsl.servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.polsl.architecture.WMachine;
import pl.polsl.utils.WMachineSerializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet for restoring and saving W Machine state, i.e.
 * registers' values, memory, signals' states,
 * bit counts, track level, hand control check box state,
 * enabled extensions, language. 
 */
@WebServlet("/WMachineState")
public class WMachineState extends WMachineServletBase {
	private static final long serialVersionUID = 1L;
	
	private final Gson GSON;

    /**
     * Default constructor. 
     */
    public WMachineState() {
    	final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(WMachine.class, new WMachineSerializer());
        GSON = gsonBuilder.create();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Writer writer = response.getWriter();
		WMachine machine = getCurrentWMachine(request.getSession());
		String action = request.getParameter("action");
		
		if("restore".equalsIgnoreCase(action)) {
			GSON.toJson(machine, writer);
		}
	}
}
