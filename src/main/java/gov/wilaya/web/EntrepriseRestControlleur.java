package gov.wilaya.web;

import java.util.List;

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

import gov.wilaya.dao.EntrepriseRepository;
import gov.wilaya.entities.Entreprise;

@RestController
@RequestMapping(value = "/entreprise")
@CrossOrigin("*")
public class EntrepriseRestControlleur {

	@Autowired
	private EntrepriseRepository entrepriseRepository;
	
	@RequestMapping(value = "/ajout", method = RequestMethod.POST)
	public void ajouterContact(@RequestBody Entreprise entreprise) {
		if (entrepriseRepository.searchByName(entreprise.getNomEntreprise()) == null || 
				entrepriseRepository.searchByName(entreprise.getNomEntreprise()).isEmpty()) {
			entrepriseRepository.save(entreprise);
		}
	}
	
	@RequestMapping(value = "/entreprises", method = RequestMethod.GET)
	public List<Entreprise> getEntreprises() {
		return entrepriseRepository.findAll();
	}
	@RequestMapping(value = "/getEntreprises", method = RequestMethod.GET)
	public Page<Entreprise> getEntreprises(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		return entrepriseRepository.findAll(new PageRequest(page, size));
	}
	
	@RequestMapping(value = "/entreprises/name", method = RequestMethod.GET)
	public Page<Entreprise> getEntreprisesByName(@RequestParam(name = "name", defaultValue = "") String name,
		@RequestParam(name="page",defaultValue="0")int page,
		@RequestParam(name="size",defaultValue="5")int size) {
		return entrepriseRepository.findByName(name, new PageRequest(page, size));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Entreprise getEntrepriseById(@PathVariable Long id) {
		return entrepriseRepository.findOne(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean supprimerEntreprise(@PathVariable Long id) {
		if ( entrepriseRepository.findOne(id) != null) {
			entrepriseRepository.delete(id);
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value="entrepises", method=RequestMethod.DELETE)
	public void supprimerEntreprises(){
		 entrepriseRepository.deleteAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public boolean udpateEntreprise(@PathVariable Long id, @RequestBody Entreprise entreprise) {
		if ( entrepriseRepository.findOne(id) != null) {
			entreprise.setIdEntreprise(id);
			entrepriseRepository.save(entreprise);
			return true;
		}
		return false;
	}
	
}
