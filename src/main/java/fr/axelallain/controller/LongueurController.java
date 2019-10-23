package fr.axelallain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.axelallain.entity.Longueur;
import fr.axelallain.service.LongueurService;
import fr.axelallain.service.VoieService;

@Controller
public class LongueurController {
	
	@Autowired
	private LongueurService longueurService;
	
	@Autowired
	private VoieService voieService;
	
	@GetMapping("/panel/spotsutilisateur/details/{spotid}/addlongueur")
	public String addLongueurForm(@PathVariable Long spotid, Longueur longueur, Model model) {
		model.addAttribute("longueur", new Longueur());
		model.addAttribute("voies", voieService.findBySpotId(spotid));
		model.addAttribute("spotid", spotid);
		
		return "addlongueur";
	}
	
	@PostMapping("/panel/spotsutilisateur/details/{spotid}/addlongueur")
	public String addLongueurSubmit(@PathVariable Long spotid, Longueur longueur) {
		longueur.setVoie(voieService.findById(longueur.getVoie().getId()));
		longueurService.addLongueur(longueur);
		
		return "redirect:/panel/spotsutilisateur/details/" + spotid;
	}
	
	@DeleteMapping("/panel/spotsutilisateur/details/{spotid}/deletelongueur/{longueurid}")
	public String deleteLongueur(@PathVariable Long longueurid, Long spotid) {
		longueurService.deleteLongueur(longueurid);
		
		return "redirect:/panel/spotsutilisateur/details/" + spotid;
	}
	
	@GetMapping("/panel/spotsutilisateur/details/{spotid}/modifierlongueur/{longueurid}")
	public String modifierLongueurForm(@PathVariable Long spotid, Long longueurid, Model model) {
		model.addAttribute("longueur", longueurService.findById(longueurid));
		
		return "modifierlongueur";
	}
	
	@PostMapping("/panel/spotsutilisateur/details/{spotid}/modifierlongueur/{longueurid}")
	public String modifierLongueurSubmit(@PathVariable Long spotid, Long longueurid, Longueur longueur) {
		longueurService.modifierLongueur(longueur);
		
		return "redirect/panel/spotsutilisateur/details/" + spotid;
	}

}
