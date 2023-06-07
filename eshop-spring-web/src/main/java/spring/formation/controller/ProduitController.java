package spring.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import spring.formation.repo.IProduitRepository;

@Controller
public class ProduitController {
	
	@Autowired
	private IProduitRepository produitRepository;
	
	@GetMapping({"/produit", "/produit/{id}"})
	public String test(@PathVariable(required=false) Integer id, Model model) {
		model.addAttribute("result", id!=null?id*2:0);
		
		return "produit";
	}

}
