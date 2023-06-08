package quest.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("formateur")
public class Formateur extends Personne {
	private int experience;
	private boolean referent;
	@OneToMany(mappedBy = "formateur")
	private List<Formation> formations = new ArrayList<>();

	public Formateur() {
		super();
	}

	public Formateur(Civilite civilite, String nom, String prenom, String login, String motDePasse, int experience,
			boolean referent) {
		super(civilite, nom, prenom, login, motDePasse);
		this.experience = experience;
		this.referent = referent;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public boolean isReferent() {
		return referent;
	}

	public void setReferent(boolean referent) {
		this.referent = referent;
	}

	public List<Formation> getFormations() {
		return formations;
	}

	public void setFormations(List<Formation> formations) {
		this.formations = formations;
	}

}
