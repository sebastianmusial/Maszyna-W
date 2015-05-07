package pl.polsl.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Database data query class
 * @author Józef Flakus
 * @version 1.0
 */
public class DatabaseQuery {

	/**
	 * Find user in the database
	 * @param con database connection
	 * @param userName user name
	 * @param userPass user password
	 * @return list of strings (userID, login)
	 * @throws SQLException an exception that provides information on a database access error or other errors
	 * @throws Exception an exception if error occurs
	 */
	public static List<String> findUser(Connection con, String userName, String userPass) throws SQLException, Exception {		
		PreparedStatement pst = null;
		String loggedUserName = null;
		String loggedUserID   = null;
		String privileges     = null;
		List<String> result   = null;
		
		PassEncryption encryption = new PassEncryption();
		
		try {			
			pst = con.prepareStatement("SELECT login, userID, privilegesLevel FROM Users WHERE login = ? AND password = ?");
	        pst.setString(1, userName);
	        pst.setString(2, encryption.hashS256(userPass));
	        	        
	        ResultSet rs = pst.executeQuery();
	        
			while (rs.next()) {			 
				loggedUserID   = rs.getString("userID");
				loggedUserName = rs.getString("login");
				privileges     = Integer.toString(rs.getInt("privilegesLevel"));
			}
			
			result = new ArrayList<String>();
			result.add(loggedUserID);
			result.add(loggedUserName);
			result.add(privileges);
	        
		} finally {				
			if (pst != null) {
				pst.close();
			}			
		}
			
		return result;
	}
	
	/**
	 * Find login in the database
	 * @param con database connection
	 * @param userName user name
	 * @return true (if found), false (if not found) 
	 * @throws SQLException an exception that provides information on a database access error or other errors
	 * @throws Exception an exception if error occurs
	 */
	public static Boolean findLogin(Connection con, String userName) throws SQLException, Exception {		
		PreparedStatement pst = null;
		String resultString   = null;
		Boolean result		  = false;
				
		try {			
			pst = con.prepareStatement("SELECT userID FROM Users WHERE login = ?");
	        pst.setString(1, userName);
	        	        
	        ResultSet rs = pst.executeQuery();
	        
			while (rs.next()) {		 
				resultString   = rs.getString("userID");
			}
			
			if (resultString != null) {
				result = true;
			}
			
		} finally {				
			if (pst != null) {
				pst.close();
			}			
		}
			
		return result;
	}
}
