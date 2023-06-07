package spring.formation.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "commande")
public class Commande {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CMD_ID")
	private Long id;

	@Column(name = "CMD_DATE")
	@Temporal(TemporalType.DATE)
	private Date date;

	private Double prixTotal;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "CMD_ETAT", nullable = false)
	private EtatCommande etat;

	@ManyToOne
	@JoinColumn(name = "CMD_CLIENT_ID")
	private Client client;

	@OneToMany(mappedBy = "commande")
	private List<CommandeDetail> details = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}

	public EtatCommande getEtat() {
		return etat;
	}

	public void setEtat(EtatCommande etat) {
		this.etat = etat;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<CommandeDetail> getDetails() {
		return details;
	}

	public void setDetails(List<CommandeDetail> details) {
		this.details = details;
	}
}
