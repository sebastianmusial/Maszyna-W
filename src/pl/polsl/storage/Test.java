package pl.polsl.storage;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import pl.polsl.hibernate.DatabaseConnector;
import pl.polsl.storage.dao.CommandsListDAO;
import pl.polsl.storage.dao.UsersDAO;

public class Test {
	public static void main(String[] args) {
		UserStorage user = UsersDAO.getByLoginAndPassword("raku", "haslo");
		if (user != null) {
			List<CommandsListStorage> list = CommandsListDAO.getByUser(user);

			for (CommandsListStorage r : list) {
				System.out.println(r.getName());
			}
		} else {
			System.out.println("Zle haslo/login!");
		}
	}

}
