package pl.polsl.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryData {

	public static String findUser(Connection con, String userName, String userPass) throws SQLException, Exception {
		
		PreparedStatement pst = null;
		String loggedUser = null;
		
		try {
			
			pst = con.prepareStatement("SELECT login FROM User WHERE login = ? AND password = ?");
	        pst.setString(1, userName);
	        pst.setString(2, userPass);	
	        
	        ResultSet rs = pst.executeQuery();
	        
			while (rs.next()) {			 
				loggedUser = rs.getString("login");
				
				// debugging purpose
				// System.out.printf("logged user: " + loggedUser + "\n");
			}
	        
		} finally {	
			
			if (pst != null) {
				pst.close();
			}			
		}
			
		return loggedUser;
	}
}
