package gov.wilaya.entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
	@Lob
	private byte[] contenu;
	@NotBlank
	private String chargeurDocument;
	@NotBlank
	private String objetDocument;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "idType")
	private TypeDocument type;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "idProjet")
	private Projet projet;
	
	public Projet getProjet() {
		return projet;
	}
	public void setProjet(Projet projet) {
		this.projet = projet;
	}
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chargeurDocument == null) ? 0 : chargeurDocument.hashCode());
		result = prime * result + Arrays.hashCode(contenu);
		result = prime * result + ((dateAjout == null) ? 0 : dateAjout.hashCode());
		result = prime * result + ((idDocument == null) ? 0 : idDocument.hashCode());
		result = prime * result + ((nomDocument == null) ? 0 : nomDocument.hashCode());
		result = prime * result + ((projet == null) ? 0 : projet.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Document other = (Document) obj;
		if (chargeurDocument == null) {
			if (other.chargeurDocument != null)
				return false;
		} else if (!chargeurDocument.equals(other.chargeurDocument))
			return false;
		if (!Arrays.equals(contenu, other.contenu))
			return false;
		if (dateAjout == null) {
			if (other.dateAjout != null)
				return false;
		} else if (!dateAjout.equals(other.dateAjout))
			return false;
		if (idDocument == null) {
			if (other.idDocument != null)
				return false;
		} else if (!idDocument.equals(other.idDocument))
			return false;
		if (nomDocument == null) {
			if (other.nomDocument != null)
				return false;
		} else if (!nomDocument.equals(other.nomDocument))
			return false;
		if (projet == null) {
			if (other.projet != null)
				return false;
		} else if (!projet.equals(other.projet))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

}
