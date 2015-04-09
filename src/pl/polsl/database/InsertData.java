package pl.polsl.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertData {

	public static void insertUser(Connection con, String userLogin, String userPass, String userEmail) throws SQLException, Exception {				
		PreparedStatement pst = null;
		
		try {		
            pst = con.prepareStatement("INSERT INTO User VALUES (?, ?, ?, ?)");

            pst.setNull(1, 0);
            pst.setString(2, userLogin);
            pst.setString(3, userPass);
            pst.setString(4, userEmail);

            pst.executeUpdate();
            
		} finally {			
			if (pst != null) {
				pst.close();
			}			
		}
	}
	
	public static void insertTopic(Connection con, String topicName, String topicBody, int userID) throws SQLException, Exception {
		PreparedStatement pst = null;		
		int topicID = 0;
		
		try {			
            pst = con.prepareStatement("INSERT INTO Topics VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pst.setNull(1, 0);
            pst.setInt(2, userID);
            pst.setInt(3, 1);
            pst.setString(4, topicName);
            pst.executeUpdate();
            
            ResultSet rs = pst.getGeneratedKeys();
            if(rs.next())
                topicID = rs.getInt(1);
            
            pst.close();
            pst = con.prepareStatement("INSERT INTO Reply VALUES (?, ?, ?, ?)");
            pst.setNull(1, 0);
            pst.setInt(2, userID);
            pst.setInt(3, topicID);
            pst.setString(4, topicBody);
            pst.executeUpdate();          
		} finally {			
			if (pst != null) {
				pst.close();
			}			
		}		
	}	
}