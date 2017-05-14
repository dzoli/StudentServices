package beans;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import manager.ProfesorManager;
import model.Departman;
import model.Predmet;
import model.Profesor;
import model.Student;

@ManagedBean
public class AdminBean {
	//novi profesor
	private Integer izabraniDepartmanID;
	private List<Departman> sviDepartmani;
	private String imeProf;
	private String prezimeProf;
	private Date datumRodjenjaProf;
	private String adresaProf;
	private String zvanjeProf;
	private String lozinkaProf;
	private String korisnickoImeProf;
	private String porukaProf = null;
	//novi predmet
	private String nazivPred;
	private Integer espbPred;
	private Integer semestarPred;
	private String tipPred;
	private Integer godinaSlusanjaPred;
	private List<Profesor> sviProfesori;
	private Integer izabraniProfesorIDPred;
	private String porukaPred = null; 
	//novi student
	private String imeStud;
	private String prezimeStud;
	private String adresaStud;
	private Date datumRodjenjaStud;
	private String lozinkaStud;
	private String brojIndeksa;
	private String godinaStudija;
	private Integer izabraniDepartmanIDStudent;
	private String porukaStud = null;
	
	
	public AdminBean(){
		sviDepartmani = new ProfesorManager().getAllDepartmans();
		sviProfesori = new ProfesorManager().sviProfesori();
	}
	
	
	//operacije...
	public void SacuvajProfesora(){
		Departman izabraniDep = 
				new ProfesorManager().getDepartmandForID(izabraniDepartmanID);
		Profesor p = new ProfesorManager().sacuvajProfesora(imeProf, prezimeProf, izabraniDep,
													adresaProf, datumRodjenjaProf, zvanjeProf, 
													lozinkaProf, korisnickoImeProf);
		if(p != null){
			porukaProf = "Uspesno sacuvan profesor, ima ID: " + p.getProfesorid();
			addMessageSuccessful("Operacija uspesna!", porukaProf);
		}else{
			porukaProf = "Profesor nije uspesno sacuvan.";
			addMessageError("Greska!", porukaProf);
		}
		sviProfesori = new ProfesorManager().sviProfesori();
		
		//reset...
		imeProf=null;
		prezimeProf = null;
		izabraniDep = null;
		adresaProf = null;
		datumRodjenjaProf = null;
		zvanjeProf = null;
		lozinkaProf = null;
		korisnickoImeProf = null;
	}
	
	public void SacuvajPredmet(){
		Profesor izabraniProf = new ProfesorManager().getProfesorForID(izabraniProfesorIDPred);
		Predmet pred = new ProfesorManager().sacuvajPredmet(nazivPred, semestarPred, 
															godinaSlusanjaPred, tipPred, 
															espbPred, izabraniProf);
		if(pred != null){
			porukaPred = "Uspesno sacuvan predmet, ima ID: " + pred.getPredmetid();
			addMessageSuccessful("Operacja uspesna!", porukaPred);
		}else{
			porukaPred = "Nije uspesno sacuan predmet";
			addMessageError("Greska!", porukaPred);
		}
		
		//reset...
		izabraniProfesorIDPred = null;
		nazivPred = null;
		semestarPred = null;
		godinaSlusanjaPred = null;
		tipPred = null;
		espbPred = null;
		izabraniProf = null;
	}
	
	public void SacuvajStudenta(){
		Departman selectedDep = new ProfesorManager().getDepartmandForID(izabraniDepartmanIDStudent);
		Integer godinaStudija = Integer.parseInt(this.godinaStudija);
		Student stud = new ProfesorManager().sacuvajStudenta(imeStud, prezimeStud, adresaStud, datumRodjenjaStud, lozinkaStud,
															 brojIndeksa, selectedDep, godinaStudija);
		if(stud != null){
			porukaStud = "Uspesno sacuvan studemt, ima ID: " + stud.getStudentid();
			addMessageSuccessful("Operacija uspesna!", porukaStud);
		}else{
			porukaStud = "Student nije uspesno sacuvan";
			addMessageError("Greska", porukaStud);
		}
		
		//reset...
		imeStud = null;
		prezimeStud = null;
		adresaStud = null;
		datumRodjenjaStud = null;
		lozinkaStud = null;
		godinaStudija = null;
		brojIndeksa = null;
	}
	
