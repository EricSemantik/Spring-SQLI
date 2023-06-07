package spring.formation.controller;


import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	@GetMapping("")
	public String defaut() {
		return "redirect:accueil";
	}
	
	@RequestMapping(path = "/accueil", method = RequestMethod.GET)
	public String home(Model model, @RequestParam(name = "username", required = false) String nomUtilisateur) {	
		
		model.addAttribute("dtJour", new Date());
		model.addAttribute("nomUtilisateur", nomUtilisateur);
		
		return "home";
	}
}
