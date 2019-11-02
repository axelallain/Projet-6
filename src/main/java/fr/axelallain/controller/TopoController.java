package fr.axelallain.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.axelallain.UserPrincipal;
import fr.axelallain.dto.TopoDto;
import fr.axelallain.entity.Spot;
import fr.axelallain.entity.Topo;
import fr.axelallain.service.SpotService;
import fr.axelallain.service.TopoService;
import fr.axelallain.service.UtilisateurService;

@Controller
public class TopoController {

	@Autowired
	private TopoService topoService;
	
	@Autowired
	private SpotService spotService;
	
	@Autowired
	private UtilisateurService utilisateurService;
	
	@GetMapping("/panel/toposutilisateur/addtopo")
	public String addTopoForm(Model model) {
		model.addAttribute("topodto", new TopoDto());
		
		UserPrincipal cuser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long cuserid = cuser.getId();       
        model.addAttribute("cuserid", cuserid);
        
        model.addAttribute("spots", spotService.findAllSpotsByUtilisateurId(cuserid));
        
		return "addtopo";
	}
	
	@PostMapping("/panel/toposutilisateur/addtopo")
	public String addTopoSubmit(@ModelAttribute TopoDto topodto, Topo topo, Model model) {
		
		topo.setUtilisateur(utilisateurService.findById(topodto.getUtilisateurId()));
		topo.setNom(topodto.getNom());
		topo.setDescription(topodto.getDescription());
		topo.setLieu(topodto.getLieu());
		
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
		model.addAttribute("spots", spotService.findAllSpotsByToposId(topoid));
		model.addAttribute("countspots", spotService.countAllSpotsByToposId(topoid));
		
		return "fichetopo";
	}
	
	@GetMapping("/topos")
	public String allTopos(Model model) {
		model.addAttribute("topos", topoService.findAllTopos());
		
		return "topos";
	}
	
	@GetMapping("/panel/stafftopos")
	public String staffTopos(Model model) {
		model.addAttribute("topos", topoService.findAllTopos());
		
		return "stafftopos";
	}
	
	@GetMapping("/panel/stafftopos/modifier/{id}")
	public ModelAndView modifierStaffTopoForm(@PathVariable Long id, Model model) {
		Topo topo = topoService.findTopoById(id);

	    ModelAndView modelAndView = new ModelAndView();

	    modelAndView.setViewName("modifierstafftopo");
	    modelAndView.addObject("topo", topo);
	    
	    return modelAndView;
	}
	
	@PostMapping("/panel/stafftopos/addtopo")
	public String addStaffTopoSubmit(Topo topo, Model model) {
		topoService.addTopo(topo);
		
		return "redirect:/panel/stafftopos/";
	}
	
}