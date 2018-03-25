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
public class Entreprise implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idEntreprise;
	@NotBlank
	private String nomEntreprise;
	@NotBlank
	private String adresseEntreprise;
	@OneToMany(mappedBy="entreprise")
	private Collection<ContactEntreprise> contacts;
	@OneToMany(mappedBy="entreprise")
	private Collection<Marche> marches;
	
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
	public Collection<ContactEntreprise> getContacts() {
		return contacts;
	}
	public void setContacts(Collection<ContactEntreprise> contacts) {
		this.contacts = contacts;
	}
	public Collection<Marche> getMarches() {
		return marches;
	}
	public void setMarches(Collection<Marche> marches) {
		this.marches = marches;
	}
	
}
