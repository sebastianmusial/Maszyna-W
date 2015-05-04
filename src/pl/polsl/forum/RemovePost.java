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
import pl.polsl.database.DatabaseRemove;

/**
 * Remove post
 * @author Józef Flakus
 * @version 1.0
 */
@WebServlet("/RemovePost")
public class RemovePost extends HttpServlet {
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
    	
    	Connection con = new DatabaseConnection(request).getInstance();    	
    	HttpSession session = request.getSession(true);
    	
    	int postID  = Integer.parseInt(request.getParameter("post_id"));
    	int topicID = Integer.parseInt(request.getParameter("topic_id"));
    			
		int error = 0;
		
		try {
			DatabaseRemove.removePost(con, postID);
		} catch (SQLException e) {
			e.printStackTrace();
			error = 1;
		} catch (Exception e) {
			e.printStackTrace();
			error = 1;
		}

    	response.sendRedirect("topic.jsp?id=" + topicID + "&error=" + error);	  
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
