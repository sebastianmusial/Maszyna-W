package pl.polsl.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Database data insertion class
 * @author Józef Flakus
 * @version 1.0
 */
public class DatabaseInsertion {

	/**
	 * Insert created user in the database.
	 * @param con database connection
	 * @param userLogin user login name
	 * @param userPass user password
	 * @param userEmail user email address
	 * @param privilegesLevel privileges for newly created user
	 * @throws SQLException an exception that provides information on a database access error or other errors
	 * @throws Exception an exception if error occurs
	 */
	public static void insertUser(Connection con, 
									  String userLogin, 
									  String userPass, 
									  String userEmail,
									  int privilegesLevel) throws SQLException, Exception {				
		PreparedStatement pst = null;
		PassEncryption encryption = new PassEncryption();
		
		try {		
            pst = con.prepareStatement("INSERT INTO Users VALUES (?, ?, ?, ?, ?)");

            pst.setNull(1, 0);
            pst.setString(2, userLogin);
            pst.setString(3, encryption.hashS256(userPass));
            pst.setString(4, userEmail);
            pst.setInt(5, privilegesLevel);

            pst.executeUpdate();
            
		} finally {			
			if (pst != null) {
				pst.close();
			}			
		}
	}
	
	
	/**
	 * Insert created topic in the database
	 * @param con database connection
	 * @param topicName name of the created topic
	 * @param topicBody body (first post) of the created topic
	 * @param userID ID number of topic author
	 * @throws SQLException an exception that provides information on a database access error or other errors
	 * @throws Exception an exception if error occurs
	 */
	public static void insertTopic(Connection con, 
									   String topicName, 
									   String topicBody, 
									   int userID) throws SQLException, Exception {
		PreparedStatement pst = null;		
		int topicID = 0;
		
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		
		try {			
            pst = con.prepareStatement("INSERT INTO Topics VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pst.setNull(1, 0);
            pst.setInt(2, userID);
            pst.setInt(3, 1);
            pst.setString(4, topicName);
            pst.setString(5, currentTime);
            pst.executeUpdate();
            
            ResultSet rs = pst.getGeneratedKeys();
            if(rs.next())
                topicID = rs.getInt(1);
            
            pst.close();
            pst = con.prepareStatement("INSERT INTO Reply VALUES (?, ?, ?, ?, ?)");
            pst.setNull(1, 0);
            pst.setInt(2, userID);
            pst.setInt(3, topicID);
            pst.setString(4, topicBody);
            pst.setString(5, currentTime);
            pst.executeUpdate();          
		} finally {			
			if (pst != null) {
				pst.close();
			}			
		}		
	}
	
	public static void insertPost(Connection con, 
									  String postBody, 
									  String currentTime, 
									     int topicID, 
									     int userID) throws SQLException, Exception {
		PreparedStatement pst = null;
		
		try {			
            pst = con.prepareStatement("INSERT INTO Reply VALUES (?, ?, ?, ?, ?)");
            pst.setNull(1, 0);
            pst.setInt(2, userID);
            pst.setInt(3, topicID);
            pst.setString(4, postBody);
            pst.setString(5, currentTime);
            pst.executeUpdate();         
		} finally {			
			if (pst != null) {
				pst.close();
			}			
		}			
	}
}