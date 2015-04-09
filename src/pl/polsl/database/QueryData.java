package pl.polsl.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryData {

	public static List<String> findUser(Connection con, String userName, String userPass) throws SQLException, Exception {
		
		PreparedStatement pst = null;
		String loggedUserName = null;
		String loggedUserID = null;
		List<String> result = null;
		
		try {
			
			pst = con.prepareStatement("SELECT login, userID FROM User WHERE login = ? AND password = ?");
	        pst.setString(1, userName);
	        pst.setString(2, userPass);	
	        
	        ResultSet rs = pst.executeQuery();
	        
			while (rs.next()) {			 
				loggedUserID = rs.getString("userID");
				loggedUserName = rs.getString("login");
				
				// debugging purpose
				System.out.printf("logged user: " + loggedUserID + " " + loggedUserName + "\n");
			}
			
			result = new ArrayList<String>();
			result.add(loggedUserID);
			result.add(loggedUserName);
	        
		} finally {	
			
			if (pst != null) {
				pst.close();
			}			
		}
			
		return result;
	}
}
