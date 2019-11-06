package fr.axelallain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "reservation")
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private Topo topo;
	
	@Column(name = "dateDebutDemandee")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateDebutDemandee;
	
	@Column(name = "dateFinDemandee")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateFinDemandee;
	
	@OneToOne
	private Utilisateur locataire;
	
	@Column(name = "statut")
	private String statut;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Topo getTopo() {
		return topo;
	}

	public void setTopo(Topo topo) {
		this.topo = topo;
	}

	public Date getDateDebutDemandee() {
		return dateDebutDemandee;
	}

	public void setDateDebutDemandee(Date dateDebutDemandee) {
		this.dateDebutDemandee = dateDebutDemandee;
	}

	public Date getDateFinDemandee() {
		return dateFinDemandee;
	}

	public void setDateFinDemandee(Date dateFinDemandee) {
		this.dateFinDemandee = dateFinDemandee;
	}

	public Utilisateur getLocataire() {
		return locataire;
	}

	public void setLocataire(Utilisateur locataire) {
		this.locataire = locataire;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

}
