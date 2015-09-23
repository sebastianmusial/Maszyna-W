package pl.polsl.forum;

import java.util.List;

import pl.polsl.dao.UserDao;
import pl.polsl.database.PassEncryption;
import pl.polsl.storage.UserStorage;

/**
 * Service is used in {@link pl.polsl.forum.LoginServlet} to manage process of logging in.
 * @author Michal Rakoczy
 *
 */
public class LoginService {

	/**
	 * User name.
	 */
	String login;
	/**
	 * User password hash.
	 */
	String password;
	/**
	 * Class is needed to hash password in constructor.
	 */
	PassEncryption passEncryption;
	
	/**
	 * Constructor.
	 * @param login User name.
	 * @param password User password (no hash).
	 */
	public LoginService(String login, String password) {
		super();
		passEncryption = new PassEncryption();
		this.login = login;		
		this.password = passEncryption.hashS256(password);
	}
	
	/**
	 * Checks if user with given login and password exists in database.
	 * @return user if was found
	 * @throws LoginException if user is not found or if is problem with database.
	 */
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
			throw new LoginException("Database error");
		}
	}
		
}
