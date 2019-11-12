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

	@Override
	public Long countAllSpotsByToposId(Long id) {
		long count = (long)em.createQuery("SELECT COUNT(s) FROM Spot s JOIN s.topos t WHERE t.id=:id").setParameter("id", id).getSingleResult();
		return count;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spot> findAllSpotsByToposId(Long id) {
		Query query = em.createQuery("SELECT s FROM Spot s JOIN s.topos t WHERE t.id=:id").setParameter("id", id);
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
		Query query = em.createQuery("SELECT e FROM Spot e WHERE e.utilisateur.id=:id").setParameter("id", id);
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
		Query query = em.createQuery("SELECT e FROM Spot e");
		return (List<Spot>) query.getResultList();
	}
	
	// RECHERCHE PAR CRITERE //
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spot> findByOfficiel() {
		Query query = em.createQuery("SELECT s FROM Spot s WHERE s.officiel = true", Spot.class);
		return (List<Spot>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spot> findByNomLike(String nom) {
		Query query = em.createQuery("SELECT e FROM Spot e WHERE lower(e.nom) LIKE :nom", Spot.class);
		query.setParameter("nom", '%'+nom.toLowerCase()+'%');
		return (List<Spot>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spot> findByLieuLike(String lieu) {
		Query query = em.createQuery("SELECT s FROM Spot s WHERE lower(s.lieu) LIKE :lieu", Spot.class);
		query.setParameter("lieu", '%'+lieu.toLowerCase()+'%');
		return (List<Spot>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spot> findByOfficielAndNomLike(String nom) {
		Query query = em.createQuery("SELECT s FROM Spot s WHERE s.officiel = true AND lower(s.nom) LIKE :nom", Spot.class);
		query.setParameter("nom", '%'+nom.toLowerCase()+'%');
		return (List<Spot>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spot> findByOfficielAndLieuLike(String lieu) {
		Query query = em.createQuery("SELECT s FROM Spot s WHERE s.officiel = true AND lower(s.lieu) LIKE :lieu", Spot.class);
		query.setParameter("lieu", '%'+lieu.toLowerCase()+'%');
		return (List<Spot>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spot> findByOfficielAndNomLikeAndLieuLike(String nom, String lieu) {
		Query query = em.createQuery("SELECT s FROM Spot s WHERE s.officiel = true AND lower(s.nom) LIKE :nom AND lower(s.lieu) LIKE :lieu", Spot.class);
		query.setParameter("nom", '%'+nom+'%').setParameter("lieu", '%'+lieu+'%');
		return (List<Spot>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spot> findByNomLikeAndLieuLike(String nom, String lieu) {
		Query query = em.createQuery("SELECT s FROM Spot s WHERE lower(s.nom) LIKE :nom AND lower(s.lieu) LIKE :lieu", Spot.class);
		query.setParameter("nom", '%'+nom+'%').setParameter("lieu", '%'+lieu+'%');
		return (List<Spot>) query.getResultList();
	}
	
}