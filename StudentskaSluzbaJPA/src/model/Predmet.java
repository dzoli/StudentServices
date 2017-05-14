package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the predmet database table.
 * 
 */
@Entity
@NamedQuery(name="Predmet.findAll", query="SELECT p FROM Predmet p")
public class Predmet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int predmetid;

	private int espb;

	private int godinaslusanja;

	private String naziv;

	private int semestar;

	private String tip;

	//bi-directional many-to-one association to Profesor
	@ManyToOne
	@JoinColumn(name="PROFESORID")
	private Profesor profesor;

	//bi-directional many-to-one association to Prijava
	@OneToMany(mappedBy="predmet")
	private List<Prijava> prijavas;

	public Predmet() {
	}

	public int getPredmetid() {
		return this.predmetid;
	}

	public void setPredmetid(int predmetid) {
		this.predmetid = predmetid;
	}

	public int getEspb() {
		return this.espb;
	}

	public void setEspb(int espb) {
		this.espb = espb;
	}

	public int getGodinaslusanja() {
		return this.godinaslusanja;
	}

	public void setGodinaslusanja(int godinaslusanja) {
		this.godinaslusanja = godinaslusanja;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getSemestar() {
		return this.semestar;
	}

	public void setSemestar(int semestar) {
		this.semestar = semestar;
	}

	public String getTip() {
		return this.tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public Profesor getProfesor() {
		return this.profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public List<Prijava> getPrijavas() {
		return this.prijavas;
	}

	public void setPrijavas(List<Prijava> prijavas) {
		this.prijavas = prijavas;
	}

	public Prijava addPrijava(Prijava prijava) {
		getPrijavas().add(prijava);
		prijava.setPredmet(this);

		return prijava;
	}

	public Prijava removePrijava(Prijava prijava) {
		getPrijavas().remove(prijava);
		prijava.setPredmet(null);

		return prijava;
	}

}