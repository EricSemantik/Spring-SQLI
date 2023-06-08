package quest.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@DiscriminatorValue("etudiant")
public class Etudiant extends Personne {
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	@ManyToMany(mappedBy = "etudiants")
	private List<Formation> formations = new ArrayList<>();

	public Etudiant() {
		super();
	}

	public Etudiant(Civilite civilite, String nom, String prenom, String login, String motDePasse, Date dateNaissance) {
		super(civilite, nom, prenom, login, motDePasse);
		this.dateNaissance = dateNaissance;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public List<Formation> getFormations() {
		return formations;
	}

	public void setFormations(List<Formation> formations) {
		this.formations = formations;
	}

}
