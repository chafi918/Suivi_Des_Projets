package gov.wilaya.web;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gov.wilaya.dao.NatureMarcheRepository;
import gov.wilaya.entities.NatureMarche;
@RestController
@RequestMapping(value = "/adminNatureMarche")
public class NatureMarcheRestControlleur {
	@Autowired
	private NatureMarcheRepository natureMarcheRepository;

	@RequestMapping(value = "/ajout", method = RequestMethod.POST)
	public void ajouterNatureMarche(@RequestBody NatureMarche natureMarche) {
		if (natureMarcheRepository.findByName(natureMarche.getLibelleNature()) == null || 
				natureMarcheRepository.findByName(natureMarche.getLibelleNature()).isEmpty()) {
			natureMarcheRepository.save(natureMarche);
		}
	}
	
	@RequestMapping(value = "/allNatures", method = RequestMethod.GET)
	public List<NatureMarche> getNatures() {
		return natureMarcheRepository.findAll();
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public NatureMarche getNatureById(@PathVariable Long id) {
		return natureMarcheRepository.findOne(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean supprimerNature(@PathVariable Long id) {
		if (natureMarcheRepository.findOne(id) != null) {
			natureMarcheRepository.delete(id);
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping(value="natures", method=RequestMethod.DELETE)
	public void supprimerNatures(){
		natureMarcheRepository.deleteAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void udpateNature(@PathVariable Long id, @RequestBody NatureMarche natureMarche) {
		if (natureMarcheRepository.findOne(id) != null) {
			natureMarche.setIdNature(id);
			natureMarcheRepository.save(natureMarche);
		}

	}

}
