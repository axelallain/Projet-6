package fr.axelallain.service;

import java.util.List;

import fr.axelallain.entity.Spot;

public interface SpotService {
	
	public List<Spot> findAllSpotsByTopoId(Long id);
	
	public Long countAllSpotsByTopoId(Long id);
	
	public Spot findSpotById(Long id);

}
