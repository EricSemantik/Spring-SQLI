package spring.formation.repo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import spring.formation.config.ApplicationConfig;
import spring.formation.model.Produit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class TestQueries {
	
	@Autowired
	private IProduitRepository produitRepo;
	
	@Test
	@Transactional(readOnly = false)
	
	public void updateStockByProduit() {
		// ARRANGE
		Produit produit = new Produit("IPhone 13", 1000.0, 1300.0);
		produit.setStock(5);

		produit = produitRepo.save(produit);
		
		// ACT
		produitRepo.updateStockByProduit(12, produit.getId());
		
		// ASSERT
		Produit produitFind = produitRepo.findById(produit.getId()).get();
		
		Assert.assertEquals(12, produitFind.getStock());
	}

}
