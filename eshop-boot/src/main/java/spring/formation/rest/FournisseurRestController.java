package spring.formation.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import spring.formation.model.Fournisseur;
import spring.formation.model.Views;
import spring.formation.repo.IFournisseurRepository;

@RestController
@RequestMapping("/fournisseur")
@CrossOrigin("*")
public class FournisseurRestController {
	@Autowired
	private IFournisseurRepository fournisseurRepo;

	@GetMapping("")
	@JsonView(Views.ViewFournisseur.class)
	public List<Fournisseur> findAll() {
		List<Fournisseur> fournisseurs = fournisseurRepo.findAll();

		return fournisseurs;
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewFournisseur.class)
	public Fournisseur findById(@PathVariable Long id) {
		Optional<Fournisseur> optFournisseur = fournisseurRepo.findById(id);

		if (optFournisseur.isPresent()) {
			return optFournisseur.get();
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fournisseur non trouvé");
	}
	
	@PostMapping("")
	@JsonView(Views.ViewFournisseur.class)
	public Fournisseur create(@RequestBody Fournisseur fournisseur, BindingResult result) {
		fournisseur = fournisseurRepo.save(fournisseur);

		return fournisseur;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewFournisseur.class)
	public Fournisseur update(@PathVariable Long id, @RequestBody Fournisseur fournisseur) {
		if (!fournisseurRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fournisseur non trouvé");
		}

		fournisseur = fournisseurRepo.save(fournisseur);

		return fournisseur;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!fournisseurRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fournisseur non trouvé");
		}

		fournisseurRepo.deleteById(id);
	}
}
