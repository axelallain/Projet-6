package fr.axelallain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fr.axelallain.UserPrincipal;
import fr.axelallain.entity.Commentaire;
import fr.axelallain.service.CommentaireService;
import fr.axelallain.service.SpotService;

@Controller
public class SpotController {
	
	@Autowired
	private SpotService spotService;
	
	@Autowired
	private CommentaireService commentaireService;
	
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

}
