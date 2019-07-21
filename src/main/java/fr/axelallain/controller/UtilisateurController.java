package fr.axelallain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.axelallain.service.UtilisateurService;

@Controller
public class UtilisateurController {

	@Autowired
	private UtilisateurService utilisateurService;
	
	@GetMapping("/")
	public String homePage(Model model) {
		model.addAttribute("utilisateurs", utilisateurService.findAllUtilisateurs());
		return "index";
	}
	
}
