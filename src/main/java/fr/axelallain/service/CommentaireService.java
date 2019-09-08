package fr.axelallain.service;

import java.util.List;

import fr.axelallain.entity.Commentaire;

public interface CommentaireService {
	
	List<Commentaire> findAllCommentairesBySpotId(Long id);

	void ajouter(Commentaire commentaire);
}
