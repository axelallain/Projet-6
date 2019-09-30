package fr.axelallain.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "utilisateur")
public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "username", unique = true, nullable = false)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	
	@Column(name = "prenom", nullable = false)
	private String prenom;
	
	@Column(name = "nom", nullable = false)
	private String nom;
	
	@Column(name = "dateNaissance")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateNaissance;
	
	@Column(name = "numeroTelephone", unique = true, nullable = false)
	private String numeroTelephone;
	
	@Column(name = "codePostal", nullable = false)
	private String codePostal;

	@Column(name = "staff", nullable = false)
	private Boolean staff = false;
	
	@OneToMany(mappedBy="utilisateur", fetch = FetchType.EAGER)
    private Collection<Topo> topos;
	
	@OneToMany(mappedBy="utilisateur", fetch = FetchType.EAGER)
	private Collection<Spot> spots;
	
	public Utilisateur() {
		
	}
	
	public Collection<Topo> getTopos() {
		return topos;
	}
	
	public Collection<Spot> getSpots() {
		return spots;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getNumeroTelephone() {
		return numeroTelephone;
	}

	public void setNumeroTelephone(String numeroTelephone) {
		this.numeroTelephone = numeroTelephone;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public Boolean getStaff() {
		return staff;
	}

	public void setStaff(Boolean staff) {
		this.staff = staff;
	}
	
}
