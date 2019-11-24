package fr.axelallain.service;

import java.util.List;

import fr.axelallain.entity.Topo;

public interface TopoService {

	public List<Topo> findAllTopos();
	
	public void addTopo(Topo topo);
	
	public void deleteTopo(Long id);
	
	public Topo findTopoById(Long id);
	
	public List<Topo> findAllToposByUtilisateurId(Long id);
	
	void editTopo(Topo topo);
	
	// RECHERCHE PAR CRITERE //
	
	List<Topo> searchTopos(String nom, String lieu);
}
