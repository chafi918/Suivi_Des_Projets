package gov.wilaya.beans;

import gov.wilaya.entities.Marche;

public class InputMarche {

	private Marche marche;
	private Long idProjet;
	
	
	
	public InputMarche() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InputMarche(Marche marche, Long idProjet) {
		super();
		this.marche = marche;
		this.idProjet = idProjet;
	}
	public Marche getMarche() {
		return marche;
	}
	public void setMarche(Marche marche) {
		this.marche = marche;
	}
	public Long getIdProjet() {
		return idProjet;
	}
	public void setIdProjet(Long idProjet) {
		this.idProjet = idProjet;
	}
	
	
}
