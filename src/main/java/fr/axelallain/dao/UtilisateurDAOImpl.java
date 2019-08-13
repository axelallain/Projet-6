package fr.axelallain.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import fr.axelallain.entity.Utilisateur;

@Repository
public class UtilisateurDAOImpl implements UtilisateurDAO {
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Utilisateur> findAllUtilisateurs() {
		Query query = em.createQuery("SELECT e FROM Utilisateur e");
		return (List<Utilisateur>) query.getResultList();
	}

	@Override
	public void inscription(Utilisateur utilisateur) {
		em.persist(utilisateur);
	}

	@Override
	public Utilisateur findByUsername(String username) {
		Utilisateur utilisateur = em.createQuery(
				  "SELECT u from Utilisateur u WHERE u.username = :username", Utilisateur.class).
				  setParameter("username", username).getSingleResult();
		return utilisateur;
	}
	
}