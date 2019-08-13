package fr.axelallain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import fr.axelallain.entity.Utilisateur;
import fr.axelallain.service.TopoService;
import fr.axelallain.service.UtilisateurService;

@Controller
public class UtilisateurController {

	@Autowired
	private UtilisateurService utilisateurService;
	
	@Autowired
	private TopoService topoService;
	
	@GetMapping("/")
	public String homePage(Model model) {
		model.addAttribute("utilisateurs", utilisateurService.findAllUtilisateurs());
		model.addAttribute("topos", topoService.findAllTopos());
	    
		return "index";
	}
	
	@GetMapping("/inscription")
	public String inscriptionForm(Model model) {
		model.addAttribute("utilisateur", new Utilisateur());
		
		return "inscription";
	}
	
	@PostMapping("/inscription")
	public String inscriptionSubmit(Utilisateur utilisateur, Model model) {
		utilisateurService.inscription(utilisateur);
		
		return "redirect:/";
	}
	
	@GetMapping("/panel")
	public String panel(Model model) {
		model.addAttribute("topos", topoService.findAllTopos());
		
		return "panel";
	}
	
	@GetMapping("/login")
	public String loginForm() {
		
		return "login";
	}
	
}