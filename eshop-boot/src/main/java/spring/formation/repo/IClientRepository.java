package spring.formation.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring.formation.model.Client;

public interface IClientRepository extends JpaRepository<Client, Long> {
	// recherche tous les clients par ville => par @Query
	@Query("select c from Client c join c.adresses adr where adr.ville = :ville")
	List<Client> findAllByVille(@Param("ville") String ville);
	
	@Query("select distinct c from Client c left join fetch c.commandes where c.id = ?1")
	Optional<Client> findByIdWithCommandes(Long id);
}
