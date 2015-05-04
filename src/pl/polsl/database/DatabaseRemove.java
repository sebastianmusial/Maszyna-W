package pl.polsl.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseRemove {

	/**
	 * Remove given post from the database.
	 * @param con database connection
	 * @param postID identification number of post
	 */
	public static void removePost(Connection con, int postID) throws SQLException, Exception {
		PreparedStatement pst = null;		
		try {		
            pst = con.prepareStatement("DELETE FROM Reply WHERE replyID = ?");
            pst.setInt(1, postID);
            pst.executeUpdate();
		} finally {			
			if (pst != null) {
				pst.close();
			}			
		}
	}
}
