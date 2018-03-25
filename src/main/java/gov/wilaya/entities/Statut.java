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
public class Statut implements Serializable {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idStatut;
	@NotBlank
	private String libelleStatut;
	@OneToMany(mappedBy="statut")
	private Collection<Projet> projets;

	public Statut(String libelleStatut) {
		super();
		this.libelleStatut = libelleStatut;
	}
	public Long getIdStatut() {
		return idStatut;
	}
	public void setIdStatut(Long idStatut) {
		this.idStatut = idStatut;
	}
	public String getLibelleStatut() {
		return libelleStatut;
	}
	public void setLibelleStatut(String libelleStatut) {
		this.libelleStatut = libelleStatut;
	}
	public Collection<Projet> getProjets() {
		return projets;
	}
	public void setProjets(Collection<Projet> projets) {
		this.projets = projets;
	}
	public Statut() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
