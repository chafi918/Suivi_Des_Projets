package gov.wilaya.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;
@Entity
public class Commune implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idCommune;
	@NotBlank
	@Column(unique=true)
	private String libelleCommune;
	public Commune() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Commune(Long idCommune, String libelleCommune) {
		super();
		this.idCommune = idCommune;
		this.libelleCommune = libelleCommune;
	}
	public Long getIdCommune() {
		return idCommune;
	}
	public void setIdCommune(Long idCommune) {
		this.idCommune = idCommune;
	}
	public String getLibelleCommune() {
		return libelleCommune;
	}
	public void setLibelleCommune(String libelleCommune) {
		this.libelleCommune = libelleCommune;
	}
	

}
