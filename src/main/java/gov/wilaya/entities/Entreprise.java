package gov.wilaya.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Entreprise implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idEntreprise;
	@NotBlank
	private String nomEntreprise;
	@NotBlank
	private String adresseEntreprise;
	
	public Entreprise() {
		super();
	}
	public Entreprise(String nomEntreprise, String adresseEntreprise) {
		super();
		this.nomEntreprise = nomEntreprise;
		this.adresseEntreprise = adresseEntreprise;
	}
	public Long getIdEntreprise() {
		return idEntreprise;
	}
	public void setIdEntreprise(Long idEntreprise) {
		this.idEntreprise = idEntreprise;
	}
	public String getNomEntreprise() {
		return nomEntreprise;
	}
	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
	}
	public String getAdresseEntreprise() {
		return adresseEntreprise;
	}
	public void setAdresseEntreprise(String adresseEntreprise) {
		this.adresseEntreprise = adresseEntreprise;
	}
	
}
