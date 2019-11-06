package fr.axelallain.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import fr.axelallain.entity.Reservation;

@Repository
public class ReservationDAOImpl implements ReservationDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void addReservation(Reservation reservation) {
		em.persist(reservation);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> findAllReservationsByUtilisateurId(Long id) {
		Query query = em.createQuery("SELECT r FROM Reservation r WHERE r.topo.utilisateur.id=:id").setParameter("id", id);
		return (List<Reservation>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> findAllReservationsByLocataireId(Long id) {
		Query query = em.createQuery("SELECT r FROM Reservation r WHERE r.locataire.id=:id").setParameter("id", id);
		return (List<Reservation>) query.getResultList();
	}

}
