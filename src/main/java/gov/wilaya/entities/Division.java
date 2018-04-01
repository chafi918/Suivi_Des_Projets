package gov.wilaya.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;
@Entity
public class Division implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idDivision;
	@NotBlank
	private String libelleDivision;
	
	public Division() {
		super();
	}
	public Division(String libelleDivision) {
		super();
		this.libelleDivision = libelleDivision;
	}
	public Long getIdDivision() {
		return idDivision;
	}
	public void setIdDivision(Long idDivision) {
		this.idDivision = idDivision;
	}
	public String getLibelleDivision() {
		return libelleDivision;
	}
	public void setLibelleDivision(String libelleDivision) {
		this.libelleDivision = libelleDivision;
	}


}
