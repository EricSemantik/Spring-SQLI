package spring.formation.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring.formation.model.Commande;

public interface ICommandeRepository extends JpaRepository<Commande, Long> {
	@Query("select c from Commande c where c.client.id = :id")
	List<Commande> findAllByClientId(@Param("id") Long idClient); // Query par annotation
}
