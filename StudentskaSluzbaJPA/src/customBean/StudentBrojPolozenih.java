package customBean;

import model.Student;

public class StudentBrojPolozenih {

	private Student student;
	private Integer brPolozenih;

	
	public StudentBrojPolozenih() {

	}
	
	public StudentBrojPolozenih(Student student, Integer brPolozenih) {
		super();
		this.student = student;
		this.brPolozenih = brPolozenih;
	}


	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Integer getBrPolozenih() {
		return brPolozenih;
	}

	public void setBrPolozenih(Integer brPolozenih) {
		this.brPolozenih = brPolozenih;
	}

}
