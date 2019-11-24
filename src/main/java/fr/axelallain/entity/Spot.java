package fr.axelallain.entity;

import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "spot")
public class Spot {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Length(max = 50)
	@Column(name = "nom", nullable = false)
	private String nom;
	
	@Length(max = 50)
	@Column(name = "lieu", nullable = false)
	private String lieu;
	
	@Length(max = 250)
	@Column(name = "description", nullable = false)
	private String description;
	
	@Length(max = 2)
	@Column(name = "cotationmin")
	private String cotationmin;
	
	@Length(max = 2)
	@Column(name = "cotationmax")
	private String cotationmax;
	
	@Column(name = "imageUrl")
	private String imageUrl;
	
	@Column(name = "officiel")
	private Boolean officiel = false;
	
	@Column(name = "dateParution", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false, nullable = false)
	private Timestamp dateParution;
	
	@ManyToOne
	@JoinColumn(name = "utilisateur_id")
	private Utilisateur utilisateur;
	
	@OneToMany(mappedBy="spot", cascade = CascadeType.ALL)
	private Collection<Voie> voies;
	
	@ManyToMany(mappedBy = "spots", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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

	public Timestamp getDateParution() {
		return dateParution;
	}

	public void setDateParution(Timestamp dateParution) {
		this.dateParution = dateParution;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
}