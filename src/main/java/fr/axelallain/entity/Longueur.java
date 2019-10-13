package fr.axelallain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "longueur")
public class Longueur {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "nom", nullable = false)
	private String nom;
	
	@Column(name = "cotationmin")
	private String cotationmin;
	
	@Column(name = "cotationmax")
	private String cotationmax;
	
	@ManyToOne
	private Voie voie;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCotationmin() {
		return cotationmin;
	}

	public void setCotationmin(String cotationmin) {
		this.cotationmin = cotationmin;
	}

	public String getCotationmax() {
		return cotationmax;
	}

	public void setCotationmax(String cotationmax) {
		this.cotationmax = cotationmax;
	}

	public Voie getVoie() {
		return voie;
	}

	public void setVoie(Voie voie) {
		this.voie = voie;
	}

}
