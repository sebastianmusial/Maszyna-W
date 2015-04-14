package pl.polsl.forum;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.polsl.database.DatabaseConnection;
import pl.polsl.database.DatabaseInsertion;;

/**
 * Servlet implementation class CreateTopic
 * @author Józef Flakus
 */
@WebServlet("/CreateTopic")
public class CreateTopic extends HttpServlet {
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
    	
    	HttpSession session = request.getSession(true);
    	
    	String topicName = request.getParameter("topic_name");
    	String topicBody  = request.getParameter("topic_body");
    	int userID = Integer.parseInt((String)session.getAttribute("loggedID"));
    	    	
    	int error = 0;
    	
    	Connection con = new DatabaseConnection(request).getInstance();
    	
    	try {
    		con.setAutoCommit(false);
    		DatabaseInsertion.insertTopic(con, topicName, topicBody, userID); 
    		con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			error = 1;
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			error = 1;
		} finally {
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    	   	
    	response.sendRedirect("status.jsp?type=createTopic&error=" + error);
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
