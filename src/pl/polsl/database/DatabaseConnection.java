package pl.polsl.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

/**
 * Singleton of database connection
 * @author Józef Flakus
 * @version 1.0
 */
public class DatabaseConnection {
    
    private static boolean isCreated;
    private static Connection connection;
    
    private static Properties connectionProperties;
    
    /**
     * Method used for returning singleton instance.
     * @return connection instance
     */
    public static Connection getInstance() {
    	try {
			if(!connection.isValid(10))
				reconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return connection;
    }
    
    /**
     * Constructor with parameter.
     * @param request HttpServletRequest object
     * @deprecated Do not use constructor, instead use
     * DatabaseConnection.getInstance() static method directly.
     */
    @Deprecated
    public DatabaseConnection(HttpServletRequest request) {
        
        if (!isCreated) {
            
            String db_driver = request.getSession().getServletContext().getInitParameter("DB_DRIVER");
            String db_url    = request.getSession().getServletContext().getInitParameter("DB_URL");
            String db_user   = request.getSession().getServletContext().getInitParameter("DB_USER");
            String db_pass   = request.getSession().getServletContext().getInitParameter("DB_PASS");
              
            try {
                
                Class.forName(db_driver);
                connection = DriverManager.getConnection(db_url, db_user, db_pass);
                
            } catch (ClassNotFoundException ex) {
                
                System.err.println("Class not found exception: " + ex.getMessage());
                
            } catch (SQLException ex) {
                
                System.err.println("SQL exception: " + ex.getMessage());
                
            }
                            
            isCreated = true;
            System.out.printf("First connection to database.\n");
        }
    }
    
    /**
     * Constructs database connection using properties.
     * Should be used only once in servlet context listener
     * at context initialization.
     * @param properties properties with database connection data
     */
    public DatabaseConnection(Properties properties) {
    	if(!isCreated) {
    		isCreated = true;
    		connectionProperties = properties;
    		reconnect();
    		System.out.printf("First connection to database.\n");
    	}
    }
    
    private static void reconnect() {
    	try {
            String db_driver = connectionProperties.getProperty("DB_DRIVER");
            String db_url    = connectionProperties.getProperty("DB_URL");
            String db_user   = connectionProperties.getProperty("DB_USER");
            String db_pass   = connectionProperties.getProperty("DB_PASS");
            Class.forName(db_driver);
            connection = DriverManager.getConnection(db_url, db_user, db_pass);
        }
        catch (ClassNotFoundException ex) {
            System.err.println("Class not found exception: " + ex.getMessage());
        }
        catch (SQLException ex) {
            System.err.println("SQL exception: " + ex.getMessage());
        }
    }
}


