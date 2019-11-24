package fr.axelallain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.axelallain.entity.Commentaire;
import fr.axelallain.service.CommentaireService;

@Controller
public class CommentaireController {
	
	@Autowired
	private CommentaireService commentaireService;
	
	@PostMapping("/spot/{spotid}/commentaire")
	public String commenterSpot(@PathVariable Long spotid, Commentaire commentaire) {
		commentaireService.ajouter(commentaire);
		
		return "redirect:/details-spot/" + spotid;		
	}

}
