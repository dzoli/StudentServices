package manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtils {
	
	private static EntityManagerFactory entityManagerFactory;
	
	static{
		
		entityManagerFactory = Persistence.createEntityManagerFactory("StudentskaSluzbaJPA");
	}
	
	static public EntityManager getEntityManager(){
		 return entityManagerFactory.createEntityManager();
		
	}

}
