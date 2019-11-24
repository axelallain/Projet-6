package fr.axelallain.entity;

import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "topo")
public class Topo {

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
	
	@Column(name = "imageUrl")
	private String imageUrl;
	
	@Column(name = "disponible")
	private Boolean disponible = true;
	
	@Column(name = "dateParution", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false, nullable = false)
	private Timestamp dateParution;
	
	@ManyToOne
	@JoinColumn(name = "utilisateur_id")
	private Utilisateur utilisateur;
	
	@ManyToMany
	@JoinTable(
			name = "topo_spot",
			joinColumns = { @JoinColumn(name = "topo_id") },
			inverseJoinColumns = { @JoinColumn(name = "spot_id") } )
    private Collection<Spot> spots;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Reservation reservation;
	
	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}
	
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	public Topo() {
		
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

	public Timestamp getDateParution() {
		return dateParution;
	}

	public void setDateParution(Timestamp dateParution) {
		this.dateParution = dateParution;
	}

	public Boolean getDisponible() {
		return disponible;
	}

	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Collection<Spot> getSpots() {
		return spots;
	}

	public void setSpots(Collection<Spot> spots) {
		this.spots = spots;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	
}
