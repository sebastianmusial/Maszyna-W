package pl.polsl.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertData {

	public static void insertUser(Connection con, String user_login, String user_pass, String user_email) throws SQLException, Exception {	
			
            PreparedStatement pst = con.prepareStatement("INSERT INTO user VALUES (?, ?, ?, ?)");

            pst.setNull(1, 0);
            pst.setString(2, user_login);
            pst.setString(3, user_pass);
            pst.setString(4, user_email);

            pst.executeUpdate();			
	}
	
}