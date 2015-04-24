package pl.polsl.forum;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.polsl.database.DatabaseConnection;
import pl.polsl.database.DatabaseInsertion;

/**
 * Servlet implementation class CreatePost
 * @author Józef Flakus
 */
@WebServlet("/CreatePost")
public class CreatePost extends HttpServlet {
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
    	
    	String postBody  = request.getParameter("post_body");
    	int topicID = Integer.parseInt(request.getParameter("topic_id"));
    	int userID = Integer.parseInt((String)session.getAttribute("loggedID"));
    	
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		
		int error = 0;
		
		try {
			DatabaseInsertion.insertPost(con, postBody, currentTime, topicID, userID);
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