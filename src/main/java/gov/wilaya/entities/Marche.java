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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
public class Marche implements Serializable {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idMarche;
	@NotBlank
	private String numeroMarche;
	@NotNull
	@Min(value=0)
	private double montantMarche;
	@NotNull
	@Min(value=0)
	private double montantTravauxRealises;
	@NotNull
	@Min(value=0)
	private double montantEmis;
	@NotNull
	private String delaiExecution;
	@NotNull
	private float tauxAvancement;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dateOS;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dateReceptionProvisoire;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "idNature")
	private NatureMarche nature;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "idEntreprise")
	private Entreprise entreprise;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "idProjet")
	private Projet projet;
	
	public Marche(String numeroMarche, double montantMarche, double montantTravauxRealises, String delaiExecution,
			float tauxAvancement,NatureMarche nature) {
		super();
		this.numeroMarche = numeroMarche;
		this.montantMarche = montantMarche;
		this.montantTravauxRealises = montantTravauxRealises;
		this.delaiExecution = delaiExecution;
		this.tauxAvancement = tauxAvancement;
		this.nature = nature;
	}
	
	public Marche() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}

	public Long getIdMarche() {
		return idMarche;
	}
	public void setIdMarche(Long idMarche) {
		this.idMarche = idMarche;
	}
	public String getNumeroMarche() {
		return numeroMarche;
	}
	public void setNumeroMarche(String numeroMarche) {
		this.numeroMarche = numeroMarche;
	}
	public double getMontantMarche() {
		return montantMarche;
	}
	public void setMontantMarche(double montantMarche) {
		this.montantMarche = montantMarche;
	}
	public double getMontantTravauxRealises() {
		return montantTravauxRealises;
	}
	public void setMontantTravauxRealises(double montantTravauxRealises) {
		this.montantTravauxRealises = montantTravauxRealises;
	}
	public String getDelaiExecution() {
		return delaiExecution;
	}
	public void setDelaiExecution(String delaiExecution) {
		this.delaiExecution = delaiExecution;
	}
	public float getTauxAvancement() {
		return tauxAvancement;
	}
	public void setTauxAvancement(float tauxAvancement) {
		this.tauxAvancement = tauxAvancement;
	}
	public NatureMarche getNature() {
		return nature;
	}
	public void setNature(NatureMarche nature) {
		this.nature = nature;
	}
	
	public Entreprise getEntreprise() {
		return entreprise;
	}
	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}
	

	public Date getDateOS() {
		return dateOS;
	}

	public void setDateOS(Date dateOS) {
		this.dateOS = dateOS;
	}
	

	public double getMontantEmis() {
		return montantEmis;
	}

	public void setMontantEmis(double montantEmis) {
		this.montantEmis = montantEmis;
	}

	public Date getDateReceptionProvisoire() {
		return dateReceptionProvisoire;
	}

	public void setDateReceptionProvisoire(Date dateReceptionProvisoire) {
		this.dateReceptionProvisoire = dateReceptionProvisoire;
	}

	@Override
	public String toString() {
		return "Marche [idMarche=" + idMarche + ", numeroMarche=" + numeroMarche + ", montantMarche=" + montantMarche
				+ ", montantTravauxRealises=" + montantTravauxRealises + ", montantEmis=" + montantEmis
				+ ", delaiExecution=" + delaiExecution + ", tauxAvancement=" + tauxAvancement + ", dateOS=" + dateOS
				+ ", dateReceptionProvisoire=" + dateReceptionProvisoire + ", nature=" + nature + ", entreprise="
				+ entreprise + ", projet=" + projet + "]";
	}

	
	
}
