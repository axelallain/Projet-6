package fr.axelallain.dao;

import java.util.List;

import fr.axelallain.entity.Spot;

public interface SpotDAO {
	
	public List<Spot> findAllSpotsByTopoId(Long id);
	
	public Long countAllSpotsByTopoId(Long id);
	
	public Spot findSpotById(Long id);
	
	public List<Spot> findAllSpotsByUtilisateurId(Long id);
	
	public void addSpot(Spot spot);
	
	public void deleteSpot(Long id);
	
	public List<Spot> findAllSpots();
	
	List<Spot> findByNameLike(String name);
	
}
