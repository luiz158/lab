package app.poll;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Util {
	
	private static EntityManager entityManager;
	
	public static EntityManager getEntityManager() {
		if (entityManager == null) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("polldb");
			entityManager = emf.createEntityManager();
		}
		
		return entityManager;
	}
}
