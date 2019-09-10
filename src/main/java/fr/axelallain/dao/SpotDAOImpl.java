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

	public Long countAllSpotsByTopoId(Long id) {
		long count = (long)em.createQuery("SELECT COUNT(e) FROM Spot e WHERE e.topo.id=:id").setParameter("id", id).getSingleResult();
		return count;
	}
	
	@Override
	public Spot findSpotById(Long id) {
		Spot spot = em.find(Spot.class, id);
		
		return spot;
	}
}