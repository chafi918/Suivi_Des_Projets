package gov.wilaya.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
@Entity 
public class Utilisateur implements Serializable{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idUser;
	@NotBlank
	private String nomUser;
	@NotBlank
	private String prenomUser;
	@NotBlank
	@Email
	private String mailUser;
	@NotBlank
	private String loginUser;
	@NotBlank
	private String mdpUser;
	private String responsabilite;
	private boolean estActive;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_DIVISION")
	private Division division;
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Utilisateur_Profil", 
        joinColumns = { @JoinColumn(name = "idUser") }, 
        inverseJoinColumns = { @JoinColumn(name = "idProfil") }
    )
    private Collection<Profil> profil;

	public Utilisateur(String nomUser, String prenomUser, String mailUser, String loginUser, String mdpUser,
			String responsabilite, boolean estActive, Division division) {
		super();
		this.nomUser = nomUser;
		this.prenomUser = prenomUser;
		this.mailUser = mailUser;
		this.loginUser = loginUser;
		this.mdpUser = mdpUser;
		this.responsabilite = responsabilite;
		this.estActive = estActive;
		this.division = division;
	}
	
	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getNomUser() {
		return nomUser;
	}
	public void setNomUser(String nomUser) {
		this.nomUser = nomUser;
	}
	public String getPrenomUser() {
		return prenomUser;
	}
	public void setPrenomUser(String prenomUser) {
		this.prenomUser = prenomUser;
	}
	public String getMailUser() {
		return mailUser;
	}
	public void setMailUser(String mailUser) {
		this.mailUser = mailUser;
	}
	public String getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}
	public String getMdpUser() {
		return mdpUser;
	}
	public void setMdpUser(String mdpUser) {
		this.mdpUser = mdpUser;
	}
	public String getResponsabilite() {
		return responsabilite;
	}
	public void setResponsabilite(String responsabilite) {
		this.responsabilite = responsabilite;
	}
	public boolean isEstActive() {
		return estActive;
	}
	public void setEstActive(boolean estActive) {
		this.estActive = estActive;
	}
	public Division getDivision() {
		return division;
	}
	public void setDivision(Division division) {
		this.division = division;
	}
	public Collection<Profil> getProfil() {
		return profil;
	}
	public void setProfil(Collection<Profil> profil) {
		this.profil = profil;
	}
}
