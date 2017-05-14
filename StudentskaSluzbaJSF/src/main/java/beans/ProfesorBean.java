package beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import manager.PrijavaManager;
import manager.StudentManager;
import model.Prijava;
import model.Profesor;

@ManagedBean
public class ProfesorBean {

	// potreban je ulogovani korisnik
	@ManagedProperty(value = "#{userLoginView}")
	private UserLoginBean logUser;

	private List<Prijava> svePrijave;
	private Integer ocena;
	private Integer bodovi;
	private String status;

	public ProfesorBean() {

	}

	public List<Prijava> getSvePrijave() {
		Profesor logProf = new StudentManager().getProfesorForLoginParameters(logUser.getUsernamePS(), logUser.getPasswordPS());
		svePrijave = new PrijavaManager().getSvePrijaveForProfesor(logProf);
		return svePrijave;
	}

	public void setSvePrijave(List<Prijava> svePrijave) {
		this.svePrijave = svePrijave;
	}

	public void onRowEdit(RowEditEvent event) {
		Integer prijavaId = ((Prijava) event.getObject()).getPrijavaid();
		Prijava mergePrj = new PrijavaManager().mergePrijava(prijavaId, ocena, bodovi, status); //update
		if (mergePrj != null) {
			FacesMessage msg = new FacesMessage("Prijava izmenjena!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			FacesMessage msg = new FacesMessage("Prijava nije izmenjena!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		//ako se nesto izmenilo opet uzmi sve prijave
		Profesor logProf = new StudentManager().getProfesorForLoginParameters(logUser.getUsernamePS(), logUser.getPasswordPS());
		svePrijave = new PrijavaManager().getSvePrijaveForProfesor(logProf);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Otkazana izmena!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public Integer getOcena() {
		return ocena;
	}

	public void setOcena(Integer ocena) {
		this.ocena = ocena;
	}

	public Integer getBodovi() {
		return bodovi;
	}

	public void setBodovi(Integer bodovi) {
		this.bodovi = bodovi;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserLoginBean getLogUser() {
		return logUser;
	}

	public void setLogUser(UserLoginBean logUser) {
		this.logUser = logUser;
	}

}
