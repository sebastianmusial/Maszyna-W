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
import pl.polsl.database.InsertData;

/**
 * Servlet implementation class CreateTopic
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
    	    	
    	boolean error = false;
    	
    	Connection con = new DatabaseConnection(request).getInstance();
    	
    	try {
    		InsertData.insertTopic(con, topicName, topicBody, userID);   		
		} catch (SQLException e) {
			e.printStackTrace();
			error = true;
		} catch (Exception e) {
			e.printStackTrace();
			error = true;
		}
    	   	
    	// output  	
        request.setAttribute("error", error); 

        RequestDispatcher rd = request.getRequestDispatcher("createTopic_status.jsp");
        rd.forward(request, response); 
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
