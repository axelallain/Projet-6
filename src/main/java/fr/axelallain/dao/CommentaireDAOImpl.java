package fr.axelallain.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import fr.axelallain.entity.Commentaire;

@Repository
public class CommentaireDAOImpl implements CommentaireDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Commentaire> findAllCommentairesBySpotId(Long id) {
		Query query = em.createQuery("SELECT e FROM Commentaire e WHERE e.spot.id=:id ORDER BY dateParution DESC").setParameter("id", id);
		return (List<Commentaire>) query.getResultList();
	}
	
	@Override
	public void ajouter(Commentaire commentaire) {
		em.merge(commentaire);
	}

}
