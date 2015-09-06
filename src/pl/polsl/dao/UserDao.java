package pl.polsl.dao;

import java.util.List;

import pl.polsl.storage.UserStorage;

public class UserDao {

	private Dao<UserStorage> dao = new Dao<UserStorage>();
	
	public List<UserStorage> getAll() {
		return dao.getAll(UserStorage.class);
	}
	
	public void save(UserStorage u) {
		dao.save(u);
	}
	
}
