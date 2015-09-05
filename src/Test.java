import java.util.List;

import pl.polsl.dao.Dao;
import pl.polsl.dao.UserDao;
import pl.polsl.database.PassEncryption;
import pl.polsl.storage.UserStorage;


public class Test {

	public static void main(String[] args) {
		PassEncryption p = new PassEncryption();
		String h = "haslo";
		String pass = p.hashS256(h);
		System.out.println(pass);
	}
	
}
