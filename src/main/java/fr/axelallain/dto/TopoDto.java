package fr.axelallain.dto;

import java.util.List;

public class TopoDto {
	
	public Long utilisateurId;
	
	public String nom;
	
	public String description;
	
	public String lieu;
	
	List<Integer> spotsId;

	public Long getUtilisateurId() {
		return utilisateurId;
	}

	public void setUtilisateurId(Long utilisateurId) {
		this.utilisateurId = utilisateurId;
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

	public List<Integer> getSpotsId() {
		return spotsId;
	}

	public void setSpotsId(List<Integer> spotsId) {
		this.spotsId = spotsId;
	}

}
