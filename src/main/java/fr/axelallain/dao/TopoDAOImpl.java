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
		Query query = em.createQuery("SELECT e FROM Topo e WHERE e.utilisateur.id=:id").setParameter("id", id);
		return (List<Topo>) query.getResultList();
	}

	@Override
	public void modifierTopo(Topo topo) {
		em.merge(topo);
	}
	
	// RECHERCHE PAR CRITERE //
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Topo> findByNomLike(String nom) {
		Query query = em.createQuery("SELECT t FROM Topo t WHERE lower(t.nom) LIKE :nom");
		query.setParameter("nom", '%'+nom.toLowerCase()+'%');
		return (List<Topo>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Topo> findByLieuLike(String lieu) {
		Query query = em.createQuery("SELECT t FROM Topo t WHERE lower(t.lieu) LIKE :lieu");
		query.setParameter("nom", '%'+lieu.toLowerCase()+'%');
		return (List<Topo>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Topo> findByNomLikeAndLieuLike(String nom, String lieu) {
		Query query = em.createQuery("SELECT t FROM Topo t WHERE lower(t.nom) LIKE :nom AND lower(t.lieu) LIKE :lieu");
		query.setParameter("nom", '%'+nom.toLowerCase()+'%').setParameter("lieu", '%'+lieu.toLowerCase()+'%');
		return (List<Topo>) query.getResultList();
	}
	
}