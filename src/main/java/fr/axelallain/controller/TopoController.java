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
import fr.axelallain.entity.Reservation;
import fr.axelallain.entity.Topo;
import fr.axelallain.service.SpotService;
import fr.axelallain.service.TopoService;

@Controller
public class TopoController {

	@Autowired
	private TopoService topoService;
	
	@Autowired
	private SpotService spotService;
	
	public boolean isAuthenticated(){

	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();

	}
	
	@GetMapping("/dashboard/my-topos/add-topo")
	public String addTopoForm(Model model) {
		
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserstaff", user.getStaff());
		}
		
		model.addAttribute("topo", new Topo());
		
		UserPrincipal cuser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long cuserid = cuser.getId();       
        model.addAttribute("cuserid", cuserid);
        
        model.addAttribute("spots", spotService.findAllSpotsByUtilisateurId(cuserid));
        
		return "add-topo";
	}
	
	@PostMapping("/dashboard/my-topos/add-topo")
	public String addTopoSubmit(Topo topo, Model model) {
		
		topoService.addTopo(topo);
		
		return "redirect:/dashboard/my-topos";
	}
	
	@GetMapping("/dashboard/my-topos/delete/{id}")
	public String deleteTopo(@PathVariable Long id) {
		topoService.deleteTopo(id);
		
		return "redirect:/dashboard/my-topos";
	}
	
	@GetMapping("/dashboard/my-topos/edit/{id}")
	public ModelAndView editTopoForm(@PathVariable Long id, Model model) {
		
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserstaff", user.getStaff());
		}
		
		UserPrincipal cuser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long cuserid = cuser.getId();
        
        model.addAttribute("cuserid", cuserid);
		
	    Topo topo = topoService.findTopoById(id);

	    ModelAndView modelAndView = new ModelAndView();

	    modelAndView.setViewName("edit-topo");
	    modelAndView.addObject("topo", topo);

	    return modelAndView;
	}
	
	@PostMapping("/dashboard/my-topos/edit/{id}")
	public String editTopoSubmit(@PathVariable Long id, Topo topo, Model model) {
		
		topoService.editTopo(topo);

	    return "redirect:/dashboard/my-topos";
	}
	
	@GetMapping("/details-topo/{topoid}")
	public String detailsTopo(@PathVariable Long topoid, Model model, Authentication authentication) {
		
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserstaff", user.getStaff());
		}
		
		model.addAttribute("topo", topoService.findTopoById(topoid));
		model.addAttribute("spots", spotService.findAllSpotsByToposId(topoid));
		model.addAttribute("reservation", new Reservation());
        
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserid", user.getId());
		}
		
		return "details-topo";
	}
	
	// more-topo.html //
	
	@GetMapping("/dashboard/my-topos/more-topo/{id}")
	public String moreTopo(@PathVariable Long id, Model model, Authentication authentication) {
		
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserstaff", user.getStaff());
		}
		
		model.addAttribute("topo", topoService.findTopoById(id));
		model.addAttribute("spots", spotService.findAllSpotsByToposId(id));
        
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserid", user.getId());
		}
		
		return "more-topo";
	}
	
	@GetMapping("/dashboard/my-topos/more-topo/{topoid}/delete/{spotid}")
	public String deleteSpotFromThisTopo(@PathVariable Long topoid, @PathVariable Long spotid) {
		Topo topo = topoService.findTopoById(topoid);
		topo.getSpots().remove(spotService.findSpotById(spotid));
		topoService.editTopo(topo);
		
		return "redirect:/dashboard/my-topos/more-topo/" + topoid;
	}
	
	//
	
	@GetMapping("/topos")
	public String allTopos(Model model) {
		
		if(isAuthenticated()) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("cuserstaff", user.getStaff());
		}
		
		model.addAttribute("topos", topoService.findAllTopos());
		
		return "topos";
	}
	
}