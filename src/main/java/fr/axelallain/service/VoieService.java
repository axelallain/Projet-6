package fr.axelallain.service;

import java.util.List;

import fr.axelallain.entity.Voie;

public interface VoieService {
	
	List<Voie> findBySpotId(Long id);
	
	void deleteVoie(Long id);
	
	void addVoie(Voie voie);
	
	Voie findById(Long id);
	
	void modifierVoie(Voie voie);

}
