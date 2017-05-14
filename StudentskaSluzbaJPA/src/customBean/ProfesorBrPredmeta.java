package customBean;

import model.Profesor;

public class ProfesorBrPredmeta {
	private Profesor profesor;
	private Integer brPredmeta;
	
	public ProfesorBrPredmeta() {
		// TODO Auto-generated constructor stub
	}

	public ProfesorBrPredmeta(Profesor profesor, Integer brPredmeta) {
		super();
		this.profesor = profesor;
		this.brPredmeta = brPredmeta;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Integer getBrPredmeta() {
		return brPredmeta;
	}

	public void setBrPredmeta(Integer brPredmeta) {
		this.brPredmeta = brPredmeta;
	}
	
	
}
