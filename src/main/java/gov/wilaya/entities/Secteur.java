package gov.wilaya.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Secteur implements Serializable{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idSecteur;
	@NotBlank
	private String libelleSecteur;
	
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

	public Secteur() {
		super();
	}
	
}
