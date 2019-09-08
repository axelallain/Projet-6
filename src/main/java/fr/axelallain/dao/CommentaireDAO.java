package fr.axelallain.dao;

import java.util.List;

import fr.axelallain.entity.Commentaire;

public interface CommentaireDAO {
	
	List<Commentaire> findAllCommentairesBySpotId(Long id);
	
	void ajouter(Commentaire commentaire);

}
