package fr.axelallain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.axelallain.UserPrincipal;
import fr.axelallain.service.SpotService;
import fr.axelallain.service.TopoService;

@Controller
public class RechercheController {
	
	@Autowired
	private SpotService spotService;
	
	@Autowired
	private TopoService topoService;
	
	public boolean isAuthenticated(){
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
	}
	
	@GetMapping("/search-form")
	public String searchForm(Model model) {
		
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserstaff", user.getStaff());
		}
		
		return "search-form";
	}
	
	@GetMapping("/search-spots")
	public String searchSpots(@RequestParam(defaultValue="") String nom, @RequestParam(defaultValue="") String lieu, @RequestParam(required=false) boolean officiel, Model model) {
		
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserstaff", user.getStaff());
		}
		
		model.addAttribute("spots", spotService.searchSpots(nom, lieu, officiel));
		
		return "search-spots";
	}
	
	@GetMapping("/search-topos")
	public String searchTopos(@RequestParam(defaultValue="") String nom, @RequestParam(defaultValue="") String lieu, Model model) {
		
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserstaff", user.getStaff());
		}
		
		model.addAttribute("topos", topoService.searchTopos(nom, lieu));
		
		return "search-topos";
	}

}