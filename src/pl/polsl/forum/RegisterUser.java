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
    	
    	String user_login = request.getParameter("user_name");
    	String user_pass  = request.getParameter("user_pass");
    	String user_email = request.getParameter("user_email");
    	
    	int error = 0;
    	
    	Connection con = new DatabaseConnection(request).getInstance();
    	
    	try {
    		DatabaseInsertion.insertUser(con, user_login, user_pass, user_email);
		} catch (SQLException e) {
			error = 1;
		} catch (Exception e) {
			error = 1;
		}
    	
    	response.sendRedirect("status.jsp?type=register&error=" + error);
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
