package spring.formation.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.formation.model.Fournisseur;
import spring.formation.model.Produit;
import spring.formation.repo.IFournisseurRepository;
import spring.formation.repo.IProduitRepository;

@Controller
@RequestMapping("/produit")
public class ProduitController {
	
	@Autowired
	private IFournisseurRepository fournisseurRepository;
	
	@Autowired
	private IProduitRepository produitRepository;
	
	@GetMapping({"", "/list"}) // ETAPE 1 : Réception de la Request
	public String list(Model model) {
		// ETAPE 2 : Récupération des données
		List<Produit> produits = produitRepository.findAll();
		
		// ETAPE 3 : Renseigner le Model
		model.addAttribute("mesProduits", produits);
		
		return "produit/list"; // ETAPE 4 : Appel de la View
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("fournisseurs", fournisseurRepository.findAll());
		
		return "produit/form";
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam Long id, Model model) {
		Optional<Produit> optProduit = produitRepository.findById(id)	;
		
		model.addAttribute("produit", optProduit.get());
		model.addAttribute("fournisseurs", fournisseurRepository.findAll());
		
		return "produit/form";
	}
	
	@PostMapping("/save")
	public String save(@RequestParam(required = false) Long id, @RequestParam String libelle, @RequestParam Double prixAchat,
			@RequestParam Double prixVente, @RequestParam String reference, @RequestParam String modele,
			@RequestParam int stock, @RequestParam(required = false) Long idFournisseur) {
		
		Produit produit = null;

		if (id == null) {
			produit = new Produit();
		} else {
			produit = produitRepository.findById(id).get();
		}

		produit.setLibelle(libelle);
		produit.setPrixAchat(prixAchat);
		produit.setPrixVente(prixVente);
		produit.setReference(reference);
		produit.setModele(modele);
		produit.setStock(stock);
		
		if(idFournisseur != null) {
			Fournisseur fournisseur = new Fournisseur();
			fournisseur.setId(idFournisseur);
			
			produit.setFournisseur(fournisseur);
		} else {
			produit.setFournisseur(null);
		}
		
		produitRepository.save(produit);

		return "redirect:list";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		produitRepository.deleteById(id);
		
		return "redirect:../list";
	}
	
	@GetMapping("/cancel")
	public String cancel(Model model) {
		return "forward:";
	}

}
