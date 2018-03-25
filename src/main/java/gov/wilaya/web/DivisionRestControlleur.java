package gov.wilaya.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gov.wilaya.dao.DivisionRepository;
import gov.wilaya.entities.Division;

@RestController
@RequestMapping(value = "/adminDivision")
public class DivisionRestControlleur {

	@Autowired
	private DivisionRepository divisionRepository;

	@RequestMapping(value = "/ajout", method = RequestMethod.POST)
	public void ajouterDivision(@RequestBody Division division) {
		if (divisionRepository.findByName("%"+division.getLibelleDivision()+"%") == null || 
				divisionRepository.findByName("%"+division.getLibelleDivision()+"%").isEmpty()) {
			divisionRepository.save(division);
		}
	}
	
	@RequestMapping(value = "/divisions", method = RequestMethod.GET)
	public List<Division> getDivisions() {
		return divisionRepository.findAll();
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Division getDivisionById(@PathVariable Long id) {
		return divisionRepository.findOne(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean supprimerDivision(@PathVariable Long id) {
		if (divisionRepository.findOne(id) != null) {
			divisionRepository.delete(id);
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value="divisions", method=RequestMethod.DELETE)
	public void supprimerDivisions(){
		divisionRepository.deleteAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public boolean udpateDivision(@PathVariable Long id, @RequestBody Division division) {
		if (divisionRepository.findOne(id) != null) {
			division.setIdDivision(id);
			divisionRepository.save(division);
			return true;
		}
		return false;
	}
}
