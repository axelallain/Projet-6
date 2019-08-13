package fr.axelallain.service;

import java.util.List;

import fr.axelallain.entity.Topo;

public interface TopoService {

	public List<Topo> findAllTopos();
	
	public void addTopo(Topo topo);
	
}
