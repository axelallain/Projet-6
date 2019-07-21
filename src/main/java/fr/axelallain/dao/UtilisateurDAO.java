package fr.axelallain.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import fr.axelallain.entity.Utilisateur;

@EnableJpaRepositories
public interface UtilisateurDAO extends JpaRepository<Utilisateur, Long>{

}
