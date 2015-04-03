package pl.polsl.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	
}