package gov.wilaya.beans;

import gov.wilaya.entities.Entreprise;
import gov.wilaya.entities.Marche;

public class InputEntreprise {
	
	private Entreprise entreprise;
	private Long idMarche;
	public InputEntreprise() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InputEntreprise(Entreprise entreprise, Long idMarche) {
		super();
		this.entreprise = entreprise;
		this.idMarche = idMarche;
	}
	public Entreprise  getEntreprise () {
		return entreprise;
	}
	public void setEntreprise (Entreprise entreprise) {
		this.entreprise = entreprise;
	}
	public Long getIdMarche() {
		return idMarche;
	}
	public void setIdMarche(Long idMarche) {
		this.idMarche = idMarche;
	}

}
