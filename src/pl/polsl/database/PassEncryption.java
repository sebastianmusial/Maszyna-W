package pl.polsl.database;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Password encryption class.
 * @author Józef Flakus
 * @version 1.0
 */
public class PassEncryption {

	/**
	 * Hash password using SHA-256 encryption. 
	 * @param pass password for encryption
	 * @return hashed password
	 */
	public String hashS256(String pass) {
		MessageDigest md = null;
		byte[] digest = null;
		
		try {
			md = MessageDigest.getInstance("SHA-256");
	        md.update(pass.getBytes("UTF-8"));
	        digest = md.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.err.println(e.getMessage() + "\nSHA-256 encryption failed.");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.err.println(e.getMessage() + "\nSHA-256 encryption failed.");
		}
		
        StringBuffer hashedPass = new StringBuffer();
        for (int i = 0; i < digest.length; i++) {
        	hashedPass.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
        }	
        
        return hashedPass.toString();
	}
	
}
