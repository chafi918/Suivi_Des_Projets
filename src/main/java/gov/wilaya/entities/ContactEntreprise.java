package gov.wilaya.entities;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
@Entity
public class ContactEntreprise implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idContact;
	@NotBlank
	private String nomContact;
	@NotBlank
	private String responsabilite;
	private long telephone;
	@Email
	@NotBlank
	private String mailContact;
	@NotBlank
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_ENTREPRISE")
	private Entreprise entreprise;
	
	public ContactEntreprise() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ContactEntreprise(String nomContact, String responsabilite, String mailContact, long telephone) {
		super();
		this.nomContact = nomContact;
		this.responsabilite = responsabilite;
		this.mailContact = mailContact;
		this.telephone = telephone;
	}
	
	public Entreprise getEntreprise() {
		return entreprise;
	}
	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}
	public long getTelephone() {
		return telephone;
	}
	public void setTelephone(long telephone) {
		this.telephone = telephone;
	}
	public Long getIdContact() {
		return idContact;
	}
	public void setIdContact(Long idContact) {
		this.idContact = idContact;
	}
	public String getNomContact() {
		return nomContact;
	}
	public void setNomContact(String nomContact) {
		this.nomContact = nomContact;
	}
	public String getResponsabilite() {
		return responsabilite;
	}
	public void setResponsabilite(String responsabilite) {
		this.responsabilite = responsabilite;
	}
	public String getMailContact() {
		return mailContact;
	}
	public void setMailContact(String mailContact) {
		this.mailContact = mailContact;
	}
	

}
