package fr.axelallain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.axelallain.UserPrincipal;
import fr.axelallain.entity.Reservation;
import fr.axelallain.entity.Topo;
import fr.axelallain.service.ReservationService;
import fr.axelallain.service.TopoService;

@Controller
public class ReservationController {
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private TopoService topoService;
	
	public boolean isAuthenticated(){
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
	}
	
	@PostMapping("/reserver/{topoid}")
	public String reserver(@PathVariable Long topoid, Reservation reservation) {
		reservationService.addReservation(reservation);
		
		return "redirect:/dashboard/reservation-sent";
	}
	
	@GetMapping("/dashboard/reservation-received")
	public String reservationReceived(Model model) {
		
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserstaff", user.getStaff());
		}
		
		UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("reservations", reservationService.findAllReservationsByUtilisateurId(user.getId()));
		
		return "reservation-received";
	}
	
	@GetMapping("/dashboard/reservation-sent")
	public String reservationSent(Model model) {
		
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserstaff", user.getStaff());
		}
		
		UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("reservations", reservationService.findAllReservationsByLocataireId(user.getId()));
		
		return "reservation-sent";
	}
	
	@GetMapping("/dashboard/reservation-sent/delete/{id}")
	public String deleteReservationSent(@PathVariable Long id) {
		reservationService.deleteReservation(id);
		
		return "redirect:/dashboard/reservation-sent";
	}
	
	@PostMapping("/dashboard/reservation-received/refuse/{id}")
	public String refuseReservationReceived(@PathVariable Long id, Reservation reservation) {
		reservationService.updateReservation(reservation);
		
		return "redirect:/dashboard/reservation-received";
	}
	
	@PostMapping("/dashboard/reservation-received/accept/{id}")
	public String acceptReservationReceived(@PathVariable Long id, Reservation reservation) {
		Reservation old = reservationService.findById(id);
		
		Topo topo = topoService.findTopoById(old.getTopo().getId());
		topo.setDisponible(false);
		reservation.setTopo(topo);
		
		reservationService.updateReservation(reservation);
		
		return "redirect:/dashboard/reservation-received";
	}

}