package spring.formation.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import spring.formation.model.Produit;

public interface IProduitRepository extends JpaRepository<Produit, Long> {
	List<Produit> findByPrixBetween(Double a, Double b); // Query par @NamedQuery (dans Produit.java)

	// Rechercher tous les produits qui ne sont plus en stock => par @NamedQuery
	
	// Mise jour du stock (param) pour un produit donné (par param id) => @Modifying
}
