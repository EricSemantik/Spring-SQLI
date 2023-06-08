package spring.formation.rest.old;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.formation.model.Produit;
import spring.formation.repo.IProduitRepository;

@Controller
@RequestMapping("/old/produit")
@CrossOrigin("*")
public class ProduitOldRestController {

	@Autowired
	private IProduitRepository produitRepo;

	@GetMapping("")
	public ResponseEntity<List<Produit>> findAll() {
		List<Produit> produits = produitRepo.findAll();

		return new ResponseEntity<List<Produit>>(produits, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produit> findById(@PathVariable Long id) {
		Optional<Produit> optProduit = produitRepo.findById(id);

		if (optProduit.isPresent()) {
			return new ResponseEntity<Produit>(optProduit.get(), HttpStatus.OK);
		}

		return new ResponseEntity<Produit>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("")
	public ResponseEntity<Produit> create(@RequestBody Produit produit) {
		produit = produitRepo.save(produit);

		return new ResponseEntity<Produit>(produit, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Produit> update(@PathVariable Long id, @RequestBody Produit produit) {
		if (!produitRepo.existsById(id)) {
			return new ResponseEntity<Produit>(HttpStatus.NOT_FOUND);
		}

		produit = produitRepo.save(produit);

		return new ResponseEntity<Produit>(produit, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if (!produitRepo.existsById(id)) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
		produitRepo.deleteById(id);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
