package fr.axelallain.dao;

import java.util.List;

import fr.axelallain.entity.Longueur;

public interface LongueurDAO {
	
	void addLongueur(Longueur longueur);
	
	List<Longueur> findByVoieSpotId(Long id);

}
