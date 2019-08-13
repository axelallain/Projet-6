package fr.axelallain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.axelallain.entity.Topo;
import fr.axelallain.service.TopoService;

@Controller
public class TopoController {

	@Autowired
	private TopoService topoService;
	
	@GetMapping("/addtopo")
	public String addTopoForm(Model model) {
		model.addAttribute("topo", new Topo());
		return "addTopo";
	}
	
	@PostMapping("/addtopo")
	public String addTopoSubmit(Topo topo, Model model) {
		topoService.addTopo(topo);
		return "redirect:/panel";
	}
	
	@DeleteMapping("/panel/delete/{id}")
	public String deleteTopo(@PathVariable Long id) {
		topoService.deleteTopo(id);
		
		return "redirect:/panel";
	}
}