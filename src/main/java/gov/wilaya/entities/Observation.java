package gov.wilaya.entities;

import java.io.Serializable;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
public class Observation implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idObservation;
	@NotBlank
	private String observation;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dateObservation;
	@NotBlank
	private String nomObservant;
	
	public Observation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Observation(String observation, Date dateObservation, String nomObservant) {
		super();
		this.observation = observation;
		this.dateObservation = dateObservation;
		this.nomObservant = nomObservant;
	}


	public Long getIdObservation() {
		return idObservation;
	}
	public void setIdObservation(Long idObservation) {
		this.idObservation = idObservation;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	public Date getDateObservation() {
		return dateObservation;
	}
	public void setDateObservation(Date dateObservation) {
		this.dateObservation = dateObservation;
	}
	public String getNomObservant() {
		return nomObservant;
	}
	public void setNomObservant(String nomObservant) {
		this.nomObservant = nomObservant;
	}
	

}
