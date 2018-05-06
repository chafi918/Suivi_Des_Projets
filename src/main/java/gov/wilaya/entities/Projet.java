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
public class Projet implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idProjet;
	@NotBlank
	private String intitule;
	@NotBlank
	private String commune;
	@NotBlank
	private String province;
	@NotNull
	private float tauxAvancement;
	@NotNull
	@Min(value=0)
	private double montantProgramme;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dateOP;
	private String chargeDuProjet;
	private boolean estProjetRoyal;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dateCommTravaux;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dateAO;
	private boolean estMasque;
	@NotNull
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "idSecteur")
	private Secteur secteur;
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public float getTauxAvancement() {
		return tauxAvancement;
	}

	public void setTauxAvancement(float tauxAvancement) {
		this.tauxAvancement = tauxAvancement;
	}
	@NotNull
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "idStatut")
	private Statut statut;

	public Long getIdProjet() {
		return idProjet;
	}
	
	public void setIdProjet(Long idProjet) {
		this.idProjet = idProjet;
	}
	public String getIntitule() {
		return intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	public String getCommune() {
		return commune;
	}
	public void setCommune(String commune) {
		this.commune = commune;
	}
	public double getMontantProgramme() {
		return montantProgramme;
	}
	public void setMontantProgramme(double montantProgramme) {
		this.montantProgramme = montantProgramme;
	}
	public Date getDateOP() {
		return dateOP;
	}
	public void setDateOP(Date dateOP) {
		this.dateOP = dateOP;
	}
	public String getChargeDuProjet() {
		return chargeDuProjet;
	}
	public void setChargeDuProjet(String chargeDuProjet) {
		this.chargeDuProjet = chargeDuProjet;
	}
	public boolean isEstProjetRoyal() {
		return estProjetRoyal;
	}
	public void setEstProjetRoyal(boolean estProjetRoyal) {
		this.estProjetRoyal = estProjetRoyal;
	}
	public Date getDateCommTravaux() {
		return dateCommTravaux;
	}
	public void setDateCommTravaux(Date dateCommTravaux) {
		this.dateCommTravaux = dateCommTravaux;
	}
	public Date getDateAO() {
		return dateAO;
	}
	public void setDateAO(Date dateAO) {
		this.dateAO = dateAO;
	}
	public boolean isEstMasque() {
		return estMasque;
	}
	public void setEstMasque(boolean estMasque) {
		this.estMasque = estMasque;
	}
	public Secteur getSecteur() {
		return secteur;
	}
	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}
	public Statut getStatut() {
		return statut;
	}
	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public Projet(String intitule, String commune,String province, double montantProgramme, boolean estProjetRoyal, Date dateAO,
			boolean estMasque, Statut statut, Secteur secteur) {
		super();
		this.intitule = intitule;
		this.commune = commune;
		this.province = province;
		this.montantProgramme = montantProgramme;
		this.estProjetRoyal = estProjetRoyal;
		this.dateAO = dateAO;
		this.estMasque = estMasque;
		this.secteur = secteur;
		this.statut = statut;
	}
	public Projet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
