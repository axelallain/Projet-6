package fr.axelallain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fr.axelallain.service.VoieService;

@Controller
public class VoieController {
	
	@Autowired
	private VoieService voieService;
	
	@DeleteMapping("/panel/spotsutilisateur/details/delete/{id}")
	public String deleteVoie(@PathVariable Long id, Long spotid) {
		voieService.deleteVoie(id);
		
		return "redirect:/panel/spotsutilisateur/details/" + spotid;
	}

}