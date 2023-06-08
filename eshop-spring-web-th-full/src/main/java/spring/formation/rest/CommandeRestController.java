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

import spring.formation.model.Adresse;
import spring.formation.model.Views;
import spring.formation.repo.IAdresseRepository;

@RestController
@RequestMapping("/adresse")
@CrossOrigin("*")
public class CommandeRestController {
	@Autowired
	private IAdresseRepository adresseRepo;

	@GetMapping("")
	@JsonView(Views.ViewAdresse.class)
	public List<Adresse> findAll() {
		List<Adresse> adresses = adresseRepo.findAll();

		return adresses;
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewAdresse.class)
	public Adresse findById(@PathVariable Long id) {
		Optional<Adresse> optAdresse = adresseRepo.findById(id);

		if (optAdresse.isPresent()) {
			return optAdresse.get();
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Adresse non trouvé");
	}
	
	@PostMapping("")
	@JsonView(Views.ViewAdresse.class)
	public Adresse create(@RequestBody Adresse adresse, BindingResult result) {
		adresse = adresseRepo.save(adresse);

		return adresse;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewAdresse.class)
	public Adresse update(@PathVariable Long id, @RequestBody Adresse adresse) {
		if (!adresseRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Adresse non trouvé");
		}

		adresse = adresseRepo.save(adresse);

		return adresse;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!adresseRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Adresse non trouvé");
		}

		adresseRepo.deleteById(id);
	}
}
