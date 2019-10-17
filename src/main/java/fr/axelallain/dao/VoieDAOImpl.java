package fr.axelallain.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import fr.axelallain.entity.Spot;
import fr.axelallain.entity.Voie;

@Repository
public class VoieDAOImpl implements VoieDAO {
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Voie> findBySpotId(Long id) {
		Query query = em.createQuery("SELECT e FROM Voie e WHERE e.spot.id=:id").setParameter("id", id);
		return (List<Voie>) query.getResultList();
	}

	@Override
	public void deleteVoie(Long id) {
		em.createQuery("DELETE from Voie e WHERE e.id=:id").setParameter("id", id).executeUpdate();
	}

	@Override
	public void addVoie(Voie voie) {
		em.merge(voie);
	}

	@Override
	public Voie findById(Long id) {
		Voie voie = em.find(Voie.class, id);
		return voie;
	}

}
