package fr.axelallain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.axelallain.dao.VoieDAO;
import fr.axelallain.entity.Voie;

@Service
@Transactional
public class VoieServiceImpl implements VoieService {
	
	@Autowired
	private VoieDAO voieDao;

	@Override
	public List<Voie> findBySpotId(Long id) {
		return voieDao.findBySpotId(id);
	}

	@Override
	public void deleteVoie(Long id) {
		voieDao.deleteVoie(id);
	}

	@Override
	public void addVoie(Voie voie) {
		voieDao.addVoie(voie);
	}

	@Override
	public Voie findById(Long id) {
		return voieDao.findById(id);
	}

	@Override
	public void modifierVoie(Voie voie) {
		voieDao.modifierVoie(voie);
	}

}
