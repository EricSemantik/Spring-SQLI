package spring.formation.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring.formation.model.Fournisseur;

public interface IFournisseurRepository extends JpaRepository<Fournisseur, Long> {
	@Query("select distinct f from Fournisseur f left join fetch f.produits")
	List<Fournisseur> findAllWithProduits();
	
	@Query("select distinct f from Fournisseur f left join fetch f.produits where f.id = :id")
	Optional<Fournisseur> findBydIdWithProduits(@Param("id") Long id);
}
