package pl.polsl.forum;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.polsl.database.*;

/**
 * Register user in the database
 * @author Józef Flakus
 * @version 1.0
 */

@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/** Privileges level for newly created user */
	final int privilegesLevel = 10;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
    	
    	String userLogin = request.getParameter("user_name");
    	String userPass  = request.getParameter("user_pass");
    	String userEmail = request.getParameter("user_email");
    	
    	RegisterService service = new RegisterService(userLogin, userPass, userEmail);
    	try {
			service.registerUser();
			response.sendRedirect("status.jsp?type=register&error=" + 0);
		} catch (RegisterException e1) {
			response.sendRedirect("status.jsp?type=register&error=" + 1);
		}
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}
