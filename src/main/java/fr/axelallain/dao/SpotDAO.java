package fr.axelallain.dao;

import java.util.List;

import fr.axelallain.entity.Spot;

public interface SpotDAO {
	
	public List<Spot> findAllSpotsByTopoId(Long id);
	
	public Long countAllSpotsByToposId(Long id);
	
	public Spot findSpotById(Long id);
	
	public List<Spot> findAllSpotsByUtilisateurId(Long id);
	
	public void addSpot(Spot spot);
	
	public void deleteSpot(Long id);
	
	public List<Spot> findAllSpots();
	
	List<Spot> findAllSpotsByToposId(Long id);
	
	// RECHERCHE PAR CRITERE //
	
	List<Spot> findByOfficiel();
	
	List<Spot> findByNomLike(String nom);
	
	List<Spot> findByLieuLike(String lieu);
	
	List<Spot> findByOfficielAndNomLike(String nom);
	
	List<Spot> findByOfficielAndLieuLike(String lieu);
	
	List<Spot> findByOfficielAndNomLikeAndLieuLike(String nom, String lieu);
	
	List<Spot> findByNomLikeAndLieuLike(String nom, String lieu);
	
}
