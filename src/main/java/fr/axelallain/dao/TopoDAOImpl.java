package fr.axelallain.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import fr.axelallain.entity.Topo;

@Repository
public class TopoDAOImpl implements TopoDAO {
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Topo> findAllTopos() {
		Query query = em.createQuery("SELECT e FROM Topo e ORDER BY dateParution DESC");
		return (List<Topo>) query.getResultList();
	}
	
	@Override
	public void addTopo(Topo topo) {
		em.persist(topo);
	}
	
	@Override
	public void deleteTopo(Long id) {
		em.createQuery("delete from Topo t where t.id=:id").setParameter("id", id).executeUpdate();
	}

	@Override
	public Topo findTopoById(Long id) {
		Topo topo = em.find(Topo.class, id);
		
		return topo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Topo> findAllToposByUtilisateurId(Long id) {
		Query query = em.createQuery("SELECT e FROM Topo e WHERE e.utilisateur.id=:id ORDER BY id ASC").setParameter("id", id);
		return (List<Topo>) query.getResultList();
	}

	@Override
	public void editTopo(Topo topo) {
		em.merge(topo);
	}
	
	// RECHERCHE PAR CRITERE //

	@SuppressWarnings("unchecked")
	@Override
	public List<Topo> searchTopos(String nom, String lieu) {
		String queryString = "SELECT t FROM Topo t WHERE 1=1";
		
		if(!nom.isEmpty()) {
			queryString = queryString + " AND lower(t.nom) LIKE :nom";
		}
		
		if(!lieu.isEmpty()) {
			queryString = queryString + " AND lower(t.lieu) LIKE :lieu";
		}
		
		queryString = queryString + " ORDER BY dateParution DESC";
		
		Query query = em.createQuery(queryString, Topo.class);
		
		if(!nom.isEmpty()) {
			query.setParameter("nom", '%'+nom+'%');
		}
		
		if(!lieu.isEmpty()) {
			query.setParameter("lieu", '%'+lieu+'%');
		}
		
		return (List<Topo>) query.getResultList();
	}
	
}