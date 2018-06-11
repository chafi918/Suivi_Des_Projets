package gov.wilaya.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;
@Entity
public class Province implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idProvince;
	@NotBlank
	@Column(unique=true)
	private String libelleProvince;
	public Province() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Province(Long idProvince, String libelleProvince) {
		super();
		this.idProvince = idProvince;
		this.libelleProvince = libelleProvince;
	}
	public Long getIdProvince() {
		return idProvince;
	}
	public void setIdProvince(Long idProvince) {
		this.idProvince = idProvince;
	}
	public String getLibelleProvince() {
		return libelleProvince;
	}
	public void setLibelleProvince(String libelleProvince) {
		this.libelleProvince = libelleProvince;
	}
	
	

}
