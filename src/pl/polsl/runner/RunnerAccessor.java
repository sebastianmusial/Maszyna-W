package pl.polsl.runner;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.polsl.servlet.WMachineServletBase;
import pl.polsl.settings.TrackLevel;

/**
 * Allow to remotely run tact, command or program.
 */
@WebServlet("/RunnerAccessor")
public class RunnerAccessor extends WMachineServletBase {
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @see WMachineServletBase#processRequest(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain;charset=UTF-8");
		
		Runner runner = getCurrentRunner(request.getSession());
		switch(request.getParameter("run").toUpperCase()) {
			case "PROGRAM": runner.run(TrackLevel.LOW); break;
			case "COMMAND": runner.run(TrackLevel.MEDIUM); break;
			case "TACT": runner.run(TrackLevel.HIGH); break;
			case "MANUALLY": runner.runManually(); break;
		}
	}
}
