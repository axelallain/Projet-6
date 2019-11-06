package fr.axelallain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.axelallain.entity.Reservation;
import fr.axelallain.service.ReservationService;

@Controller
public class ReservationController {
	
	@Autowired
	private ReservationService reservationService;
	
	@PostMapping("/reserver/{topoid}")
	public String reserver(@PathVariable Long topoid, Reservation reservation) {
		reservationService.addReservation(reservation);
		
		return "redirect:/fichetopo/" + topoid;
	}
	
	@GetMapping("/panel/demandesrecues/{cuserid}")
	public String demandesRecues(@PathVariable Long cuserid, Model model) {
		model.addAttribute("reservations", reservationService.findAllReservationsByUtilisateurId(cuserid));
		
		return "demandesrecues";
	}
	
	@GetMapping("/panel/demandesenvoyees/{cuserid}")
	public String demandesEnvoyees(@PathVariable Long cuserid, Model model) {
		model.addAttribute("reservations", reservationService.findAllReservationsByLocataireId(cuserid));
		
		return "demandesenvoyees";
	}

}