package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the student database table.
 * 
 */
@Entity
@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int studentid;

	private String adresa;

	private String brindeksa;

	@Temporal(TemporalType.DATE)
	private Date datumrodjenja;

	private int godinastudija;

	private String ime;

	private String lozinka;

	private String prezime;

	//bi-directional many-to-one association to Prijava
	@OneToMany(mappedBy="student")
	private List<Prijava> prijavas;

	//bi-directional many-to-one association to Departman
	@ManyToOne
	@JoinColumn(name="DEPARTMANID")
	private Departman departman;

	public Student() {
	}

	public int getStudentid() {
		return this.studentid;
	}

	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}

	public String getAdresa() {
		return this.adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getBrindeksa() {
		return this.brindeksa;
	}

	public void setBrindeksa(String brindeksa) {
		this.brindeksa = brindeksa;
	}

	public Date getDatumrodjenja() {
		return this.datumrodjenja;
	}

	public void setDatumrodjenja(Date datumrodjenja) {
		this.datumrodjenja = datumrodjenja;
	}

	public int getGodinastudija() {
		return this.godinastudija;
	}

	public void setGodinastudija(int godinastudija) {
		this.godinastudija = godinastudija;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getLozinka() {
		return this.lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public List<Prijava> getPrijavas() {
		return this.prijavas;
	}

	public void setPrijavas(List<Prijava> prijavas) {
		this.prijavas = prijavas;
	}

	public Prijava addPrijava(Prijava prijava) {
		getPrijavas().add(prijava);
		prijava.setStudent(this);

		return prijava;
	}

	public Prijava removePrijava(Prijava prijava) {
		getPrijavas().remove(prijava);
		prijava.setStudent(null);

		return prijava;
	}

	public Departman getDepartman() {
		return this.departman;
	}

	public void setDepartman(Departman departman) {
		this.departman = departman;
	}

}