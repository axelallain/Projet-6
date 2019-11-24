package fr.axelallain.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "utilisateur")
public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Length(max = 12)
	@Column(name = "username", unique = true, nullable = false)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@Column(name = "staff", nullable = false)
	private Boolean staff = false;
	
	@OneToMany(mappedBy="utilisateur", fetch = FetchType.EAGER)
    private Collection<Topo> topos;
	
	@OneToMany(mappedBy="utilisateur")
	private Collection<Spot> spots;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Reservation reservation;
	
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

	public Boolean getStaff() {
		return staff;
	}

	public void setStaff(Boolean staff) {
		this.staff = staff;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	
}
