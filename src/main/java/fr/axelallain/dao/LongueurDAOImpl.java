package fr.axelallain.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import fr.axelallain.entity.Commentaire;
import fr.axelallain.entity.Longueur;

@Repository
public class LongueurDAOImpl implements LongueurDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void addLongueur(Longueur longueur) {
		em.persist(longueur);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Longueur> findByVoieSpotId(Long id) {
		Query query = em.createQuery("SELECT e FROM Longueur e WHERE e.voie.spot.id=:id").setParameter("id", id);
		return (List<Longueur>) query.getResultList();
	}

	@Override
	public void deleteLongueur(Long id) {
		em.createQuery("delete from Longueur e where e.id=:id").setParameter("id", id).executeUpdate();
	}

	@Override
	public Longueur findById(Long id) {
		Longueur longueur = em.find(Longueur.class, id);
		return longueur;
	}

	@Override
	public void modifierLongueur(Longueur longueur) {
		em.merge(longueur);
	}

}
