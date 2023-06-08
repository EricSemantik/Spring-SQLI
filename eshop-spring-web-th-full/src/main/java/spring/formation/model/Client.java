package spring.formation.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@DiscriminatorValue("client")
public class Client extends Personne {
	@Column(name = "CLI_PRENOM", length = 100)
	@JsonView(Views.ViewBasic.class)
	private String prenom;

	@OneToMany(mappedBy = "client")
	private List<Commande> commandes = new ArrayList<>();

	@OneToMany(mappedBy = "client")
	private List<Adresse> adresses;

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public List<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

	public List<Adresse> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}
}
