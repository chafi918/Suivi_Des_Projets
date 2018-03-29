package gov.wilaya.web;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import gov.wilaya.dao.MarcheRepository;
import gov.wilaya.entities.Marche;


@RestController
@RequestMapping(value = "/marche")
public class MarcheRestControlleur { 
	@Autowired
	private MarcheRepository marcheRepository;
	
	@RequestMapping(value = "/ajout", method = RequestMethod.POST)
	public void ajouterDivision(@RequestBody Marche marche) {
		if (marcheRepository.findByName(marche.getNumeroMarche()) == null || 
				marcheRepository.findByName(marche.getNumeroMarche()).isEmpty()) {
			marcheRepository.save(marche);
		}
	}
	@RequestMapping(value = "/allMarches", method = RequestMethod.GET)
	public List<Marche> getMarches() {
		return marcheRepository.findAll();
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Marche getMarcheById(@PathVariable Long id) {
		return marcheRepository.findOne(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean supprimerMarche(@PathVariable Long id) {
		if (marcheRepository.findOne(id) != null) {
			marcheRepository.delete(id);
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value="supprimerMarches", method=RequestMethod.DELETE)
	public void supprimerMarches(){
		marcheRepository.deleteAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public boolean udpateDivision(@PathVariable Long id, @RequestBody Marche marche) {
		if (marcheRepository.findOne(id) != null) {
			marche.setIdMarche(id);
			marcheRepository.save(marche);
			return true;
		}
		return false;
	}
	
	@RequestMapping(value = "/marches/nature/{id}", method = RequestMethod.GET)
	public List<Marche> getMarchesParNature(@PathVariable Long id) {
		return marcheRepository.findByNature(id);
	}
}
