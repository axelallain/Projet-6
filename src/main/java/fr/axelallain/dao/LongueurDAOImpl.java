package fr.axelallain.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import fr.axelallain.entity.Longueur;

@Repository
public class LongueurDAOImpl implements LongueurDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void addLongueur(Longueur longueur) {
		em.persist(longueur);
	}

}
