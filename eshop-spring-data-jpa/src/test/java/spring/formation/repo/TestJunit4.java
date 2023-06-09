package spring.formation.repo;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.formation.config.ApplicationConfig;
import spring.formation.model.Produit;
import spring.formation.repo.IProduitRepository;

public class TestJunit4 {

	private static AnnotationConfigApplicationContext context;

	@BeforeClass
	public static void beforeAllTest() {
		context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
	}

	@AfterClass
	public static void afterAllTest() {
		context.close();
	}

	@Test
	public void produit() {
		IProduitRepository produitRepo = context.getBean(IProduitRepository.class);

		int nbStartProduit = produitRepo.findAll().size();

		Produit produit = new Produit("IPhone 13", 1000.0, 1300.0);

		produit = produitRepo.save(produit);

		Produit produitBis = new Produit("Nintendo Switch", 150.0, 250.0);
		produitBis = produitRepo.save(produitBis);

		produit = produitRepo.findById(produit.getId()).get();

		Assert.assertEquals("IPhone 13", produit.getLibelle());
		Assert.assertEquals(Double.valueOf(1000), produit.getPrixAchat());
		Assert.assertEquals(Double.valueOf(1300), produit.getPrixVente());

		produit.setLibelle("IPhone 13 Pro");
		produit.setPrixAchat(1250.0);
		produit.setPrixVente(1550.0);

		produit = produitRepo.save(produit);

		produit = produitRepo.findById(produit.getId()).get();

		Assert.assertEquals("IPhone 13 Pro", produit.getLibelle());
		Assert.assertEquals(Double.valueOf(1250), produit.getPrixAchat());
		Assert.assertEquals(Double.valueOf(1550), produit.getPrixVente());

		int nbEndProduit = produitRepo.findAll().size();

		Assert.assertEquals(2, nbEndProduit - nbStartProduit);

		produitRepo.deleteById(produit.getId());

		boolean find = produitRepo.findById(produit.getId()).isPresent();

		if (find) {
			Assert.fail("Erreur Suppression Produit");
		}
	}

}
