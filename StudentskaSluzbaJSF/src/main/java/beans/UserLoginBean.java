package beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import manager.StudentManager;
import model.Profesor;
import model.Student;

@ManagedBean(name = "userLoginView")
@SessionScoped
public class UserLoginBean {
	private String nextPage;
	// admin
	private String username;
	private String password;
	// profesor i student
	private String usernamePS;
	private String passwordPS;
	// parametri za StudentCase
	private String imePrezimeStudenta;
	// parametri za ProfesorCase
	private String imePrezimeProfesora;

	public String getNextPage() {
		return nextPage;
	}

	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserLoginBean() {

	}

	public String login() {
		if (username.equals("admin") && password.equals("1234") && (username != null && password != null)) {
			System.out.println(username);
			return "AdminCase?faces-redirect=true";
		} else {
			addMessage("Neispravni parametri", "Pokusajte ponovo!");
			return "Home?faces-redirect=false";
		}
	}

	public void addMessage(String summary, String detail) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(summary, detail);
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		context.addMessage(null, msg);
	}

	public String loginUser() {
		if (usernamePS.contains("/")) {
			// student, broj-indeksa + lozinka
			Student logStudent = new StudentManager().getStudentForLoginParameters(usernamePS, passwordPS);
			if (logStudent.getIme() != null) {
				imePrezimeStudenta = logStudent.getIme() + " ";
				imePrezimeStudenta += logStudent.getPrezime();
				return "StudentCase?faces-redirect=true";
			} else {
				addMessage("Neispravni parametri", "Pokusajte ponovo!");
				return "Home?faces-redirect=false";
			}
		} else {
			// profesor, korsnicko + lozinka
			Profesor logProfesor = new StudentManager().getProfesorForLoginParameters(usernamePS, passwordPS);
			if (logProfesor.getIme() != null) {
				imePrezimeProfesora = logProfesor.getIme() + " ";
				imePrezimeProfesora += logProfesor.getPrezime();
				return "ProfesorCase?faces-redirect=true";
			} else {
				addMessage("Neispravni parametri", "Pokusajte ponovo!");
				return "Home?faces-redirect=false";
			}
		}

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsernamePS() {
		return usernamePS;
	}

	public void setUsernamePS(String usernamePS) {
		this.usernamePS = usernamePS;
	}

	public String getPasswordPS() {
		return passwordPS;
	}

	public void setPasswordPS(String passwordPS) {
		this.passwordPS = passwordPS;
	}

	public String getImePrezimeStudenta() {
		return imePrezimeStudenta;
	}

	public void setImePrezimeStudenta(String imePrezimeStudenta) {
		this.imePrezimeStudenta = imePrezimeStudenta;
	}

	public String getImePrezimeProfesora() {
		return imePrezimeProfesora;
	}

	public void setImePrezimeProfesora(String imePrezimeProfesora) {
		this.imePrezimeProfesora = imePrezimeProfesora;
	}

}