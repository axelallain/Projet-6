package fr.axelallain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.axelallain.dao.LongueurDAO;
import fr.axelallain.entity.Longueur;

@Service
@Transactional
public class LongueurServiceImpl implements LongueurService {
	
	@Autowired
	private LongueurDAO longueurDao;

	@Override
	public void addLongueur(Longueur longueur) {
		longueurDao.addLongueur(longueur);
	}

}
