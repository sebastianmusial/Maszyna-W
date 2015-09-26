package pl.polsl.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import pl.polsl.dao.Dao;
import pl.polsl.database.DatabaseConnection;
import pl.polsl.database.HibernateUtil;
import pl.polsl.hibernate.DatabaseConnector;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

/**
 * Application Lifecycle Listener implementation class WMachineServletContextListener
 *
 */
@WebListener
public final class WMachineServletContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public WMachineServletContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent event)  { 
    	HibernateUtil.getSessionFactory().close();
    	DatabaseConnector.getInstance().disconnect();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  {
    	ServletContext context = event.getServletContext();
    	InputStream stream = context.getResourceAsStream("/WEB-INF/database.properties");
    	Properties properties = new Properties();
        try {
			properties.load(stream);
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        new DatabaseConnection(properties);
    }
	
}
