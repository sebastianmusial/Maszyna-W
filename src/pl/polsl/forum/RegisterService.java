package pl.polsl.forum;

import pl.polsl.dao.UserDao;
import pl.polsl.database.PassEncryption;
import pl.polsl.storage.UserStorage;

/**
 * Service is used in {@link pl.polsl.forum.RegisterServlet} to manage process of registering new user.
 * @author Michal Rakoczy
 *
 */
public class RegisterService {

	/**
	 * User name.
	 */
	String login;
	/**
	 * User password.
	 */
	String password;
	/**
	 * User email address.
	 */
	String email;
	/**
	 * User level of pribileges.
	 */
	short privileges;
		
	/**
	 * Constructor.
	 * @param login
	 * @param password
	 * @param email
	 */
	public RegisterService(String login, String password, String email) {
		super();
		this.login = login;
		this.password = password;
		this.email = email;
		this.privileges = 10;
	}
	
	/**
	 * Regitser user.
	 * @throws RegisterException If user name exists in database or database error.
	 */
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
