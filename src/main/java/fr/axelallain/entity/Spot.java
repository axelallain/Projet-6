package fr.axelallain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "spot")
public class Spot {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nom", nullable = false)
	private String nom;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "lieu", nullable = false)
	private String lieu;
	
	@Column(name = "officiel")
	private Boolean officiel = false;
	
	@Column(name = "imageUrl")
	private String imageUrl;
	
	@ManyToOne
	@JoinColumn(name = "topo_id")
	private Topo topo;
	
	public Spot() {
		
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public Boolean getOfficiel() {
		return officiel;
	}

	public void setOfficiel(Boolean officiel) {
		this.officiel = officiel;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Topo getTopo() {
		return topo;
	}

	public void setTopo(Topo topo) {
		this.topo = topo;
	}
	
}