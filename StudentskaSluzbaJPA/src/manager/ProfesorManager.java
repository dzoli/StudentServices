package manager;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Departman;
import model.Predmet;
import model.Profesor;
import model.Student;

public class ProfesorManager {

	public Departman getDepartmandForID(Integer id){
		EntityManager em = JPAUtils.getEntityManager();
		Departman d = em.find(Departman.class, id);
		em.close();
		return d;
	}
	
	public Profesor getProfesorForID(Integer id){
		EntityManager em = JPAUtils.getEntityManager();
		Profesor p = em.find(Profesor.class, id);
		em.close();
		return p;
	}
	
	public List<Departman> getAllDepartmans(){
		EntityManager em = JPAUtils.getEntityManager();
		TypedQuery<Departman> tq = em.createNamedQuery("Departman.findAll",Departman.class);		
		List<Departman> allDeps = tq.getResultList();
		em.close();
		return allDeps;
	}
	
	public Profesor sacuvajProfesora(String ime,     String prezime,     Departman dep, 
								    String adresa,  Date datumRodjenja, String zvanje, 
			                        String lozinka, String korisnickoIme){
		Profesor retp = null;
		EntityManager em = JPAUtils.getEntityManager();
		try {
			em.getTransaction().begin();
			retp = new Profesor();
			retp.setIme(ime);
			retp.setPrezime(prezime);
			retp.setDepartman(dep);
			retp.setAdresa(adresa);
			retp.setDatumrodjenja(datumRodjenja);
			retp.setZvanje(zvanje);
			retp.setLozinka(lozinka);
			retp.setKorisnickoime(korisnickoIme);
			em.persist(retp);
			em.flush();
			em.getTransaction().commit();
			System.out.println(retp.getProfesorid()); //kada ga perzistujemo ID se sam setuje!
		} catch (Exception e) {
			retp = null;
			e.printStackTrace();
		}finally{
			if(em.isOpen() || em != null){
				em.close();
			}
		}
		return retp;
	}
	
	public Predmet sacuvajPredmet(String naziv, Integer semestar, Integer godinaSlusanja,
								  String tip,   Integer espb,      Profesor prof){
		EntityManager em = JPAUtils.getEntityManager();
		Predmet pred;
		try {
			em.getTransaction().begin();
			pred = new Predmet();
			pred.setNaziv(naziv);
			pred.setSemestar(semestar);
			pred.setGodinaslusanja(godinaSlusanja);
			pred.setEspb(espb);
			pred.setTip(tip);
			pred.setProfesor(prof);
			em.persist(pred);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			pred = null;
			e.printStackTrace();
		}finally{
			if(em.isOpen())
				em.close();
		}
		return pred;
	}
	
	//strategija indentity
	public Student sacuvajStudenta(String ime, String prezime, String adresa,
								   Date datumRodj, String lozinka, String brojIndeksa,
								   Departman departman, Integer godinaStudija){
		EntityManager em = JPAUtils.getEntityManager();
		Student stud;
		try {
			em.getTransaction().begin();
			stud = new Student();
			stud.setBrindeksa(brojIndeksa);
			stud.setDepartman(departman);
			stud.setIme(ime);
			stud.setPrezime(prezime);
			stud.setDatumrodjenja(datumRodj);
			stud.setAdresa(adresa);
			stud.setLozinka(lozinka);
			stud.setGodinastudija(godinaStudija);
			em.persist(stud);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			stud = null;
			e.printStackTrace();
		}finally{
			if(em.isOpen())
				em.close();
		}
		return stud;
	}
	
	
	public List<Profesor> sviProfesoriSaDepartmana(String departman){
		EntityManager em = JPAUtils.getEntityManager();
		TypedQuery<Profesor> tq = 
				em.createQuery(
						  "select p from Profesor p "
						+ "where p.departman.naziv =:departman"
						,Profesor.class
				);
		tq.setParameter("departman", departman);
		List<Profesor> profesoriSaDep = tq.getResultList();
		em.close();
		return profesoriSaDep;
	}
	
	
	public List<Profesor> sviProfesori(){
		EntityManager em = JPAUtils.getEntityManager();
		TypedQuery<Profesor> tq = em.createNamedQuery("Profesor.findAll",Profesor.class);		
		List<Profesor> profesoriSaDep = tq.getResultList();
		em.close();
		return profesoriSaDep;
	}

	public List<String> getKorisnickaImena(){
		EntityManager em = JPAUtils.getEntityManager();
		TypedQuery<String> tq = 
				em.createQuery("select p.korisnickoime "
							 + "from Profesor p ",String.class);
		List<String> rez = tq.getResultList();
		em.close();
		return rez;
	}
	
	public Profesor getProfesorForKorisnicko(String korisnicko){
		EntityManager em = JPAUtils.getEntityManager();
		TypedQuery<Profesor> tq = 
				em.createQuery("select p from Profesor p "
							 + "where p.korisnickoime =:korisnicko "
		        ,Profesor.class);
		tq.setParameter("korisnicko", korisnicko);
		Profesor p = tq.getSingleResult();
		em.close();
		return p;
	}
	
	
	//test...
	public static void main(String[] args) {
		
//		Departman d = new ProfesorManager().getDepartmandForID(1);
//		System.out.println(d.getNaziv());

		
//		List<Departman> all = new ProfesorManager().getAllDepartmans();
//		for(Departman d: all){
//			System.out.println(d.getNaziv());
//		}

		
//		Departman d = new ProfesorManager().getDepartmandForID(1);
//		if(new ProfesorManager().sacuvajProfesora("Milos", "Rackovic", d, 
//												  "Rnd 1",  null,     "dr", 
//												  "prof1", "prof1") != null){
//			System.out.println("uspesno sacuvan");
//		}else{
//			System.out.println("nije sacuvan");
//		}

		
//		List<Profesor> sviProfSaDep = new ProfesorManager().sviProfesoriSaDepartmana("DMI");
//		for(Profesor p: sviProfSaDep){
//			System.out.println(p.getIme());
//		}
		
		
//		List<Profesor> profesori = new ProfesorManager().sviProfesori();
//		for(Profesor p: profesori)
//			System.out.println(p.getIme());
		
		
//		Profesor p = new ProfesorManager().getProfesorForID(1);
//		System.out.println(p.getIme());
		
		
//		Student s = new ProfesorManager().sacuvajStudenta("Novica", "Bjelica", "z", null, "a", "411/13");
//		System.out.println(s.getIme());
		
//		List<String> korisnickaImena = new ProfesorManager().getKorisnickaImena();
//		for(String ki: korisnickaImena)
//			System.out.println(ki);

		Profesor p = new ProfesorManager().getProfesorForKorisnicko("prof1");
		System.out.println(p.getIme());
		
		System.exit(0);
	}
}


