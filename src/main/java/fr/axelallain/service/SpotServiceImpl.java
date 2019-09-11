package fr.axelallain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.axelallain.dao.SpotDAO;
import fr.axelallain.entity.Spot;

@Service
@Transactional
public class SpotServiceImpl implements SpotService {
	
	@Autowired
	private SpotDAO spotDao;

	@Override
	public List<Spot> findAllSpotsByTopoId(Long id) {
		return spotDao.findAllSpotsByTopoId(id);
	}
	
	@Override
	public Long countAllSpotsByTopoId(Long id) {
		return spotDao.countAllSpotsByTopoId(id);
	}
	
	@Override
	public Spot findSpotById(Long id) {
		return spotDao.findSpotById(id);
	}
	
	@Override
	public List<Spot> findAllSpotsByUtilisateurId(Long id) {
		return spotDao.findAllSpotsByUtilisateurId(id);
	}

	@Override
	public void addSpot(Spot spot) {
		spotDao.addSpot(spot);
	}

	@Override
	public void deleteSpot(Long id) {
		spotDao.deleteSpot(id);
	}
	
	@Override
	public List<Spot> findAllSpots() {
		return spotDao.findAllSpots();
	}
}
