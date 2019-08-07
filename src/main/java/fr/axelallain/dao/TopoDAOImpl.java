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
		Query query = em.createQuery("SELECT e FROM Topo e");
		return (List<Topo>) query.getResultList();
	}
	
	public void addTopo(Topo topo) {
		em.persist(topo);
	}
}
