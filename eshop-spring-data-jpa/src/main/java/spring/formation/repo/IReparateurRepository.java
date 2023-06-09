package spring.formation.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.formation.model.Reparateur;

public interface IReparateurRepository extends JpaRepository<Reparateur, Long> {
	// Rechercher liste de réparateurs par nom par convention de nommage
	List<Reparateur> findByNom(String nom);
}
