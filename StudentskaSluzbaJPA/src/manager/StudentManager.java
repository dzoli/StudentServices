package manager;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import customBean.ProfesorBrPredmeta;
import customBean.StudentBrojPolozenih;
import model.Departman;
import model.Predmet;
import model.Profesor;
import model.Student;

public class StudentManager {

	// public Student
	public Student getStudentForLoginParameters(String brIndeksa, String lozinka) {
		EntityManager em = JPAUtils.getEntityManager();
		Student s;
		try {
			TypedQuery<Student> tq = em.createQuery(
							"select s from Student s " + 
							"where s.brindeksa =:brIndeksa " + 
							"and s.lozinka =:lozinka ",
					Student.class);
			tq.setParameter("brIndeksa", brIndeksa);
			tq.setParameter("lozinka", lozinka);
			s = tq.getSingleResult();
		} catch (Exception e) {
			s = new Student();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return s;
	}

	public Student getStudentForBrIndeksa(String brIndeksa) {
		EntityManager em = JPAUtils.getEntityManager();
		TypedQuery<Student> tq = em.createQuery("select s from Student s " + "where s.brindeksa =:brIndeksa ",
				Student.class);
		tq.setParameter("brIndeksa", brIndeksa);
		Student s;
		try {
			s = tq.getSingleResult();
		} catch (Exception e) {
			s = null;
			e.printStackTrace();
		}
		em.close();
		return s;
	}

	public Profesor getProfesorForLoginParameters(String korisnicko, String lozinka) {
		EntityManager em = JPAUtils.getEntityManager();
		Profesor prof;
		try {
			TypedQuery<Profesor> tq = em.createQuery(
				"select p from Profesor p " + "where p.korisnickoime =:korisnicko " + "and p.lozinka =:lozinka",
				Profesor.class);
		tq.setParameter("korisnicko", korisnicko);
		tq.setParameter("lozinka", lozinka);
			prof = tq.getSingleResult();
		} catch (Exception e) {
			prof = new Profesor();
			e.printStackTrace();
		}
		em.close();
		return prof;
	}

	public List<Predmet> getPredmetsForDepartman(Departman dep) {
		EntityManager em = JPAUtils.getEntityManager();
		TypedQuery<Predmet> tq = em.createQuery("select p from Predmet p " + "where p.profesor.departman =:dep",
				Predmet.class);
		tq.setParameter("dep", dep);
		List<Predmet> predmetiSaDep = tq.getResultList();
		em.close();
		return predmetiSaDep;
	}

	public Predmet getPredmetForID(Integer idPred) {
		EntityManager em = JPAUtils.getEntityManager();
		Predmet p = em.find(Predmet.class, idPred);
		em.close();
		return p;
	}

	public List<Student> getStudentsSortByDep(Integer godStudija) {
		EntityManager em = JPAUtils.getEntityManager();
		TypedQuery<Student> tq = em.createQuery(
				"select s from Student s " + "where s.godinastudija =:godStudija " + "order by s.departman.naziv desc",
				Student.class);
		tq.setParameter("godStudija", godStudija);
		List<Student> studentiSaGodine = tq.getResultList();
		em.close();
		return studentiSaGodine;
	}

	public List<StudentBrojPolozenih> getStudentBrojPolozenihBeans(Departman dep) {
		EntityManager em = JPAUtils.getEntityManager();
		Query q = em.createQuery("select s, count(p) from Student s " + "join s.prijavas p "
				+ "where p.status = 'polozen' " + "and s.departman =:dep " + "group by s " + "order by count(p) desc");
		q.setParameter("dep", dep);
		@SuppressWarnings("unchecked")
		List<Object[]> data = q.getResultList();
		List<StudentBrojPolozenih> retVal = new ArrayList<>();
		for (Object[] item : data) {
			Integer brPol = Math.toIntExact((Long) item[1]);
			StudentBrojPolozenih sbp = new StudentBrojPolozenih((Student) item[0], brPol);
			retVal.add(sbp);
		}
		em.close();
		return retVal;
	}

	public List<Student> getStudentsForLozinka1234() {
		EntityManager em = JPAUtils.getEntityManager();
		TypedQuery<Student> tq = em.createQuery("select s from Student s " + "where s.lozinka = '1234' ",
				Student.class);
		List<Student> stds = tq.getResultList();
		em.close();
		return stds;
	}

	public List<String> getAllBrIndeksa() {
		EntityManager em = JPAUtils.getEntityManager();
		TypedQuery<String> tq = em.createQuery("select s.brindeksa from Student s", String.class);
		List<String> data = tq.getResultList();
		em.close();
		return data;
	}

	public List<ProfesorBrPredmeta> getProfesorsForDepartmanSortByProf(Departman dep) {
		EntityManager em = JPAUtils.getEntityManager();
		Query tq = em.createQuery("select p, count(pr) from Profesor p " + "join p.predmets pr "
				+ "where p.departman =:dep " + "group by p " + "order by p ");
		tq.setParameter("dep", dep);
		@SuppressWarnings("unchecked")
		List<Object[]> data = tq.getResultList();
		List<ProfesorBrPredmeta> retVal = new ArrayList<>();
		for (Object[] item : data) {
			Integer brPlz = Math.toIntExact((Long) item[1]);
			ProfesorBrPredmeta pbp = new ProfesorBrPredmeta((Profesor) item[0], brPlz);
			retVal.add(pbp);
		}
		em.close();
		return retVal;
	}

	public static void main(String[] args) {

		Student s = new StudentManager().getStudentForLoginParameters("411/133", "s1234");
		System.out.println(s.getIme());
		System.out.println("asdf");

		// Profesor p = new
		// StudentManager().getProfesorForLoginParameters("prof1", "1234");
		// System.out.println(p.getIme());

		// Departman dep = new ProfesorManager().getDepartmandForID(1);
		// List<Predmet> predmetiSaDep = new
		// StudentManager().getPredmetsForDepartman(dep);
		// for(Predmet p: predmetiSaDep){
		// System.out.println(p.getNaziv());
		// }

		// Predmet p = new StudentManager().getPredmetForID(1);
		// System.out.println(p.getNaziv());

		// List<Student> studenti = new
		// StudentManager().getStudentsSortByDep(1);
		// for(Student s: studenti)
		// System.out.println(s.getIme());

		// List<Student> sts = new StudentManager().getStudentsForLozinka1234();
		// for(Student s: sts){
		// System.out.println(s.getIme());
		// }

		// Departman dep = new ProfesorManager().getDepartmandForID(1);
		// List<StudentBrojPolozenih> sbss = new
		// StudentManager().getStudentBrojPolozenihBeans(dep);
		// for(StudentBrojPolozenih s: sbss){
		// System.out.println(s.getStudent().getIme() + " " +
		// s.getBrPolozenih());
		//
		// }

		// List<String> brIndeksa = new StudentManager().getAllBrIndeksa();
		// for(String idx : brIndeksa)
		// System.out.println(idx);

		// Departman dep = new ProfesorManager().getDepartmandForID(1);
		// List<Profesor> profs = new
		// StudentManager().getProfesorsForDepartmanSortByProf(dep);
		// for(Profesor p: profs){
		// System.out.println(p.getIme() + " " + p.getPrezime());
		// for(Predmet pr: p.getPredmets()){
		// System.out.println(pr.getNaziv());
		// }
		// }

		System.exit(0);
	}

}
