package fr.axelallain.dao;

import java.util.List;

import fr.axelallain.entity.Voie;

public interface VoieDAO {
	
	List<Voie> findBySpotId(Long id);
	
	void deleteVoie(Long id);
	
	void addVoie(Voie voie);
	
	Voie findById(Long id);
	
	void editVoie(Voie voie);

}
