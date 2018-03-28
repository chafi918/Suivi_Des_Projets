package gov.wilaya.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Statut implements Serializable {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idStatut;
	@NotBlank
	private String libelleStatut;

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

	public Statut() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
