package gov.wilaya.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;
@Entity
public class Division implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idDivision;
	@NotBlank
	private String libelleDivision;
	@OneToMany(mappedBy="division")
	private Collection<Utilisateur> utilisateurs;
	public Division() {
		super();
	}
	public Division(String libelleDivision) {
		super();
		this.libelleDivision = libelleDivision;
	}
	public Long getIdDivision() {
		return idDivision;
	}
	public void setIdDivision(Long idDivision) {
		this.idDivision = idDivision;
	}
	public String getLibelleDivision() {
		return libelleDivision;
	}
	public void setLibelleDivision(String libelleDivision) {
		this.libelleDivision = libelleDivision;
	}
	public Collection<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}
	public void setUtilisateurs(Collection<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

}
