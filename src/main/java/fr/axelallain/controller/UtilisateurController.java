package fr.axelallain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.axelallain.UserPrincipal;
import fr.axelallain.entity.Commentaire;
import fr.axelallain.entity.Spot;
import fr.axelallain.entity.Utilisateur;
import fr.axelallain.service.CommentaireService;
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
	private CommentaireService commentaireService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public boolean isAuthenticated(){
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
	}
	
	@GetMapping("/")
	public String homePage(Model model) {
		
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserstaff", user.getStaff());
		}
	    
		return "index";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model) {
        
        if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserid", user.getId());
			model.addAttribute("cuserstaff", user.getStaff());
		}
		
		return "dashboard";
	}
	
	@GetMapping("/dashboard/my-spots")
	public String mySpots(Model model) {
		
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserstaff", user.getStaff());
		}
		
		UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long cuserid = user.getId();
        
        model.addAttribute("spots", spotService.findAllSpotsByUtilisateurId(cuserid));
		
		return "my-spots";
	}
	
	@GetMapping("/dashboard/my-topos")
	public String myTopos(Model model) {
		
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserstaff", user.getStaff());
		}
		
		UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long cuserid = user.getId();
        
        model.addAttribute("topos", topoService.findAllToposByUtilisateurId(cuserid));
		
		return "my-topos";
	}
	
	// ADMINISTRATION
	
	@GetMapping("/dashboard-admin")
	public String dashboardAdmin(Model model) {
		
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserstaff", user.getStaff());
		}
		
		UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(user.getStaff() == true) {
			return "dashboard-admin";
		} else {
			return "redirect:/";
		}
	}
	
	@GetMapping("/dashboard-admin/admin-spots")
	public String adminSpots(Model model) {
		
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserstaff", user.getStaff());
		}
		
		UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(user.getStaff() == true) {
			model.addAttribute("spots", spotService.findAllSpots());
			return "admin-spots";
		} else {
			return "redirect:/";
		}
	}
	
	@GetMapping("/dashboard-admin/admin-spots/delete/{id}")
	public String adminSpotsDelete(@PathVariable Long id) {
		UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(user.getStaff() == true) {
			spotService.deleteSpot(id);
			return "redirect:/dashboard-admin/admin-spots";
		} else {
			return "redirect:/";
		}
	}
	
	@GetMapping("/dashboard-admin/admin-spots/edit/{id}")
	public String adminSpotsEditForm(@PathVariable Long id, Model model) {
		
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserstaff", user.getStaff());
		}
		
		UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(user.getStaff() == true) {
			model.addAttribute("spot", spotService.findSpotById(id));
			return "admin-spots-edit";
		} else {
			return "redirect:/";
		}
	}
	
	@PostMapping("/dashboard-admin/admin-spots/edit/{id}")
	public String adminSpotsEditSubmit(@PathVariable Long id, Model model, Spot spot) {
		
		UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(user.getStaff() == true) {
			spotService.addSpot(spot);
			return "redirect:/dashboard-admin/admin-spots";
		} else {
			return "redirect:/";
		}
	}
	
	@GetMapping("/dashboard-admin/admin-comments")
	public String adminComments(Model model) {
		
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserstaff", user.getStaff());
		}
		
		UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(user.getStaff() == true) {
			model.addAttribute("commentaires", commentaireService.findAllCommentaires());
			return "admin-comments";
		} else {
			return "redirect:/";
		}
	}
	
	@GetMapping("/dashboard-admin/admin-comments/delete/{id}")
	public String adminCommentsDelete(@PathVariable Long id) {
		UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(user.getStaff() == true) {
			commentaireService.deleteCommentaire(id);
			return "redirect:/dashboard-admin/admin-comments";
		} else {
			return "redirect:/";
		}
	}
	
	@GetMapping("/dashboard-admin/admin-comments/edit/{id}")
	public String adminCommentsEditForm(@PathVariable Long id, Model model) {
		
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserstaff", user.getStaff());
		}
		
		UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(user.getStaff() == true) {
			model.addAttribute("commentaire", commentaireService.findById(id));
			return "admin-comments-edit";
		} else {
			return "redirect:/";
		}
	}
	
	@PostMapping("/dashboard-admin/admin-comments/edit/{id}")
	public String adminCommentsEditSubmit(@PathVariable Long id, Model model, Commentaire commentaire) {
		
		UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(user.getStaff() == true) {
			commentaireService.ajouter(commentaire);
			return "redirect:/dashboard-admin/admin-comments";
		} else {
			return "redirect:/";
		}
	}
	
	//
	
	@GetMapping("/login")
	public String loginForm(Utilisateur utilisateur, Model model) {
		model.addAttribute("utilisateur", new Utilisateur());
		
		return "login";
	}
	
	@PostMapping("/signup")
	public String signupSubmit(Utilisateur utilisateur, Model model) {
		utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
		utilisateurService.inscription(utilisateur);
		
		return "redirect:/";
	}
	
}