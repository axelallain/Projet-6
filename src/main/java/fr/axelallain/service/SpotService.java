package fr.axelallain.service;

import java.util.List;

import fr.axelallain.entity.Spot;

public interface SpotService {
	
	public List<Spot> findAllSpotsByTopoId(Long id);
	
	public Long countAllSpotsByToposId(Long id);
	
	public Spot findSpotById(Long id);
	
	public List<Spot> findAllSpotsByUtilisateurId(Long id);
	
	public void addSpot(Spot spot);
	
	public void deleteSpot(Long id);
	
	public List<Spot> findAllSpots();
	
	List<Spot> findByNameLike(String name);
	
	List<Spot> findAllSpotsByToposId(Long id);

}
