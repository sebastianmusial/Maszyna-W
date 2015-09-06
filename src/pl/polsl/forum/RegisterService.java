package pl.polsl.forum;

import pl.polsl.dao.UserDao;
import pl.polsl.database.PassEncryption;
import pl.polsl.storage.UserStorage;

public class RegisterService {

	String login;
	String password;
	String email;
	short privileges;
		
	public RegisterService(String login, String password, String email) {
		super();
		this.login = login;
		this.password = password;
		this.email = email;
		this.privileges = 10;
	}
	
	public void registerUser() throws RegisterException {
		try {
		UserStorage u = new UserStorage();
		u.setLogin(login);
		PassEncryption encryption = new PassEncryption();
		String hash = encryption.hashS256(password);
		u.setPassword(hash);
		u.setEmailAddress(email);
		u.setPrivilegesLevel(privileges);
		UserDao dao = new UserDao();
		dao.save(u);
		} catch (Exception e) {
			throw new RegisterException();
		}
	}
}
