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

import spring.formation.model.Commentaire;
import spring.formation.model.Views;
import spring.formation.repo.ICommentaireRepository;

@RestController
@RequestMapping("/commentaire")
@CrossOrigin("*")
public class CommentaireRestController {
	@Autowired
	private ICommentaireRepository commentaireRepo;

	@GetMapping("")
	@JsonView(Views.ViewCommentaire.class)
	public List<Commentaire> findAll() {
		List<Commentaire> commentaires = commentaireRepo.findAll();

		return commentaires;
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewCommentaire.class)
	public Commentaire findById(@PathVariable Long id) {
		Optional<Commentaire> optCommentaire = commentaireRepo.findById(id);

		if (optCommentaire.isPresent()) {
			return optCommentaire.get();
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Commentaire non trouvé");
	}
	
	@PostMapping("")
	@JsonView(Views.ViewCommentaire.class)
	public Commentaire create(@RequestBody Commentaire commentaire, BindingResult result) {
		commentaire = commentaireRepo.save(commentaire);

		return commentaire;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewCommentaire.class)
	public Commentaire update(@PathVariable Long id, @RequestBody Commentaire commentaire) {
		if (!commentaireRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Commentaire non trouvé");
		}

		commentaire = commentaireRepo.save(commentaire);

		return commentaire;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!commentaireRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Commentaire non trouvé");
		}

		commentaireRepo.deleteById(id);
	}
}
