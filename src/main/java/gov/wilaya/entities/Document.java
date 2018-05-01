package gov.wilaya.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Document implements Serializable {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idDocument;
	@NotBlank
	private String nomDocument;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dateAjout;
	@NotEmpty
	private byte[] contenu;
	@NotBlank
	private String chargeurDocument;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "idType")
	private TypeDocument type;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "idProjet")
	private Projet projet;
	
	public Document() {
		super();
	}
	public Document(String nomDocument, Date dateAjout,byte[] contenu, String utilisateur, 
			TypeDocument type) {
		super();
		this.nomDocument = nomDocument;
		this.dateAjout = dateAjout;
		this.contenu = contenu;
		this.chargeurDocument = utilisateur;
		this.type = type;
	}
	
	public byte[] getContenu() {
		return contenu;
	}
	public void setContenu(byte[] contenu) {
		this.contenu = contenu;
	}
	public Long getIdDocument() {
		return idDocument;
	}
	public void setIdDocument(Long idDocument) {
		this.idDocument = idDocument;
	}
	public String getNomDocument() {
		return nomDocument;
	}
	public void setNomDocument(String nomDocument) {
		this.nomDocument = nomDocument;
	}
	public Date getDateAjout() {
		return dateAjout;
	}
	public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
	}
	public String getChargeurDocument() {
		return chargeurDocument;
	}
	public void setChargeurDocument(String chargeurDocument) {
		this.chargeurDocument = chargeurDocument;
	}
	public TypeDocument getType() {
		return type;
	}
	public void setType(TypeDocument type) {
		this.type = type;
	}

}
