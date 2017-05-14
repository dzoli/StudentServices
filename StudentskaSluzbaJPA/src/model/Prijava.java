package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the prijava database table.
 * 
 */
@Entity
@NamedQuery(name="Prijava.findAll", query="SELECT p FROM Prijava p")
public class Prijava implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int prijavaid;

	private int bodovi;

	private int brojizlazaka;

	@Temporal(TemporalType.DATE)
	private Date datumprijave;

	private String ispitnirok;

	private int ocena;

	private String status;

	//bi-directional many-to-one association to Predmet
	@ManyToOne
	@JoinColumn(name="PREDMETID")
	private Predmet predmet;

	//bi-directional many-to-one association to Student
	@ManyToOne
	@JoinColumn(name="STUDENTID")
	private Student student;

	public Prijava() {
	}

	public int getPrijavaid() {
		return this.prijavaid;
	}

	public void setPrijavaid(int prijavaid) {
		this.prijavaid = prijavaid;
	}

	public int getBodovi() {
		return this.bodovi;
	}

	public void setBodovi(int bodovi) {
		this.bodovi = bodovi;
	}

	public int getBrojizlazaka() {
		return this.brojizlazaka;
	}

	public void setBrojizlazaka(int brojizlazaka) {
		this.brojizlazaka = brojizlazaka;
	}

	public Date getDatumprijave() {
		return this.datumprijave;
	}

	public void setDatumprijave(Date datumprijave) {
		this.datumprijave = datumprijave;
	}

	public String getIspitnirok() {
		return this.ispitnirok;
	}

	public void setIspitnirok(String ispitnirok) {
		this.ispitnirok = ispitnirok;
	}

	public int getOcena() {
		return this.ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Predmet getPredmet() {
		return this.predmet;
	}

	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}