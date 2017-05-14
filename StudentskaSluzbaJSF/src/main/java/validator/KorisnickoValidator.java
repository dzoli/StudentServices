package validator;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import manager.ProfesorManager;
import model.Profesor;

@FacesValidator("korisnickoValidator")
public class KorisnickoValidator implements Validator {

	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String val = (String) value;
		FacesMessage msg = new FacesMessage("Korisnicko ime mora biti jedinstveno!");
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		List<String> korisnickaImena = new ProfesorManager().getKorisnickaImena();
		for(String ki: korisnickaImena){
			if(ki.equals(val)){
				Profesor prof = new ProfesorManager().getProfesorForKorisnicko(ki);
				msg.setDetail("Ovo korisnicko vec ima Profesor: " + prof.getIme() + " " + prof.getPrezime());
				throw new ValidatorException(msg);
			}
		}
		
		
	}
	

	
	
}
