package spring.formation.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "commentaire")
public class Commentaire {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COM_ID")
	@JsonView(Views.ViewBasic.class)
	private Long id;

	@Column(name = "COM_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@JsonView(Views.ViewBasic.class)
	private Date date = new Date();

	@Column(name = "COM_NOTE", nullable = false)
	@JsonView(Views.ViewBasic.class)
	private int note = 0;

	@Column(name = "COM_COMMENTAIRE", nullable = false)
	@JsonView(Views.ViewBasic.class)
	private String commentaire;

	@ManyToOne
	@JoinColumn(name = "COM_PRODUIT_ID")
	@JsonView(Views.ViewCommentaire.class)
	private Produit produit;

	@ManyToOne
	@JoinColumn(name = "COM_CLIENT_ID")
	@JsonView(Views.ViewCommentaire.class)
	private Client client;

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

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
