package fr.axelallain.entity;

import java.util.Collection;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@Column(name = "cotationmin")
	private String cotationmin;
	
	@Column(name = "cotationmax")
	private String cotationmax;
	
	@Column(name = "nbvoies")
	private Long nbvoies;
	
	@Column(name = "nblongueurs")
	private Long nblongueurs;
	
	@ManyToOne
	@JoinColumn(name = "utilisateur_id")
	private Utilisateur utilisateur;
	
	@OneToMany(mappedBy="spot")
	private Collection<Voie> voies;
	
	@ManyToMany(mappedBy = "spots", fetch = FetchType.EAGER)
	private Collection<Topo> topos;
	
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
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
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

	public Long getNbvoies() {
		return nbvoies;
	}

	public void setNbvoies(Long nbvoies) {
		this.nbvoies = nbvoies;
	}

	public Long getNblongueurs() {
		return nblongueurs;
	}

	public void setNblongueurs(Long nblongueurs) {
		this.nblongueurs = nblongueurs;
	}

	public Collection<Voie> getVoies() {
		return voies;
	}

	public void setVoies(Collection<Voie> voies) {
		this.voies = voies;
	}

	public Collection<Topo> getTopos() {
		return topos;
	}

	public void setTopos(Collection<Topo> topos) {
		this.topos = topos;
	}
	
}