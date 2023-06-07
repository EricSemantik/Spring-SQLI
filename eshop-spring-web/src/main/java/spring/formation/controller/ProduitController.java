package spring.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import spring.formation.repo.IProduitRepository;

@Controller
public class ProduitController {
	
	@Autowired
	private IProduitRepository produitRepository;
	
//	@GetMapping("/produit/list")
//	public String list() {
//		
//	}

}
