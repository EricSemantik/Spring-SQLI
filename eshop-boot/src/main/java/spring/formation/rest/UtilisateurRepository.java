package spring.formation.rest;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.formation.model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{
	Optional<Utilisateur> findByIdentifiant(String identifiant);
}
