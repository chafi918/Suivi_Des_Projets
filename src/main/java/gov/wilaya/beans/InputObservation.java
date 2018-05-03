package gov.wilaya.beans;

import gov.wilaya.entities.Observation;

public class InputObservation {

	private Observation observation;
	private Long idProjet;
	
	
	
	@Override
	public String toString() {
		return "InputObservation [observation=" + observation + ", idProjet=" + idProjet + "]";
	}



	public InputObservation() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Observation getObservation() {
		return observation;
	}



	public void setObservation(Observation observation) {
		this.observation = observation;
	}



	public Long getIdProjet() {
		return idProjet;
	}



	public void setIdProjet(Long idProjet) {
		this.idProjet = idProjet;
	}



	public InputObservation(Observation observation, Long idProjet) {
		super();
		this.observation = observation;
		this.idProjet = idProjet;
	}

	
	
}
