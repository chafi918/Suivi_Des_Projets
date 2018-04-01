package gov.wilaya.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;
@Entity
public class NatureMarche implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idNature;
	@NotBlank
	private String libelleNature;
	/*@OneToMany(mappedBy="nature")
	private Collection<Marche> marches;*/
	
	public NatureMarche(String libelleNature) {
		super();
		this.libelleNature = libelleNature;
	}
	
	public NatureMarche() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdNature() {
		return idNature;
	}
	public void setIdNature(Long idNature) {
		this.idNature = idNature;
	}
	public String getLibelleNature() {
		return libelleNature;
	}
	public void setLibelleNature(String libelleNature) {
		this.libelleNature = libelleNature;
	}
	/*public Collection<Marche> getMarches() {
		return marches;
	}
	public void setMarches(Collection<Marche> marches) {
		this.marches = marches;
	}*/
}
