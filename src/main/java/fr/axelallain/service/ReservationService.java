package fr.axelallain.service;

import java.util.List;

import fr.axelallain.entity.Reservation;

public interface ReservationService {
	
	void addReservation(Reservation reservation);
	
	List<Reservation> findAllReservationsByUtilisateurId(Long id);
	
	List<Reservation> findAllReservationsByLocataireId(Long id);
	
	void deleteReservation(Long id);
	
	void updateReservation(Reservation reservation);

}
