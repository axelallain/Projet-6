package fr.axelallain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.axelallain.UserPrincipal;
import fr.axelallain.entity.Commentaire;
import fr.axelallain.service.CommentaireService;

@Controller
public class CommentaireController {
	
	@Autowired
	private CommentaireService commentaireService;
	
	@PostMapping("/spot/{spotid}/commentaire")
	public String commenterSpot(@PathVariable Long spotid, Commentaire commentaire) {
		commentaireService.ajouter(commentaire);
		
		return "redirect:/fichespot/" + spotid;		
	}
	
	@GetMapping("/panel/staffcommentaires")
	public String staffCommentaires(Model model) {
		model.addAttribute("commentaires", commentaireService.findAllCommentaires());
		
		return "staffcommentaires";
	}
	
	@DeleteMapping("/panel/staffcommentaires/delete/{id}")
	public String deleteCommentaire(@PathVariable Long id) {
		commentaireService.deleteCommentaire(id);
		
		return "redirect:/panel/staffcommentaires";
	}
	
	@DeleteMapping("/fichespot/delete/{id}")
	public String deleteCommentaireSpot(@PathVariable Long id, Long spotid) {
		commentaireService.deleteCommentaire(id);
		
		return "redirect:/fichespot/" + spotid;
	}
	
	@GetMapping("/panel/staffcommentaires/modifier/{id}")
	public String modifierCommentaireForm(@PathVariable Long id, Model model) {
		model.addAttribute("commentaire", commentaireService.findById(id));
		
		UserPrincipal cuser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long cuserid = cuser.getId();       
        model.addAttribute("cuserid", cuserid);
		
		return "modifierstaffcommentaire";
	}
	
	@PostMapping("/panel/staffcommentaire/addcommentaire")
	public String commenterStaffPanel(Commentaire commentaire) {
		commentaireService.ajouter(commentaire);
		
		return "redirect:/panel/staffcommentaires/";
	}

}
