package fr.axelallain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.axelallain.dao.ReservationDAO;
import fr.axelallain.entity.Reservation;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	private ReservationDAO reservationDao;

	@Override
	public void addReservation(Reservation reservation) {
		reservationDao.addReservation(reservation);
	}

	@Override
	public List<Reservation> findAllReservationsByUtilisateurId(Long id) {
		return reservationDao.findAllReservationsByUtilisateurId(id);
	}

	@Override
	public List<Reservation> findAllReservationsByLocataireId(Long id) {
		return reservationDao.findAllReservationsByLocataireId(id);
	}

	@Override
	public void deleteReservation(Long id) {
		reservationDao.deleteReservation(id);
	}

	@Override
	public void updateReservation(Reservation reservation) {
		reservationDao.updateReservation(reservation);
	}

}
