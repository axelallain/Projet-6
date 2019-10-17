package fr.axelallain.service;

import java.util.List;

import fr.axelallain.entity.Longueur;

public interface LongueurService {
	
	void addLongueur(Longueur longueur);
	
	List<Longueur> findByVoieSpotId(Long id);

}
