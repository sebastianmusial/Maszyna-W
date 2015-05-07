package pl.polsl.forum;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.polsl.database.DatabaseConnection;
import pl.polsl.database.DatabaseQuery;

/**
 * Servlet implementation class FindLogin
 */
@WebServlet("/FindLogin")
public class FindLogin extends HttpServlet {
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
    	response.setContentType("text/plain");
    	response.setCharacterEncoding("UTF-8");
    	
    	String userLogin = request.getParameter("login");	
    	Boolean result   = false;
    	int error        = 0;
    	
    	Connection con = new DatabaseConnection(request).getInstance();
    	
    	try {
    		result = DatabaseQuery.findLogin(con, userLogin);	
		} catch (SQLException e) {
			error = 1;
		} catch (Exception e) {
			error = 1;
		}
        
        if (error == 0) {       	
        	if (result == true) {
        		response.getWriter().write("found"); 
        	}
        }  
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
