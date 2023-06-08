package spring.formation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import spring.formation.model.Roles;
import spring.formation.model.Utilisateur;
import spring.formation.rest.UtilisateurRepository;

@SpringBootTest
class EshopBootApplicationTests {

	@Autowired
	private UtilisateurRepository utilisateurRepo;
	
	@Test
	void contextLoads() {
		Utilisateur asma = new Utilisateur("ASMA", "azerty", "RIAHI", "Asma", false);
		asma.getRoles().add(Roles.USER);
		
		utilisateurRepo.save(asma);
		
		Utilisateur benjamin = new Utilisateur("BENJAMIN", "azerty", "RUHAUT", "Benjamin", false);
		benjamin.getRoles().add(Roles.ADMIN);
		
		utilisateurRepo.save(benjamin);
		
		Utilisateur wafa = new Utilisateur("WAFA", "azerty", "EL ORF", "Wafa", false);
		wafa.getRoles().add(Roles.ADMIN);
		wafa.getRoles().add(Roles.USER);
		
		utilisateurRepo.save(wafa);
		
		Utilisateur eric = new Utilisateur("ERIC", "azerty", "SULTAN", "Eric", true);
		eric.getRoles().add(Roles.USER);
		
		utilisateurRepo.save(eric);
	}

}
