package spring.formation.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.formation.model.Produit;

public class ProduitValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Produit.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Produit produit = (Produit) target;
		
		if(produit.getPrixAchat() != null && produit.getPrixVente() == null) {
			errors.rejectValue("prixVente", "produit.form.prixVente.required", "Le prix de vente doit être renseignée");
		} else if (produit.getPrixAchat() == null && produit.getPrixVente() != null) {
			errors.rejectValue("prixAchat", "produit.form.prixAchat.required", "Le prix d'achat doit être renseignée");
		} 
		
		if(produit.getPrixVente() != null && (produit.getPrixAchat() == null || produit.getPrixVente().compareTo(produit.getPrixAchat()) <= 0)) {
			errors.rejectValue("prixVente", "produit.form.prixVente.sup", "Le prix de vente doit être strictement supérieur au prix d'achat");
		}
	}

}
