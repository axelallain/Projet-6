package fr.axelallain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.axelallain.service.SpotService;
import fr.axelallain.service.TopoService;

@Controller
public class RechercheController {
	
	@Autowired
	private SpotService spotService;
	
	@Autowired
	private TopoService topoService;
	
	@GetMapping("/rechercheavancee")
	public String rechercheAvancee() {
		
		return "rechercheavancee";
	}
	
	@PostMapping("/rechercheavancee")
	public String rechercheAvanceeSubmit() {
		
		return "resultatrecherche";
	}
	
	// RECHERCHE PAR CRITERE //
	
	@GetMapping("/recherche")
	public String searchSpot(@RequestParam String object, @RequestParam(defaultValue="") String nom, @RequestParam(defaultValue="") String lieu, @RequestParam(required = false) boolean officiel, Model model) {
		
		// SPOT //
		
		if(object.equals("spot") && nom.isEmpty() && lieu.isEmpty() && officiel == false) {
			model.addAttribute("spots", spotService.findAllSpots());
		}
		
		if(object.equals("spot") && nom.isEmpty() && lieu.isEmpty() && officiel == true) {
			model.addAttribute("spots", spotService.findByOfficiel());
		}
		
		if(object.equals("spot") && !nom.isEmpty() && lieu.isEmpty() && officiel == false) {
			model.addAttribute("spots", spotService.findByNomLike("%"+nom+"%"));
		}
		
		if(object.equals("spot") && nom.isEmpty() && !lieu.isEmpty() && officiel == false) {
			model.addAttribute("spots", spotService.findByLieuLike("%"+lieu+"%"));
		}
		
		if(object.equals("spot") && !nom.isEmpty() && lieu.isEmpty() && officiel == true) {
			model.addAttribute("spots", spotService.findByOfficielAndNomLike("%"+nom+"%"));
		}
		
		if(object.equals("spot") && nom.isEmpty() && !lieu.isEmpty() && officiel == true) {
			model.addAttribute("spots", spotService.findByOfficielAndLieuLike("%"+lieu+"%"));
		}
		
		if(object.equals("spot") && !nom.isEmpty() && !lieu.isEmpty() && officiel == true) {
			model.addAttribute("spots", spotService.findByOfficielAndNomLikeAndLieuLike("%"+nom+"%", "%"+lieu+"%"));
		}
		
		if(object.equals("spot") && !nom.isEmpty() && !lieu.isEmpty() && officiel == false) {
			model.addAttribute("spots", spotService.findByNomLikeAndLieuLike("%"+nom+"%", "%"+lieu+"%"));
		}
		
		// TOPO //
		
		if(object.equals("topo") && nom.isEmpty() && lieu.isEmpty()) {
			model.addAttribute("spots", topoService.findAllTopos());
		}
		
		if(object.equals("topo") && !nom.isEmpty() && lieu.isEmpty()) {
			model.addAttribute("spots", topoService.findByNomLike("%"+nom+"%"));
		}
		
		if(object.equals("topo") && nom.isEmpty() && !lieu.isEmpty()) {
			model.addAttribute("spots", topoService.findByLieuLike("%"+lieu+"%"));
		}
		
		if(object.equals("topo") && !nom.isEmpty() && !lieu.isEmpty()) {
			model.addAttribute("spots", topoService.findByNomLikeAndLieuLike("%"+nom+"%", "%"+lieu+"%"));
		}
		
		return "resultatrecherche";
	}

}