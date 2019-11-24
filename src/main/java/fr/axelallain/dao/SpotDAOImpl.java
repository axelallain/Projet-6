package fr.axelallain.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import fr.axelallain.entity.Spot;

@Repository
public class SpotDAOImpl implements SpotDAO {
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Spot> findAllSpotsByTopoId(Long id) {
		Query query = em.createQuery("SELECT e FROM Spot e WHERE e.topo.id=:id").setParameter("id", id);
		return (List<Spot>) query.getResultList();
	}

	@Override
	public Long countAllSpotsByToposId(Long id) {
		long count = (long)em.createQuery("SELECT COUNT(s) FROM Spot s JOIN s.topos t WHERE t.id=:id").setParameter("id", id).getSingleResult();
		return count;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spot> findAllSpotsByToposId(Long id) {
		Query query = em.createQuery("SELECT s FROM Spot s JOIN s.topos t WHERE t.id=:id ORDER BY s.id ASC").setParameter("id", id);
		return (List<Spot>) query.getResultList();
	}

	@Override
	public Spot findSpotById(Long id) {
		Spot spot = em.find(Spot.class, id);
		
		return spot;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spot> findAllSpotsByUtilisateurId(Long id) {
		Query query = em.createQuery("SELECT s FROM Spot s WHERE s.utilisateur.id=:id ORDER BY s.id ASC").setParameter("id", id);
		return (List<Spot>) query.getResultList();
	}

	@Override
	public void addSpot(Spot spot) {
		em.merge(spot);
	}

	@Override
	public void deleteSpot(Long id) {
		// em.createQuery("delete from Longueur l where l.voie.spot.id=:id").setParameter("id", id).executeUpdate();
		// em.createQuery("delete from Voie v where v.spot.id=:id").setParameter("id", id).executeUpdate();
		// em.createQuery("delete from Spot t where t.id=:id").setParameter("id", id).executeUpdate();
		Spot spot = em.find(Spot.class, id);
		em.remove(spot);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spot> findAllSpots() {
		Query query = em.createQuery("SELECT e FROM Spot e ORDER BY dateParution DESC");
		return (List<Spot>) query.getResultList();
	}
	
	// RECHERCHE PAR CRITERE //

	@SuppressWarnings("unchecked")
	@Override
	public List<Spot> searchSpots(String nom, String lieu, boolean officiel) {
		String queryString = "SELECT s FROM Spot s WHERE 1=1";
		
		if(!nom.isEmpty()) {
			queryString = queryString + " AND lower(s.nom) LIKE :nom";
		}
		
		if(!lieu.isEmpty()) {
			queryString = queryString + " AND lower(s.lieu) LIKE :lieu";
		}
		
		if(officiel == true) {
			queryString = queryString + " AND s.officiel = true";
		}
		
		queryString = queryString + " ORDER BY dateParution DESC";
		
		Query query = em.createQuery(queryString, Spot.class);
		
		if(!nom.isEmpty()) {
			query.setParameter("nom", '%'+nom+'%');
		}
		
		if(!lieu.isEmpty()) {
			query.setParameter("lieu", '%'+lieu+'%');
		}
		
		return (List<Spot>) query.getResultList();
	}
	
}