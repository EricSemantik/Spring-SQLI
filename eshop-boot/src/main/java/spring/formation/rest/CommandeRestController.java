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

import spring.formation.model.Commande;
import spring.formation.model.Views;
import spring.formation.repo.ICommandeRepository;

@RestController
@RequestMapping("/commande")
@CrossOrigin("*")
public class CommandeRestController {
	@Autowired
	private ICommandeRepository commandeRepo;

	@GetMapping("")
	@JsonView(Views.ViewCommande.class)
	public List<Commande> findAll() {
		List<Commande> commandes = commandeRepo.findAll();

		return commandes;
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewCommande.class)
	public Commande findById(@PathVariable Long id) {
		Optional<Commande> optCommande = commandeRepo.findById(id);

		if (optCommande.isPresent()) {
			return optCommande.get();
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Commande non trouvé");
	}
	
	@PostMapping("")
	@JsonView(Views.ViewCommande.class)
	public Commande create(@RequestBody Commande commande, BindingResult result) {
		commande = commandeRepo.save(commande);

		return commande;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewCommande.class)
	public Commande update(@PathVariable Long id, @RequestBody Commande commande) {
		if (!commandeRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Commande non trouvé");
		}

		commande = commandeRepo.save(commande);

		return commande;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!commandeRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Commande non trouvé");
		}

		commandeRepo.deleteById(id);
	}
}