	public void addMessageSuccessful(String summary, String detail) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(summary, detail);
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		context.addMessage(null, msg);
	}
	
	public void addMessageError(String summary, String detail) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(summary, detail);
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		context.addMessage(null, msg);
	}
	
	public Integer getIzabraniDepartmanID() {
		return izabraniDepartmanID;
	}

	public void setIzabraniDepartmanID(Integer izabraniDepartmanID) {
		this.izabraniDepartmanID = izabraniDepartmanID;
	}

	public List<Departman> getSviDepartmani() {
		return sviDepartmani;
	}

	public void setSviDepartmani(List<Departman> sviDepartmani) {
		this.sviDepartmani = sviDepartmani;
	}

	public String getImeProf() {
		return imeProf;
	}

	public void setImeProf(String imeProf) {
		this.imeProf = imeProf;
	}

	public String getPrezimeProf() {
		return prezimeProf;
	}

	public void setPrezimeProf(String prezimeProf) {
		this.prezimeProf = prezimeProf;
	}

	public Date getDatumRodjenjaProf() {
		return datumRodjenjaProf;
	}

	public void setDatumRodjenjaProf(Date datumRodjenjaProf) {
		this.datumRodjenjaProf = datumRodjenjaProf;
	}

	public String getAdresaProf() {
		return adresaProf;
	}

	public void setAdresaProf(String adresaProf) {
		this.adresaProf = adresaProf;
	}

	public String getZvanjeProf() {
		return zvanjeProf;
	}

	public void setZvanjeProf(String zvanjeProf) {
		this.zvanjeProf = zvanjeProf;
	}

	public String getLozinkaProf() {
		return lozinkaProf;
	}

	public void setLozinkaProf(String lozinkaProf) {
		this.lozinkaProf = lozinkaProf;
	}

	public String getKorisnickoImeProf() {
		return korisnickoImeProf;
	}

	public void setKorisnickoImeProf(String korisnickoImeProf) {
		this.korisnickoImeProf = korisnickoImeProf;
	}

	public String getPorukaProf() {
		return porukaProf;
	}

	public void setPorukaProf(String porukaProf) {
		this.porukaProf = porukaProf;
	}

	public String getNazivPred() {
		return nazivPred;
	}

	public void setNazivPred(String nazivPred) {
		this.nazivPred = nazivPred;
	}

	public Integer getEspbPred() {
		return espbPred;
	}

	public void setEspbPred(Integer espbPred) {
		this.espbPred = espbPred;
	}

	public Integer getSemestarPred() {
		return semestarPred;
	}

	public void setSemestarPred(Integer semestarPred) {
		this.semestarPred = semestarPred;
	}

	public String getTipPred() {
		return tipPred;
	}

	public void setTipPred(String tipPred) {
		this.tipPred = tipPred;
	}

	public Integer getGodinaSlusanjaPred() {
		return godinaSlusanjaPred;
	}

	public void setGodinaSlusanjaPred(Integer godinaSlusanjaPred) {
		this.godinaSlusanjaPred = godinaSlusanjaPred;
	}



	public List<Profesor> getSviProfesori() {
		return sviProfesori;
	}



	public void setSviProfesori(List<Profesor> sviProfesori) {
		this.sviProfesori = sviProfesori;
	}



	public String getPorukaPred() {
		return porukaPred;
	}



	public void setPorukaPred(String porukaPred) {
		this.porukaPred = porukaPred;
	}


	public Integer getIzabraniProfesorIDPred() {
		return izabraniProfesorIDPred;
	}


	public void setIzabraniProfesorIDPred(Integer izabraniProfesorIDPred) {
		this.izabraniProfesorIDPred = izabraniProfesorIDPred;
	}


	public String getImeStud() {
		return imeStud;
	}


	public void setImeStud(String imeStud) {
		this.imeStud = imeStud;
	}


	public String getPrezimeStud() {
		return prezimeStud;
	}


	public void setPrezimeStud(String prezimeStud) {
		this.prezimeStud = prezimeStud;
	}


	public String getAdresaStud() {
		return adresaStud;
	}


	public void setAdresaStud(String adresaStud) {
		this.adresaStud = adresaStud;
	}


	public Date getDatumRodjenjaStud() {
		return datumRodjenjaStud;
	}


	public void setDatumRodjenjaStud(Date datumRodjenjaStud) {
		this.datumRodjenjaStud = datumRodjenjaStud;
	}


	public String getLozinkaStud() {
		return lozinkaStud;
	}


	public void setLozinkaStud(String lozinkaStud) {
		this.lozinkaStud = lozinkaStud;
	}


	public String getPorukaStud() {
		return porukaStud;
	}


	public void setPorukaStud(String porukaStud) {
		this.porukaStud = porukaStud;
	}


	public String getBrojIndeksa() {
		return brojIndeksa;
	}


	public void setBrojIndeksa(String brojIndeksa) {
		this.brojIndeksa = brojIndeksa;
	}


	public Integer getIzabraniDepartmanIDStudent() {
		return izabraniDepartmanIDStudent;
	}


	public void setIzabraniDepartmanIDStudent(Integer izabraniDepartmanIDStudent) {
		this.izabraniDepartmanIDStudent = izabraniDepartmanIDStudent;
	}


	public String getGodinaStudija() {
		return godinaStudija;
	}


	public void setGodinaStudija(String godinaStudija) {
		this.godinaStudija = godinaStudija;
	}
	
}
