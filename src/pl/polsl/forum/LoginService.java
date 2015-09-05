package pl.polsl.forum;

import java.util.List;

import pl.polsl.dao.UserDao;
import pl.polsl.database.PassEncryption;
import pl.polsl.storage.UserStorage;

public class LoginService {

	String login;
	String password;
	PassEncryption passEncryption;
	
	public LoginService(String login, String password) {
		super();
		passEncryption = new PassEncryption();
		this.login = login;
		
		this.password = passEncryption.hashS256(password);
	}
	
	public UserStorage isValid() throws LoginException {
		List<UserStorage> list = null;
		UserDao dao = new UserDao();
		try {
			list = dao.getAll();
			for (UserStorage u : list) {
				if (u.getLogin().equals(login)
						&& u.getPassword().equals(this.password)) {
					return u;
				}
			}
			throw new LoginException("Login or password not match!");
		} catch (Exception e) {
			throw new LoginException("Database");
		}
	}
		
}
