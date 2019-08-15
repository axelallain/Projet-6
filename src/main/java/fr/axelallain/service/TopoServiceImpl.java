package fr.axelallain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.axelallain.dao.TopoDAO;
import fr.axelallain.entity.Topo;

@Service
@Transactional
public class TopoServiceImpl implements TopoService {

	@Autowired
	private TopoDAO topoDao;

	@Override
	public List<Topo> findAllTopos() {
		return topoDao.findAllTopos();
	}

	@Override
	public void addTopo(Topo topo) {
		topoDao.addTopo(topo);
	}
	
	@Override
	public void deleteTopo(Long id) {
		topoDao.deleteTopo(id);
	}

	@Override
	public Topo findTopoById(Long id) {
		return topoDao.findTopoById(id);
	}

	@Override
	public List<Topo> findAllToposByUtilisateurId(Long id) {
		return topoDao.findAllToposByUtilisateurId(id);
	}
	
}
