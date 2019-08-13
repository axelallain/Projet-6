package fr.axelallain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.axelallain.dao.UtilisateurDAO;
import fr.axelallain.entity.Utilisateur;

@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {
	
	@Autowired
	private UtilisateurDAO utilisateurDao;
	
	@Override
	public List<Utilisateur> findAllUtilisateurs() {
		return utilisateurDao.findAllUtilisateurs();
	}

	@Override
	public void inscription(Utilisateur utilisateur) {
		utilisateurDao.inscription(utilisateur);
	}

	@Override
	public Utilisateur findByUsername(String username) {
		return utilisateurDao.findByUsername(username);
	}
	
}
