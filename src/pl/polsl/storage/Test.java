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
		List<CommandStorage> co = CommandsListDAO.getStandardList();
		System.out.println(co);
	}

}
