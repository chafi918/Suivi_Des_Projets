package gov.wilaya.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gov.wilaya.beans.InputObservation;
import gov.wilaya.dao.ObservationRepository;
import gov.wilaya.dao.ProjetRepository;
import gov.wilaya.entities.Observation;

@RestController
@RequestMapping(value = "/observation")
@CrossOrigin("*")
public class ObservationRestControlleur {

	@Autowired
	private ObservationRepository observationRepository;

	@Autowired
	private ProjetRepository projetRepository;
	
	@RequestMapping(value = "/ajout", method = RequestMethod.POST)
	public void ajouterObservation(@RequestBody InputObservation inputObservation) {
		Observation observation = inputObservation.getObservation();
		observation.setProjet(projetRepository.findOne(inputObservation.getIdProjet()));
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

	@RequestMapping(value = "supprimerObservations", method = RequestMethod.DELETE)
	public void supprimerMarches() {
		observationRepository.deleteAll();
	}
	
	@RequestMapping(value = "/getObservations", method = RequestMethod.GET)
	public Page<Observation> getObservations(@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size){
		return observationRepository.findAll(new PageRequest(page, size));
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

	@RequestMapping(value = "/projet", method = RequestMethod.GET)
	public Page<Observation> getObservationByProjet(@RequestParam(name = "idProjet") Long idProjet,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		return observationRepository.findByProjet(idProjet, new PageRequest(page, size));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Observation getObservationById(@PathVariable Long id) {
		return observationRepository.findOne(id);
	}

}
