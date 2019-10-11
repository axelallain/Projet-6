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

}
