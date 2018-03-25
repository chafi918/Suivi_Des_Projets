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
public class Secteur implements Serializable{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idSecteur;
	@NotBlank
	private String libelleSecteur;
	@OneToMany(mappedBy="secteur")
	private Collection<Projet> projets;
	
	public Secteur(String libelleSecteur) {
		super();
		this.libelleSecteur = libelleSecteur;
	}
	public Long getIdSecteur() {
		return idSecteur;
	}
	public void setIdSecteur(Long idSecteur) {
		this.idSecteur = idSecteur;
	}
	public String getLibelleSecteur() {
		return libelleSecteur;
	}
	public void setLibelleSecteur(String libelleSecteur) {
		this.libelleSecteur = libelleSecteur;
	}
	public Collection<Projet> getProjets() {
		return projets;
	}
	public void setProjets(Collection<Projet> projets) {
		this.projets = projets;
	}
	public Secteur() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
