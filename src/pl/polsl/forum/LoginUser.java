package pl.polsl.forum;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.polsl.database.DatabaseConnection;
import pl.polsl.database.DatabaseQuery;

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
    	
    	request.setCharacterEncoding("UTF-8");
    	
    	String userLogin = request.getParameter("user_name");
    	String userPass  = request.getParameter("user_pass");
    	
    	int error = 0;
    	List<String> result = null;
    	
    	Connection con = new DatabaseConnection(request).getInstance();
    	
    	try {
    		result = DatabaseQuery.findUser(con, userLogin, userPass);  		
    		if (result.get(0) == null || result.get(1) == null) {
    			error = 1;
    		}		
		} catch (SQLException e) {
			error = 1;
		} catch (Exception e) {
			error = 1;
		}
        
        if (error == 0) {       	
        	HttpSession session = request.getSession(true);
        	session.setAttribute("loggedID", result.get(0));  
        	session.setAttribute("loggedUser", result.get(1));  
        	session.setAttribute("isAdmin", false); 
        	
        	response.sendRedirect("index.jsp");
        } else {     
        	response.sendRedirect("login.jsp?error=" + error);
        }   
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
