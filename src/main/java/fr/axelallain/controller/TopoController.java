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
import fr.axelallain.entity.Topo;
import fr.axelallain.service.SpotService;
import fr.axelallain.service.TopoService;

@Controller
public class TopoController {

	@Autowired
	private TopoService topoService;
	
	@Autowired
	private SpotService spotService;
	
	@GetMapping("/panel/toposutilisateur/addtopo")
	public String addTopoForm(Model model) {
		model.addAttribute("topo", new Topo());
		
		UserPrincipal cuser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long cuserid = cuser.getId();
        
        model.addAttribute("cuserid", cuserid);
	    
		return "addTopo";
	}
	
	@PostMapping("/panel/toposutilisateur/addtopo")
	public String addTopoSubmit(Topo topo, Model model) {
		topoService.addTopo(topo);
		
		UserPrincipal cuser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		return "redirect:/panel/toposutilisateur/" + cuser.getId();
	}
	
	@DeleteMapping("/panel/toposutilisateur/delete/{id}")
	public String deleteTopo(@PathVariable Long id) {
		topoService.deleteTopo(id);
		
		UserPrincipal cuser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		return "redirect:/panel/toposutilisateur/" + cuser.getId();
	}
	
	@GetMapping("/panel/toposutilisateur/modifier/{id}")
	public ModelAndView modifierTopoForm(@PathVariable Long id, Model model) {
		
		UserPrincipal cuser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long cuserid = cuser.getId();
        
        model.addAttribute("cuserid", cuserid);
		
	    Topo topo = topoService.findTopoById(id);

	    ModelAndView modelAndView = new ModelAndView();

	    modelAndView.setViewName("modifiertopo");
	    modelAndView.addObject("topo", topo);

	    return modelAndView;
	}
	
	@GetMapping("/fichetopo/{topoid}")
	public String ficheTopo(@PathVariable Long topoid, Model model) {
		model.addAttribute("topos", topoService.findAllTopos());
		model.addAttribute("topoid", topoid);
		model.addAttribute("topo", topoService.findTopoById(topoid));
		model.addAttribute("spots", spotService.findAllSpotsByTopoId(topoid));
		model.addAttribute("countspots", spotService.countAllSpotsByTopoId(topoid));
		
		return "fichetopo";
	}
	
}