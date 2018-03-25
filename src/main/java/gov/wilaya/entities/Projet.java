package gov.wilaya.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;

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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_SECTEUR")
	private Secteur secteur;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_STATUT")
	private Statut statut;
	@OneToMany(mappedBy="projet",fetch=FetchType.LAZY)
	private Collection<Observation> observations;
	@OneToMany(mappedBy="projet",fetch=FetchType.LAZY)
	private Collection<Document> documents;
	@OneToMany(mappedBy="projet",fetch=FetchType.LAZY)
	private Collection<Marche> marches;
	
	public Collection<Marche> getMarches() {
		return marches;
	}
	public void setMarches(Collection<Marche> marches) {
		this.marches = marches;
	}
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
	public Collection<Observation> getObservations() {
		return observations;
	}
	public void setObservations(Collection<Observation> observations) {
		this.observations = observations;
	}
	public Collection<Document> getDocuments() {
		return documents;
	}
	public void setDocuments(Collection<Document> documents) {
		this.documents = documents;
	}
	public Collection<Marche> getMarchés() {
		return marches;
	}
	public void setMarchés(Collection<Marche> marchés) {
		this.marches = marchés;
	}

	public Projet(String intitule, String commune, double montantProgramme, boolean estProjetRoyal, Date dateAO,
			boolean estMasque, Statut statut, Secteur secteur) {
		super();
		this.intitule = intitule;
		this.commune = commune;
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
