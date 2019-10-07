package fr.axelallain.dao;

import java.util.List;

import fr.axelallain.entity.Utilisateur;

public interface UtilisateurDAO {

	List<Utilisateur> findAllUtilisateurs();
	
	public void inscription(Utilisateur utilisateur);
	
	Utilisateur findByUsername(String username);
	
	Utilisateur findById(Long id);
}
