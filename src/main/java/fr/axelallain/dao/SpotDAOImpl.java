package fr.axelallain.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import fr.axelallain.entity.Spot;
import fr.axelallain.entity.Topo;

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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spot> findAllSpotsByUtilisateurId(Long id) {
		Query query = em.createQuery("SELECT e FROM Spot e WHERE e.utilisateur.id=:id").setParameter("id", id);
		return (List<Spot>) query.getResultList();
	}

	@Override
	public void addSpot(Spot spot) {
		em.merge(spot);
	}

	@Override
	public void deleteSpot(Long id) {
		em.createQuery("delete from Spot t where t.id=:id").setParameter("id", id).executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spot> findAllSpots() {
		Query query = em.createQuery("SELECT e FROM Spot e");
		return (List<Spot>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spot> findByNameLike(String name) {
		Query query = em.createQuery("select e from Spot e where e.nom LIKE CONCAT('%',:name,'%')", Spot.class);
		query.setParameter("name", name);
		return (List<Spot>) query.getResultList();
	}
	
}