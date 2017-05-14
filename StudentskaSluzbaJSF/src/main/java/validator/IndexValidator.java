package validator;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import manager.StudentManager;
import model.Student;

@FacesValidator("indeksValidator")
public class IndexValidator implements Validator {

	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String val = (String) value;
		FacesMessage msg = new FacesMessage("Indeks mora biti jedinstven!");
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		List<String> indeksi = new StudentManager().getAllBrIndeksa();
		for (String idx : indeksi) {
			if (idx.equals(val)) {
				Student std = new StudentManager().getStudentForBrIndeksa(idx);
				msg.setDetail("Ovaj broj indeksa vec ima Student: " + std.getIme() + " " + std.getPrezime());
				throw new ValidatorException(msg);
			}
		}

	}

}
