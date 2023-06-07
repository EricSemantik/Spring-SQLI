package spring.formation.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.formation.model.Reparateur;

public interface IReparateurRepository extends JpaRepository<Reparateur, Long> {
	List<Reparateur> findByNom(String nom); // query par convention de nommage
}
