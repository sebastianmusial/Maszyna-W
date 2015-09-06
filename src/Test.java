import pl.polsl.dao.UserDao;
import pl.polsl.storage.UserStorage;


public class Test {

	public static void main(String[] args) {
		UserStorage u = new UserStorage();
		u.setLogin("tedst");
		u.setPassword("ss");
		u.setEmailAddress("a@a.pl");
		u.setPrivilegesLevel((short) 10);
		UserDao dao = new UserDao();
		dao.save(u);
	}
	
}
