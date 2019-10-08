package fr.axelallain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.axelallain.UserPrincipal;
import fr.axelallain.entity.Utilisateur;
import fr.axelallain.service.SpotService;
import fr.axelallain.service.TopoService;
import fr.axelallain.service.UtilisateurService;

@Controller
public class UtilisateurController {

	@Autowired
	private UtilisateurService utilisateurService;
	
	@Autowired
	private TopoService topoService;
	
	@Autowired
	private SpotService spotService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
		utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
		utilisateurService.inscription(utilisateur);
		
		return "redirect:/";
	}
	
	@GetMapping("/panel")
	public String panelUtilisateur(Model model) {
		UserPrincipal cuser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long cuserid = cuser.getId();
        Boolean cuserstaff = cuser.getStaff();
        
        model.addAttribute("cuserstaff", cuserstaff);
        model.addAttribute("cuserid", cuserid);
		
		return "panel";
	}
	
	@GetMapping("/panel/toposutilisateur/{cuserid}")
	public String toposUtilisateur(@PathVariable Long cuserid, Model model) {
		
		UserPrincipal cuser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        cuserid = cuser.getId();
        
        model.addAttribute("topos", topoService.findAllToposByUtilisateurId(cuserid));
		
		return "toposutilisateur";
	}
	
	@GetMapping("/panel/spotsutilisateur/{cuserid}")
	public String spotsUtilisateur(@PathVariable Long cuserid, Model model) {
		
		UserPrincipal cuser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        cuserid = cuser.getId();
        
        model.addAttribute("spots", spotService.findAllSpotsByUtilisateurId(cuserid));
		
		return "spotsutilisateur";
	}
	
	@GetMapping("/login")
	public String loginForm() {
		
		return "login";
	}
	
}