package pl.polsl.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import pl.polsl.architecture.Register;
import pl.polsl.architecture.Signal;
import pl.polsl.architecture.Bus;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.architecture.ScriptSignal;

/**
 *
 * @author Tomasz Rzepka
 */
@WebServlet(urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TestServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TestServlet at " + request.getContextPath() + "</h1>");
            
            Register S = new Register(5);
            Register I = new Register(5);
            Register AK = new Register(5);
            Bus magS = new Bus(5);
            Signal wys = new Signal(S, magS);
            Signal wei = new Signal(magS, I);
            Signal wyak = new Signal(AK, magS);
            Signal dod = new ScriptSignal(magS, AK, "x+AK");
            try {
                magS.nextTact();
                S.setValue(2);
                wys.activate();
                out.println("Na magistrali: " + magS.getValue() + "<br />");
                wei.activate();
                out.println("W rej I: " + I.getValue() + "<br />");
                dod.activate();
                out.println("W rej AK: " + AK.getValue() + "<br />");
            }
            catch(Exception ex)
            {
                out.println(ex.getMessage() + "<br />");
            }
            
            try {
                magS.nextTact();
                S.setValue(2);
                AK.setValue(2);
                wys.activate();
                wyak.activate();
            }
            catch(Exception ex)
            {
                out.println(ex.getMessage() + "<br />");
            }
            
            try {
                magS.nextTact();
                wei.activate();
            }
            catch(Exception ex)
            {
                out.println(ex.getMessage() + "<br />");
            }
            out.println("<button id=\"somebutton\">press here</button>\n" +
"        <div id=\"somediv\">?</div>");
            
            out.println("</body>");
            out.print("<script src=\"http://code.jquery.com/jquery-latest.min.js\"></script>");
            out.print("<script>\n" +
"            $(document).ready(function() {                        // When the HTML DOM is ready loading, then execute the following function...\n" +
"                $('#somebutton').click(function() {               // Locate HTML DOM element with ID \"somebutton\" and assign the following function to its \"click\" event...\n" +
"                    $.get('http://localhost:8084/MachineW/TestServlet2', function(responseText) { // Execute Ajax GET request on URL of \"someservlet\" and execute the following function with Ajax response text...\n" +
"                        $('#somediv').text(responseText);         // Locate HTML DOM element with ID \"somediv\" and set its text content with the response text.\n" +
"                    });\n" +
"                });\n" +
"            });\n" +
"        </script>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
