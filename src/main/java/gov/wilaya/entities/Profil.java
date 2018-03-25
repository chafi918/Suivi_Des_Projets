package gov.wilaya.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.NotBlank;
@Entity
public class Profil implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idProfil;
	@NotBlank
	private String libelleProfil;
	@ManyToMany(mappedBy="profil")
	private Collection<Utilisateur> utilisateurs;
	
	public Profil(String libelleProfil) {
		super();
		this.libelleProfil = libelleProfil;
	}
	public Long getIdProfil() {
		return idProfil;
	}
	public void setIdProfil(Long idProfil) {
		this.idProfil = idProfil;
	}
	public String getLibelleProfil() {
		return libelleProfil;
	}
	public void setLibelleProfil(String libelleProfil) {
		this.libelleProfil = libelleProfil;
	}
	public Collection<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}
	public void setUtilisateurs(Collection<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

}
