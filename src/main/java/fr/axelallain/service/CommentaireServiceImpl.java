package fr.axelallain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.axelallain.dao.CommentaireDAO;
import fr.axelallain.entity.Commentaire;

@Service
@Transactional
public class CommentaireServiceImpl implements CommentaireService {
	
	@Autowired
	private CommentaireDAO commentaireDao;
	
	public List<Commentaire> findAllCommentairesBySpotId(Long id) {
		return commentaireDao.findAllCommentairesBySpotId(id);
	}
	
	@Override
	public void ajouter(Commentaire commentaire) {
		commentaireDao.ajouter(commentaire);
	}

}
