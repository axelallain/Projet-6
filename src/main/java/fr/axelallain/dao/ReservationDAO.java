package fr.axelallain.dao;

import java.util.List;

import fr.axelallain.entity.Reservation;

public interface ReservationDAO {
	
	void addReservation(Reservation reservation);
	
	List<Reservation> findAllReservationsByUtilisateurId(Long id);
	
	List<Reservation> findAllReservationsByLocataireId(Long id);

}
