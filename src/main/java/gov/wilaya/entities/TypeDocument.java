package gov.wilaya.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class TypeDocument implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTypeDoc;
	@NotBlank
	@Column(unique=true)
	private String libelleType;

	public TypeDocument(String libelleType) {
		super();
		this.libelleType = libelleType;
	}

	public TypeDocument() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdTypeDoc() {
		return idTypeDoc;
	}

	public void setIdTypeDoc(Long idTypeDoc) {
		this.idTypeDoc = idTypeDoc;
	}

	public String getLibelleType() {
		return libelleType;
	}

	public void setLibelleType(String libelleType) {
		this.libelleType = libelleType;
	}
}
