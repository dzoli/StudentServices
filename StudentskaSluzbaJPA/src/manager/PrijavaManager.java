package manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Predmet;
import model.Prijava;
import model.Profesor;
import model.Student;

public class PrijavaManager {

	
	public List<Prijava> prijaviPredmete(String brIndeksa, Date datumPrijave, String ispitniRok, Integer brojIzlazaka, List<Predmet> predmeti){
		EntityManager em = JPAUtils.getEntityManager();
		Student stud = new StudentManager().getStudentForBrIndeksa(brIndeksa);
		//profesorove aktivnosti...
		String status = "nepolozen";
		Integer ocena = 0;
		Integer bodovi = 0;
		//povratna vrednost
		List<Prijava> retValPrijave = new ArrayList<>();
		try {
			em.getTransaction().begin();
			for(Predmet pred: predmeti){
				Prijava prij = new Prijava();
				prij.setDatumprijave(datumPrijave);
				prij.setIspitnirok(ispitniRok);
				prij.setBrojizlazaka(brojIzlazaka);
				prij.setStatus(status);
				prij.setOcena(ocena);
				prij.setBodovi(bodovi);
				prij.setPredmet(pred);
				prij.setStudent(stud);
				em.persist(prij);
				retValPrijave.add(prij);
			}
				em.flush();
				em.getTransaction().commit();
		} catch (Exception e) {
			retValPrijave = null;
			e.printStackTrace();
		}finally{
			if(em.isOpen())
				em.close();
		}
		return retValPrijave;
	}

	public List<Prijava> getSvePrijave(){
		EntityManager em = JPAUtils.getEntityManager();
		TypedQuery<Prijava> tq = em.createNamedQuery("Prijava.findAll",Prijava.class);
		List<Prijava> svePrijave = tq.getResultList();
		em.close();
		return svePrijave;
	}
	
	public List<Prijava> getSvePrijaveForProfesor(Profesor p){
		EntityManager em = JPAUtils.getEntityManager();
		TypedQuery<Prijava> tq = em.createQuery("select p from Prijava p "
											  + "where p.predmet.profesor =:p",Prijava.class);
		tq.setParameter("p", p);
		List<Prijava> svePrijave = tq.getResultList();
		em.close();
		return svePrijave;
	}
	
	
	public Prijava mergePrijava(Integer prijavaId, Integer ocena, Integer bodovi, String status){
		EntityManager em = JPAUtils.getEntityManager();
		Prijava editPrj = em.find(Prijava.class, prijavaId);
		em.getTransaction().begin();
		editPrj.setOcena(ocena);
		editPrj.setBodovi(bodovi);
		editPrj.setStatus(status);
		em.merge(editPrj);
		em.flush();
		em.getTransaction().commit();
		em.close();
		return editPrj;
	}
	
	
	public static void main(String[] args) {
		
//		List<Predmet> idPred = new ArrayList<>();
//		idPred.add(new StudentManager().getPredmetForID(1));
//		idPred.add(new StudentManager().getPredmetForID(2));
//		List<Prijava> prijave = new PrijavaManager().prijaviPredmete("411/13", null, "Januar", 3, idPred);
//		for(Prijava p: prijave){
//			System.out.println(p.getPrijavaid());
//		}
		
		

//		List<Prijava> svePrijave = new PrijavaManager().getSvePrijave();
//		for(Prijava p: svePrijave)
//			System.out.println(p.getPrijavaid());
		
//		Prijava p = new PrijavaManager().mergePrijava(20, 10, 96, "polozen");
//		System.out.println(p.getPrijavaid());

		Profesor p = new StudentManager().getProfesorForLoginParameters("prof3", "1234");
		List<Prijava> prijaveProfesora = new PrijavaManager().getSvePrijaveForProfesor(p);
		for(Prijava p3: prijaveProfesora)
			System.out.println(p3.getPrijavaid());
		
		
		
		
		System.exit(0);
	}

}















