package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the departman database table.
 * 
 */
@Entity
@NamedQuery(name="Departman.findAll", query="SELECT d FROM Departman d")
public class Departman implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int departmanid;

	private String naziv;

	//bi-directional many-to-one association to Profesor
	@OneToMany(mappedBy="departman")
	private List<Profesor> profesors;

	//bi-directional many-to-one association to Student
	@OneToMany(mappedBy="departman")
	private List<Student> students;

	public Departman() {
	}

	public int getDepartmanid() {
		return this.departmanid;
	}

	public void setDepartmanid(int departmanid) {
		this.departmanid = departmanid;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Profesor> getProfesors() {
		return this.profesors;
	}

	public void setProfesors(List<Profesor> profesors) {
		this.profesors = profesors;
	}

	public Profesor addProfesor(Profesor profesor) {
		getProfesors().add(profesor);
		profesor.setDepartman(this);

		return profesor;
	}

	public Profesor removeProfesor(Profesor profesor) {
		getProfesors().remove(profesor);
		profesor.setDepartman(null);

		return profesor;
	}

	public List<Student> getStudents() {
		return this.students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Student addStudent(Student student) {
		getStudents().add(student);
		student.setDepartman(this);

		return student;
	}

	public Student removeStudent(Student student) {
		getStudents().remove(student);
		student.setDepartman(null);

		return student;
	}

}