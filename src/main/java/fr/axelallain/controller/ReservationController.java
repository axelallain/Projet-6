package fr.axelallain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.axelallain.entity.Reservation;
import fr.axelallain.entity.Topo;
import fr.axelallain.service.ReservationService;
import fr.axelallain.service.TopoService;

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
		model.addAttribute("cuserid", cuserid);
		model.addAttribute("reservation", new Reservation());
		
		return "demandesrecues";
	}
	
	@GetMapping("/panel/demandesenvoyees/{cuserid}")
	public String demandesEnvoyees(@PathVariable Long cuserid, Model model) {
		model.addAttribute("reservations", reservationService.findAllReservationsByLocataireId(cuserid));
		model.addAttribute("cuserid", cuserid);
		
		return "demandesenvoyees";
	}
	
	@GetMapping("/panel/demandesenvoyees/{cuserid}/delete/{id}")
	public String deleteDemandeEnvoyee(@PathVariable Long cuserid, @PathVariable Long id) {
		reservationService.deleteReservation(id);
		
		return "redirect:/panel/demandesenvoyees/" + cuserid;
	}
	
	@PostMapping("/panel/accepterdemanderecue/{cuserid}/update/{id}")
	public String accepterDemandeRecue(@PathVariable Long cuserid, @PathVariable Long id, Reservation reservation) {
		reservation.getTopo().setDisponible(false);
		reservationService.updateReservation(reservation);
		
		return "redirect:/panel/demandesrecues/" + cuserid;
	}
	
	@PostMapping("/panel/refuserdemanderecue/{cuserid}/update/{id}")
	public String refuserDemandeRecue(@PathVariable Long cuserid, @PathVariable Long id, Reservation reservation) {
		reservationService.updateReservation(reservation);
		
		return "redirect:/panel/demandesrecues/" + cuserid;
	}

}