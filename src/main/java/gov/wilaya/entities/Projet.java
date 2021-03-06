package gov.wilaya.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	@NotNull
	@ManyToOne
	@JoinColumn(name = "idCommune")
	private Commune commune;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "idProvince")
	private Province province;
	@NotNull
	private int anneeDeProgrammation;
	@NotNull
	private float tauxAvancement;
	@NotNull
	@Min(value=0)
	private double montantProgramme;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dateOP;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dateFinTravaux;
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
	@NotNull
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "idStatut")
	private Statut statut;
	
	public String toCsvRow() {
	    return Stream.of(intitule
	    		, commune.getLibelleCommune()
	    		, province.getLibelleProvince()
	    		, Float.toString(tauxAvancement)
	    		, String.valueOf(montantProgramme)
	    		, dateOP == null ? "-" : new SimpleDateFormat("yyyy-MM-dd",   Locale.getDefault()).format(dateOP) 
	    		, chargeDuProjet
	    		, dateCommTravaux==null ? "-" : new SimpleDateFormat("yyyy-MM-dd",   Locale.getDefault()).format(dateCommTravaux)
	    	    , dateFinTravaux==null ? "-" : new SimpleDateFormat("yyyy-MM-dd",   Locale.getDefault()).format(dateFinTravaux)
	    		, secteur.getLibelleSecteur()
	    		, statut.getLibelleStatut())
	            .map(value -> value.replaceAll("\"", "\"\""))
	            .map(value -> Stream.of("\"", ",").anyMatch(value::contains) ? "\"" + value + "\"" : value)
	            .collect(Collectors.joining(","));
	}
	
	public Projet(String intitule, Commune commune,Province province, double montantProgramme, boolean estProjetRoyal, Date dateAO,
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
	}
	
	public Date getDateFinTravaux() {
		return dateFinTravaux;
	}
	public void setDateFinTravaux(Date dateFinTravaux) {
		this.dateFinTravaux = dateFinTravaux;
	}

	public Commune getCommune() {
		return commune;
	}

	public void setCommune(Commune commune) {
		this.commune = commune;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public float getTauxAvancement() {
		return tauxAvancement;
	}

	public void setTauxAvancement(float tauxAvancement) {
		this.tauxAvancement = tauxAvancement;
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

	public int getAnneeDeProgrammation() {
		return anneeDeProgrammation;
	}

	public void setAnneeDeProgrammation(int anneeDeProgrammation) {
		this.anneeDeProgrammation = anneeDeProgrammation;
	}
	
}
