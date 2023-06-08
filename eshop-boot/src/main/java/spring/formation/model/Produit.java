package spring.formation.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "produit")
@NamedQuery(name = "Produit.findByPrixBetween", query = "select p from Produit p where p.prixVente between ?1 and ?2")
@NamedQuery(name = "Produit.findWithoutStock", query = "select p from Produit p where p.stock = 0")
public class Produit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRO_ID")
	@JsonView(Views.ViewBasic.class)
	private Long id;
	@Column(name = "PRO_NOM", length = 150)
	@NotEmpty(message = "Le libellé est obligatoire")
	@JsonView(Views.ViewBasic.class)
	private String libelle;

	@Column(name = "PRO_PRIX_ACHAT", precision = 10, scale = 2)
	@JsonView(Views.ViewBasic.class)
	private Double prixAchat;

	@Column(name = "PRO_PRIX_VENTE", precision = 10, scale = 2)
	@JsonView(Views.ViewBasic.class)
	private Double prixVente;

	@Column(name = "PRO_REFERENCE", length = 100)
	@JsonView(Views.ViewBasic.class)
	private String reference;

	@Column(name = "PRO_MODELE", length = 100)
	@JsonView(Views.ViewBasic.class)
	private String modele;

	@Column(name = "PRO_STOCK")
	@JsonView(Views.ViewBasic.class)
	private int stock;

	@ManyToOne
	@JoinColumn(name = "PRO_FOURNISSEUR_ID")
	@JsonView(Views.ViewProduitWithFournisseur.class)
	private Fournisseur fournisseur;

	@OneToMany(mappedBy = "produit")
	private List<CommandeDetail> details = new ArrayList<>();

	@OneToMany(mappedBy = "produit")
	@JsonView(Views.ViewProduitWithCommentaires.class)
	private List<Commentaire> commentaires = new ArrayList<>();

	@ManyToMany(mappedBy = "produitsReparables")
	private List<Reparateur> reparateurs = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Double getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(Double prixAchat) {
		this.prixAchat = prixAchat;
	}

	public Double getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(Double prixVente) {
		this.prixVente = prixVente;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	public List<CommandeDetail> getDetails() {
		return details;
	}

	public void setDetails(List<CommandeDetail> details) {
		this.details = details;
	}

	public List<Commentaire> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}

	public List<Reparateur> getReparateurs() {
		return reparateurs;
	}

	public void setReparateurs(List<Reparateur> reparateurs) {
		this.reparateurs = reparateurs;
	}

	public Produit() { // IMPORTANT pour JPA

	}

	public Produit(String libelle) {
		this.libelle = libelle;
	}

	public Produit(String libelle, Double prixAchat, Double prixVente) {
		super();
		this.libelle = libelle;
		this.prixAchat = prixAchat;
		this.prixVente = prixVente;
	}

	public Produit(Long id, String libelle, Double prixAchat, Double prixVente, String reference, String modele,
			int stock) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.prixAchat = prixAchat;
		this.prixVente = prixVente;
		this.reference = reference;
		this.modele = modele;
		this.stock = stock;
	}

	@Override
	public String toString() {
		return " > " + this.id + ". " + this.libelle + ", " + this.prixVente + " euros";
	}
}
