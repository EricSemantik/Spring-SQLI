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

import spring.formation.model.Client;
import spring.formation.model.Views;
import spring.formation.repo.IClientRepository;

@RestController
@RequestMapping("/client")
@CrossOrigin("*")
public class ClientRestController {
	@Autowired
	private IClientRepository clientRepo;

	@GetMapping("")
	@JsonView(Views.ViewClient.class)
	public List<Client> findAll() {
		List<Client> clients = clientRepo.findAll();

		return clients;
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewClient.class)
	public Client findById(@PathVariable Long id) {
		Optional<Client> optClient = clientRepo.findById(id);

		if (optClient.isPresent()) {
			return optClient.get();
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client non trouvé");
	}
	
	@GetMapping("/{id}/detail")
	@JsonView(Views.ViewClientDetail.class)
	public Client detailById(@PathVariable Long id) {
		Optional<Client> optClient = clientRepo.findByIdWithCommandes(id);

		if (optClient.isPresent()) {
			return optClient.get();
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client non trouvé");
	}
	
	@PostMapping("")
	@JsonView(Views.ViewClient.class)
	public Client create(@RequestBody Client client, BindingResult result) {
		client = clientRepo.save(client);

		return client;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewClient.class)
	public Client update(@PathVariable Long id, @RequestBody Client client) {
		if (!clientRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client non trouvé");
		}

		client = clientRepo.save(client);

		return client;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!clientRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client non trouvé");
		}

		clientRepo.deleteById(id);
	}
}
