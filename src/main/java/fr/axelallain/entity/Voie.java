package fr.axelallain.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "voie")
public class Voie {
	
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
	private Spot spot;
	
	@OneToMany(mappedBy = "voie")
	private Collection<Longueur> longueurs;

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

	public Spot getSpot() {
		return spot;
	}

	public void setSpot(Spot spot) {
		this.spot = spot;
	}

	public Collection<Longueur> getLongueurs() {
		return longueurs;
	}

	public void setLongueurs(Collection<Longueur> longueurs) {
		this.longueurs = longueurs;
	}

}
