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
import org.springframework.web.servlet.ModelAndView;

import fr.axelallain.UserPrincipal;
import fr.axelallain.entity.Commentaire;
import fr.axelallain.entity.Longueur;
import fr.axelallain.entity.Spot;
import fr.axelallain.entity.Voie;
import fr.axelallain.service.CommentaireService;
import fr.axelallain.service.LongueurService;
import fr.axelallain.service.SpotService;
import fr.axelallain.service.VoieService;

@Controller
public class SpotController {
	
	@Autowired
	private SpotService spotService;
	
	@Autowired
	private CommentaireService commentaireService;
	
	@Autowired
	private VoieService voieService;
	
	@Autowired
	private LongueurService longueurService;
	
	public boolean isAuthenticated(){

	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();

	}
	
	@GetMapping("/dashboard/my-spots/add-spot")
	public String addSpotForm(Model model) {
		
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserstaff", user.getStaff());
		}
		
		model.addAttribute("spot", new Spot());
		
		UserPrincipal cuser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long cuserid = cuser.getId();
        
        model.addAttribute("cuserid", cuserid);
	    
		return "add-spot";
	}
	
	@PostMapping("/dashboard/my-spots/add-spot")
	public String addSpotSubmit(Spot spot, Model model) {
		spotService.addSpot(spot);
		
		return "redirect:/dashboard/my-spots";
	}
	
	@GetMapping("/dashboard/my-spots/delete/{id}")
	public String deleteSpot(@PathVariable Long id) {
		spotService.deleteSpot(id);
		
		return "redirect:/dashboard/my-spots";
	}
	
	@GetMapping("/dashboard/my-spots/edit/{id}")
	public ModelAndView editSpotForm(@PathVariable Long id, Model model) {
		
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserstaff", user.getStaff());
		}
		
		UserPrincipal cuser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long cuserid = cuser.getId();
        
        model.addAttribute("cuserid", cuserid);
		
	    Spot spot = spotService.findSpotById(id);

	    ModelAndView modelAndView = new ModelAndView();

	    modelAndView.setViewName("edit-spot");
	    modelAndView.addObject("spot", spot);

	    return modelAndView;
	}
	
	@GetMapping("/details-spot/{spotid}")
	public String detailsSpot(@PathVariable Long spotid, Model model) {
		
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserstaff", user.getStaff());
		}
		
		model.addAttribute("spotid", spotid);
		model.addAttribute("spot", spotService.findSpotById(spotid));
		model.addAttribute("commentaire", new Commentaire());	
		model.addAttribute("commentaires", commentaireService.findAllCommentairesBySpotId(spotid));
		
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cusername", user.getUsername());
			model.addAttribute("cuserid", user.getId());
		}
		
		return "details-spot";
	}
	
	// more-spot.html //
	
	@GetMapping("/dashboard/my-spots/more-spot/{id}")
	public String moreSpot(@PathVariable Long id, Model model, Authentication authentication) {
		
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserstaff", user.getStaff());
		}
		
		model.addAttribute("spot", spotService.findSpotById(id));
		model.addAttribute("voies", voieService.findBySpotId(id));
        
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserid", user.getId());
		}
		
		return "more-spot";
	}
	
	@GetMapping("/dashboard/my-spots/more-spot/{spotid}/delete/{voieid}")
	public String deleteVoieFromThisSpot(@PathVariable Long spotid, @PathVariable Long voieid) {
		voieService.deleteVoie(voieid);
		
		return "redirect:/dashboard/my-spots/more-spot/" + spotid;
	}
	
	@GetMapping("/dashboard/my-spots/more-spot/{spotid}/add-voie")
	public String addVoieForm(@PathVariable Long spotid, Voie voie, Model model) {
		
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserstaff", user.getStaff());
		}
		
		model.addAttribute("voie", voie);
		model.addAttribute("spot", spotService.findSpotById(spotid));
		
		return "add-voie";
	}
	
	@PostMapping("/dashboard/my-spots/more-spot/{spotid}/add-voie")
	public String addVoieSubmit(@PathVariable Long spotid, Voie voie) {
		voieService.addVoie(voie);
		
		return "redirect:/dashboard/my-spots/more-spot/" + spotid;
	}
	
	@GetMapping("/dashboard/my-spots/more-spot/{spotid}/edit-voie/{voieid}")
	public ModelAndView editVoieForm(@PathVariable Long spotid, @PathVariable Long voieid, Model model) {
		
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserstaff", user.getStaff());
		}
		
		Voie voie = voieService.findById(voieid);
		model.addAttribute("spot", spotService.findSpotById(spotid));

	    ModelAndView modelAndView = new ModelAndView();

	    modelAndView.setViewName("edit-voie");
	    modelAndView.addObject("voie", voie);

	    return modelAndView;
	}
	
	@PostMapping("/dashboard/my-spots/more-spot/{spotid}/edit-voie/{voieid}")
	public String editVoieSubmit(@PathVariable Long spotid, @PathVariable Long voieid, Voie voie) {
		voieService.editVoie(voie);

	    return "redirect:/dashboard/my-spots/more-spot/" + spotid;
	}
	
	@GetMapping("/dashboard/my-spots/more-spot/{spotid}/more-voie/{voieid}")
	public String moreVoie(@PathVariable Long spotid, @PathVariable Long voieid, Model model, Authentication authentication) {
		
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserstaff", user.getStaff());
		}
		
		model.addAttribute("voie", voieService.findById(voieid));
		model.addAttribute("longueurs", longueurService.findByVoieId(voieid));
		model.addAttribute("spot", spotService.findSpotById(spotid));
        
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserid", user.getId());
		}
		
		return "more-voie";
	}
	
	@GetMapping("/dashboard/my-spots/more-spot/{spotid}/more-voie/{voieid}/add-longueur")
	public String addLongueurForm(@PathVariable Long spotid, @PathVariable Long voieid, Longueur longueur, Model model) {
		
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserstaff", user.getStaff());
		}
		
		model.addAttribute("longueur", longueur);
		model.addAttribute("voie", voieService.findById(voieid));
		model.addAttribute("spot", spotService.findSpotById(spotid));
		
		return "add-longueur";
	}
	
	@PostMapping("/dashboard/my-spots/more-spot/{spotid}/more-voie/{voieid}/add-longueur")
	public String addLongueurSubmit(@PathVariable Long spotid, @PathVariable Long voieid, Longueur longueur) {
		longueurService.addLongueur(longueur);
		
		return "redirect:/dashboard/my-spots/more-spot/" + spotid + "/more-voie/" + voieid;
	}
	
	@GetMapping("/dashboard/my-spots/more-spot/{spotid}/more-voie/{voieid}/delete/{longueurid}")
	public String deleteLongueurFromThisVoie(@PathVariable Long spotid, @PathVariable Long voieid, @PathVariable Long longueurid) {
		longueurService.deleteLongueur(longueurid);
		
		return "redirect:/dashboard/my-spots/more-spot/" + spotid + "/more-voie/" + voieid;
	}
	
	@GetMapping("/dashboard/my-spots/more-spot/{spotid}/more-voie/{voieid}/edit-longueur/{longueurid}")
	public ModelAndView editLongueurForm(@PathVariable Long spotid, @PathVariable Long voieid, @PathVariable Long longueurid, Model model) {
		
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserstaff", user.getStaff());
		}
		
		Longueur longueur = longueurService.findById(longueurid);
		model.addAttribute("spot", spotService.findSpotById(spotid));
		model.addAttribute("voie", voieService.findById(voieid));

	    ModelAndView modelAndView = new ModelAndView();

	    modelAndView.setViewName("edit-longueur");
	    modelAndView.addObject("longueur", longueur);

	    return modelAndView;
	}
	
	@PostMapping("/dashboard/my-spots/more-spot/{spotid}/more-voie/{voieid}/edit-longueur/{longueurid}")
	public String editLongueurSubmit(@PathVariable Long spotid, @PathVariable Long voieid, @PathVariable Long longueurid, Longueur longueur) {
		longueurService.editLongueur(longueur);

		return "redirect:/dashboard/my-spots/more-spot/" + spotid + "/more-voie/" + voieid;
	}
	
	@GetMapping("/spots")
	public String allSpots(Model model) {
		
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserstaff", user.getStaff());
		}
		
		model.addAttribute("spots", spotService.findAllSpots());
		
		return "spots";
	}
	
	@PostMapping("/details-spot/{id}/officiel")
	public String spotOfficiel(@PathVariable Long id, Model model, Spot spot) {
		spotService.addSpot(spot);		
		
		return "redirect:/details-spot/" + id;
		
	}

}
