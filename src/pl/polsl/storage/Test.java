package pl.polsl.storage;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Test {
	public static void main(String[] args) {
//		Configuration configuration = new Configuration();
//		configuration.configure("hibernate.cfg.xml");
//		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
//	            configuration.getProperties()).build();
		EntityManagerFactory emFactory = Persistence
				.createEntityManagerFactory("Maszyna-W");
		EntityManager em = emFactory.createEntityManager();
		Query q = em.createNamedQuery("UserStorage.findAll");
		List<UserStorage> a = q.getResultList();
		for (UserStorage u : a) {
			System.out.println(u.getLogin());
		}
	}

}
