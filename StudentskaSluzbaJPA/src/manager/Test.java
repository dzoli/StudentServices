package manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Departman;

public class Test {

	public static void main(String[] args) {
		EntityManagerFactory emFactory =
				Persistence.createEntityManagerFactory("StudentskaSluzbaJPA");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		Departman d = new Departman();
		d.setNaziv("DMI");
		em.persist(d);
		
		em.getTransaction().commit();
		em.close();
		
	}

}
