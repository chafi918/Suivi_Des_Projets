package gov.wilaya.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gov.wilaya.dao.SecteurRepository;
import gov.wilaya.entities.Secteur;

@RestController
@RequestMapping(value = "/adminSecteur")
public class SecteurRestControlleur {
	@Autowired
	private SecteurRepository secteurRepository;
	
	@RequestMapping(value = "/secteur", method = RequestMethod.POST)
	public void ajouterSecteur(@RequestBody Secteur secteur) {
		if (secteurRepository.findByName(secteur.getLibelleSecteur()) == null || 
				secteurRepository.findByName(secteur.getLibelleSecteur()).isEmpty()) {
			secteurRepository.save(secteur);
		}
	}

	@RequestMapping(value = "/secteurs", method = RequestMethod.GET)
	public List<Secteur> getSecteurs() {
		return secteurRepository.findAll();
	}

	@RequestMapping(value = "secteur/{id}", method = RequestMethod.GET)
	public Secteur getSecteur(@PathVariable Long id) {
		return secteurRepository.findOne(id);
	}

	@RequestMapping(value = "secteurs/{id}", method = RequestMethod.DELETE)
	public boolean supprimerSecteur(@PathVariable Long id) {
		if (secteurRepository.findOne(id) != null) {
			secteurRepository.delete(id);
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value="secteurs", method=RequestMethod.DELETE)
	public void supprimerSecteurs(){
		secteurRepository.deleteAll();
	}
	
	@RequestMapping(value = "secteurs/{id}", method = RequestMethod.PUT)
	public boolean udpateSecteur(@PathVariable Long id, @RequestBody Secteur secteur) {
		if (secteurRepository.findOne(id) != null) {
			secteur.setIdSecteur(id);
			secteurRepository.save(secteur);
			return true;
		}
		return false;
	}
}
