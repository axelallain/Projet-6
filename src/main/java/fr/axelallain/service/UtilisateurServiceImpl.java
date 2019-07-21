package fr.axelallain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.axelallain.dao.UtilisateurDAO;

@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {
	
	@Autowired
	private UtilisateurDAO utilisateurDao;
}
