package gov.wilaya.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;
@Entity
public class Profil implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idProfil;
	@NotBlank
	private String libelleProfil;
	
	public Profil(String libelleProfil) {
		super();
		this.libelleProfil = libelleProfil;
	}
	
	public Profil() {
		super();
		// TODO Auto-generated constructor stub
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

}
