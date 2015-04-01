package pl.polsl.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;

/**
 * Singleton of database connection
 * @author Józef Flakus
 * @version 1.0
 */
public class DatabaseConnection {
    
    private static boolean isCreated;
    private static Connection con;
    
    /**
     * Method used for returning singleton instance.
     * @return connection instance
     */
    public Connection getInstance() {
        return con;
    }
    
    /**
     * Constructor with parameter.
     * @param request HttpServletRequest object
     */
    public DatabaseConnection(HttpServletRequest request) {
        
        if (!isCreated) {
            
            String db_driver = request.getSession().getServletContext().getInitParameter("DB_DRIVER");
            String db_url    = request.getSession().getServletContext().getInitParameter("DB_URL");
            String db_user   = request.getSession().getServletContext().getInitParameter("DB_USER");
            String db_pass   = request.getSession().getServletContext().getInitParameter("DB_PASS");
              
            try {
                
                Class.forName(db_driver);
                con = DriverManager.getConnection(db_url, db_user, db_pass);
                
            } catch (ClassNotFoundException ex) {
                
                System.err.println("Class not found exception: " + ex.getMessage());
                
            } catch (SQLException ex) {
                
                System.err.println("SQL exception: " + ex.getMessage());
                
            }
                            
            isCreated = true;
            System.out.printf("First connection to database.");
        }
    }
}


