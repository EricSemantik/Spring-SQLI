package spring.formation.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import spring.formation.controller.validator.ProduitValidator;
import spring.formation.model.Fournisseur;
import spring.formation.model.Produit;
import spring.formation.repo.IFournisseurRepository;
import spring.formation.repo.IProduitRepository;

@Controller
@RequestMapping("/produit")
public class ProduitController {

	@Autowired
	private IProduitRepository produitRepo;

	@Autowired
	private IFournisseurRepository fournisseurRepo;

	@GetMapping({ "", "/list" }) // ETAPE 1 : Réception de la requête par le Controller
	public String list(Model model) {
		// ETAPE 2 : Récupération des données
		List<Produit> produits = produitRepo.findAll();

		// ETAPE 3 : Renseigner le Model
		model.addAttribute("mesProduits", produits);

		return "produit/list"; // ETAPE 4 : Forward vers la View
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("produit", new Produit());
		model.addAttribute("fournisseurs", fournisseurRepo.findAll());

		return "produit/form";
	}

	@GetMapping("/edit")
	public String edit(@RequestParam Long id, Model model) {
		Optional<Produit> optProduit = produitRepo.findById(id);

		if (optProduit.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit non trouvé");
		}

		model.addAttribute("produit", optProduit.get());
		model.addAttribute("fournisseurs", fournisseurRepo.findAll());

		return "produit/form";
	}

	@PostMapping("/save")
	public String save(@RequestParam(required = false) Long id, @RequestParam String libelle,
			@RequestParam Double prixAchat, @RequestParam Double prixVente, @RequestParam String reference,
			@RequestParam String modele, @RequestParam int stock, @RequestParam(required = false) Long idFournisseur) {

		Produit produit = null;

		if (id == null) {
			produit = new Produit();
		} else {
			produit = produitRepo.findById(id).get();
		}

		produit.setLibelle(libelle);
		produit.setPrixAchat(prixAchat);
		produit.setPrixVente(prixVente);
		produit.setReference(reference);
		produit.setModele(modele);
		produit.setStock(stock);

		if (idFournisseur != null) {
			Fournisseur fournisseur = new Fournisseur();
			fournisseur.setId(idFournisseur);

			produit.setFournisseur(fournisseur);
		} else {
			produit.setFournisseur(null);
		}

		produitRepo.save(produit);

		return "redirect:list";
	}
	
	@PostMapping("/saveBis")
	public String saveBis(@ModelAttribute("produit") @Valid Produit produit, BindingResult result, @RequestParam(required = false) Long idFournisseur) {
		new ProduitValidator().validate(produit, result);
		
		if(result.hasErrors()) {
			return "produit/form";
		}
		
		if (idFournisseur != null) {
			Fournisseur fournisseur = new Fournisseur();
			fournisseur.setId(idFournisseur);

			produit.setFournisseur(fournisseur);
		} else {
			produit.setFournisseur(null);
		}

		produitRepo.save(produit);
		
		return "redirect:list";
	}

	@GetMapping("/cancel")
	public String cancel() {
		return "forward:/produit/list";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		if (!produitRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit non trouvé");
		}

		produitRepo.deleteById(id);

		return "forward:/produit/list";
	}
}
