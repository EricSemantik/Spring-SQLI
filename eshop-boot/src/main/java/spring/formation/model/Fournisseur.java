package spring.formation.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@DiscriminatorValue("fournisseur")
public class Fournisseur extends Personne {
	@Column(name = "FOU_RESPONSABLE", length = 200, nullable = false)
	@JsonView(Views.ViewBasic.class)
	private String responsable;

	@OneToMany(mappedBy = "fournisseur")
//	@JsonView(Views.ViewFournisseur.class)
	private List<Produit> produits = new ArrayList<>();

	@ManyToMany(mappedBy = "fournisseurs")
	private List<Adresse> adresses = new ArrayList<>();

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public List<Adresse> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}

	@Override
	public String toString() {
		return " > " + this.id + ". " + this.nom;
	}
}
