package fr.axelallain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.axelallain.entity.Utilisateur;
import fr.axelallain.service.UtilisateurService;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UtilisateurService utilisateurService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Utilisateur utilisateur = utilisateurService.findByUsername(username);
		
		if(utilisateur == null) {
			throw new UsernameNotFoundException("Cet utilisateur n'existe pas");
		}
		
		return new UserPrincipal(utilisateur);
	}

}
