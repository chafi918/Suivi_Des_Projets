package gov.wilaya.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gov.wilaya.dao.ObservationRepository;
import gov.wilaya.entities.Observation;

@RestController
@RequestMapping(value = "/observation")
public class ObservationRestControlleur {
	
	@Autowired
	private ObservationRepository observationRepository;
	
	@RequestMapping(value = "/ajout", method = RequestMethod.POST)
	public void ajouterObservation(@RequestBody Observation observation) {
			observationRepository.save(observation);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean supprimerObservation(@PathVariable Long id) {
		if (observationRepository.findOne(id) != null) {
			observationRepository.delete(id);
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value="supprimerObservations", method=RequestMethod.DELETE)
	public void supprimerMarches(){
		observationRepository.deleteAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public boolean udpateDivision(@PathVariable Long id, @RequestBody Observation observation) {
		if (observationRepository.findOne(id) != null) {
			observation.setIdObservation(id);
			observationRepository.save(observation);
			return true;
		}
		return false;
	}
	@RequestMapping(value = "/projet/{id}", method = RequestMethod.GET)
	public List<Observation> getObservationByProjet(@PathVariable Long id) {
		return observationRepository.findByProjet(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Observation getObservationById(@PathVariable Long id) {
		return observationRepository.findOne(id);
	}

}
