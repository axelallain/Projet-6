package fr.axelallain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.axelallain.UserPrincipal;
import fr.axelallain.entity.Longueur;
import fr.axelallain.entity.Voie;
import fr.axelallain.service.SpotService;
import fr.axelallain.service.VoieService;

@Controller
public class VoieController {
	
	@Autowired
	private VoieService voieService;
	
	@Autowired
	private SpotService spotService;
	
	@DeleteMapping("/panel/spotsutilisateur/details/{spotid}/deletevoie/{voieid}")
	public String deleteVoie(@PathVariable Long voieid, Long spotid) {
		voieService.deleteVoie(voieid);
		
		return "redirect:/panel/spotsutilisateur/details/" + spotid;
	}
	
	@GetMapping("/panel/spotsutilisateur/details/{spotid}/addvoie")
	public String addVoieForm(@PathVariable Long spotid, Model model) {
		model.addAttribute("voie", new Voie());
		model.addAttribute("spot", spotService.findSpotById(spotid));
		model.addAttribute("longueurs");
		
		model.addAttribute("longueur", new Longueur());
	    
		return "addvoie";
	}
	
	@PostMapping("/panel/spotsutilisateur/details/{spotid}/addvoie")
	public String addVoieSubmit(@PathVariable Long spotid, Voie voie) {		
		voieService.addVoie(voie);
		
		return "redirect:/panel/spotsutilisateur/details/" + spotid;
	}
	
	@GetMapping("/panel/spotsutilisateur/details/{spotid}/modifiervoie/{voieid}")
	public String modifierVoieForm(@PathVariable Long spotid, Long voieid, Model model) {
		model.addAttribute("voie", voieService.findById(voieid));
		
		UserPrincipal cuser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long cuserid = cuser.getId();
        
		model.addAttribute("spots", spotService.findAllSpotsByUtilisateurId(cuserid));
		
		return "modifiervoie";
	}

}