package fr.axelallain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.axelallain.UserPrincipal;
import fr.axelallain.entity.Commentaire;
import fr.axelallain.entity.Spot;
import fr.axelallain.entity.Topo;
import fr.axelallain.service.CommentaireService;
import fr.axelallain.service.SpotService;

@Controller
public class SpotController {
	
	@Autowired
	private SpotService spotService;
	
	@Autowired
	private CommentaireService commentaireService;
	
	@GetMapping("/panel/spotsutilisateur/addspot")
	public String addSpotForm(Model model) {
		model.addAttribute("spot", new Spot());
		
		UserPrincipal cuser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long cuserid = cuser.getId();
        
        model.addAttribute("cuserid", cuserid);
	    
		return "addSpot";
	}
	
	@PostMapping("/panel/spotsutilisateur/addspot")
	public String addSpotSubmit(Spot spot, Model model) {
		spotService.addSpot(spot);
		
		UserPrincipal cuser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		return "redirect:/panel/spotsutilisateur/" + cuser.getId();
	}
	
	@DeleteMapping("/panel/spotsutilisateur/delete/{id}")
	public String deleteSpot(@PathVariable Long id) {
		spotService.deleteSpot(id);
		
		UserPrincipal cuser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		return "redirect:/panel/spotsutilisateur/" + cuser.getId();
	}
	
	@GetMapping("/panel/spotsutilisateur/modifier/{id}")
	public ModelAndView modifierSpotForm(@PathVariable Long id, Model model) {
		
		UserPrincipal cuser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long cuserid = cuser.getId();
        
        model.addAttribute("cuserid", cuserid);
		
	    Spot spot = spotService.findSpotById(id);

	    ModelAndView modelAndView = new ModelAndView();

	    modelAndView.setViewName("modifierspot");
	    modelAndView.addObject("spot", spot);

	    return modelAndView;
	}
	
	@GetMapping("/fichespot/{spotid}")
	public String ficheTopo(@PathVariable Long spotid, Model model) {
		model.addAttribute("spotid", spotid);
		model.addAttribute("spot", spotService.findSpotById(spotid));
		model.addAttribute("commentaire", new Commentaire());
		
		UserPrincipal cuser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long cuserid = cuser.getId();
		model.addAttribute("cuserid", cuserid);
		
		model.addAttribute("commentaires", commentaireService.findAllCommentairesBySpotId(spotid));
		
		return "fichespot";
	}
	
	@GetMapping("/spots")
	public String allSpots(Model model) {
		model.addAttribute("spots", spotService.findAllSpots());
		
		return "spots";
	}

}
