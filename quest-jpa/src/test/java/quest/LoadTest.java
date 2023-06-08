package quest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;

import quest.model.Civilite;
import quest.model.Etudiant;
import quest.model.Formateur;
import quest.model.Formation;

public class LoadTest {

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Test
	public void test() throws ParseException {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuestUnit");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Etudiant asma = new Etudiant(Civilite.MME, "RIAHI", "Asma", "asmar", "azerty", sdf.parse("05/02/1988"));

		asma = em.merge(asma);

		Etudiant benjamin = new Etudiant(Civilite.M, "RUHAUT", "Benjamin", "benjaminr", "azerty",
				sdf.parse("05/04/1998"));

		benjamin = em.merge(benjamin);

		Etudiant wafa = new Etudiant(Civilite.MME, "EL ORF", "Wafa", "wafae", "azerty", sdf.parse("05/02/1984"));

		wafa = em.merge(wafa);

		Formateur eric = new Formateur(Civilite.M, "SULTAN", "Eric", "erics", "azerty", 22, true);
		eric = em.merge(eric);

		Formation spring = new Formation("Spring Core", 4, sdf.parse("05/06/2023"));
		spring.setFormateur(eric);
		spring.getEtudiants().add(asma);
		spring.getEtudiants().add(benjamin);
		spring.getEtudiants().add(wafa);
		
		spring = em.merge(spring);

		tx.commit();
		em.close();
		emf.close();

	}

}
