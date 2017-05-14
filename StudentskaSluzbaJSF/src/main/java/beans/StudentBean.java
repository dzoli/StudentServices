package beans;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import manager.PrijavaManager;
import manager.StudentManager;
import model.Predmet;
import model.Prijava;
import model.Student;

@ManagedBean
@SessionScoped
public class StudentBean {

	// potreban je ulogovani korisnik
	@ManagedProperty(value = "#{userLoginView}")
	private UserLoginBean logUser;

	private Student logStudent;
	private List<Predmet> izabraniPredmeti;
	private List<Predmet> predmetiSaStudentovogDepartmana;
	private Date datumPrijave = new Date();
	private String ispitniRok;
	private Integer brojIzlazaka = 1;
	private String porukaPrijava;
	
	public StudentBean() {
	}

	//operacije...
	public void prijaviPredmete(){
		List<Prijava> sacuvanePrijave = new PrijavaManager().prijaviPredmete(logUser.getUsernamePS(), datumPrijave, ispitniRok,
																	         brojIzlazaka, izabraniPredmeti);
		if(sacuvanePrijave != null){
			porukaPrijava = "Uspesno sacuvane prijave, ID-vi su: ";
			for(Prijava p: sacuvanePrijave){
				porukaPrijava += p.getPrijavaid() + ", ";
			}
			//otkini zarez
			porukaPrijava = porukaPrijava.substring(0,porukaPrijava.length()-2);
		}else{
			porukaPrijava = "Prijave nisu sacuvane!";
		}
		
		//reset value..
		datumPrijave = null;
		ispitniRok = null;
		brojIzlazaka = null;
		izabraniPredmeti = null;
		
	}
	
	
	public Student getLogStudent() {
		return logStudent;
	}

	public void setLogStudent(Student logStudent) {
		this.logStudent = logStudent;
	}

	public UserLoginBean getLogUser() {
		return logUser;
	}

	public void setLogUser(UserLoginBean logUser) {
		this.logUser = logUser;
	}

	public List<Predmet> getPredmetiSaStudentovogDepartmana() {

		logStudent = new StudentManager().getStudentForLoginParameters(logUser.getUsernamePS(),
				logUser.getPasswordPS());

		predmetiSaStudentovogDepartmana = new StudentManager().getPredmetsForDepartman(logStudent.getDepartman());
		return predmetiSaStudentovogDepartmana;
	}

	public void setPredmetiSaStudentovogDepartmana(List<Predmet> predmetiSaStudentovogDepartmana) {
		this.predmetiSaStudentovogDepartmana = predmetiSaStudentovogDepartmana;
	}

	public Date getDatumPrijave() {
		return datumPrijave;
	}

	public void setDatumPrijave(Date datumPrijave) {
		this.datumPrijave = datumPrijave;
	}

	public String getIspitniRok() {
		return ispitniRok;
	}

	public void setIspitniRok(String ispitniRok) {
		this.ispitniRok = ispitniRok;
	}

	public Integer getBrojIzlazaka() {
		return brojIzlazaka;
	}

	public void setBrojIzlazaka(Integer brojIzlazaka) {
		this.brojIzlazaka = brojIzlazaka;
	}

	public String getPorukaPrijava() {
		return porukaPrijava;
	}



	public void setPorukaPrijava(String porukaPrijava) {
		this.porukaPrijava = porukaPrijava;
	}

	public List<Predmet> getIzabraniPredmeti() {
		return izabraniPredmeti;
	}

	public void setIzabraniPredmeti(List<Predmet> izabraniPredmeti) {
		this.izabraniPredmeti = izabraniPredmeti;
	}

}
