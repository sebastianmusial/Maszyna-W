package pl.polsl.storage.dao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.Query;

import pl.polsl.hibernate.DatabaseConnector;
import pl.polsl.storage.ICRUD;
import pl.polsl.storage.UserStorage;

public class UsersDAO implements ICRUD{

	@Override
	public void create(Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void read() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Object t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object t) {
		// TODO Auto-generated method stub
		
	}
	
	private static boolean checkPassword(UserStorage user, String password) {
		password = hashS256(password);
		if (password.equals(user.getPassword())) {
			return true;
		} else {
			return false;
		}
	}
	
	private static String hashS256(String pass) {
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
