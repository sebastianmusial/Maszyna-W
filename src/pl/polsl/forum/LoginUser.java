package pl.polsl.forum;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.polsl.database.DatabaseConnection;
import pl.polsl.database.QueryData;

/**
 * Sign in user
 * @author Józef Flakus
 * @version 1.0
 */
@WebServlet("/LoginUser")
public class LoginUser extends HttpServlet {
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
    	
    	String userLogin = request.getParameter("user_name");
    	String userPass  = request.getParameter("user_pass");
    	
    	boolean error = false;
    	String result = null;
    	
    	Connection con = new DatabaseConnection(request).getInstance();
    	
    	try {
    		result = QueryData.findUser(con, userLogin, userPass);
    		
    		if (result.isEmpty()) {
    			error = true;
    		}
    		
		} catch (SQLException e) {
			error = true;
		} catch (Exception e) {
			error = true;
		}
    	   	
    	// set output attributes
        request.setAttribute("error", error); 
        RequestDispatcher rd = null;
        
        if (!error) {
        	
        	HttpSession session = request.getSession(true);
        	session.setAttribute("loggedUser", result);  
        	session.setAttribute("isAdmin", false); 
        	
        	response.sendRedirect("index.jsp");
        	
        } else {
        	
        	rd = request.getRequestDispatcher("login.jsp");
        	rd.forward(request, response);
        }   
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
