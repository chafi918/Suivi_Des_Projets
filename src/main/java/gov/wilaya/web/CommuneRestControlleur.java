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

import gov.wilaya.dao.CommuneRepository;
import gov.wilaya.dao.ProjetRepository;
import gov.wilaya.entities.Commune;
import gov.wilaya.entities.Secteur;


@RestController
@RequestMapping(value = "/admin")
@CrossOrigin("*")
public class CommuneRestControlleur {
	
	@Autowired
	private CommuneRepository communeRepository;
	@Autowired
	private ProjetRepository projetRepository;
	
	@RequestMapping(value = "/commune", method = RequestMethod.POST)
	public void ajouterCommune(@RequestBody Commune commune) {
		if (communeRepository.findByName(commune.getLibelleCommune()) == null
				|| communeRepository.findByName(commune.getLibelleCommune()).isEmpty()) {
			communeRepository.save(commune);
		}
	}

	@RequestMapping(value = "/communeBN", method = RequestMethod.GET)
	public Page<Commune> getCommuneByName(@RequestParam(name = "name", defaultValue = "") String commune,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		return communeRepository.searchByName(commune, new PageRequest(page, size));
	}

	@RequestMapping(value = "/communes", method = RequestMethod.GET)
	public List<Commune> getCommunes() {
		return communeRepository.findAll();
	}

	@RequestMapping(value = "/getAllCommunes", method = RequestMethod.GET)
	public Page<Commune> getCommunes(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		return communeRepository.findAll(new PageRequest(page, size));
	}

	@RequestMapping(value = "commune/{id}", method = RequestMethod.GET)
	public Commune getCommune(@PathVariable Long id) {
		return communeRepository.findOne(id);
	}
	
	@RequestMapping(value = "/commune", method = RequestMethod.GET)
	public Commune getCommuneByProjet(@RequestParam(name = "idProjet") Long idProjet) {
		return projetRepository.findOne(idProjet).getCommune();
	}

	@RequestMapping(value = "deleteCommune/{id}", method = RequestMethod.DELETE)
	public boolean supprimerCommune(@PathVariable Long id) {
		if (communeRepository.findOne(id) != null) {
			communeRepository.delete(id);
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = "updateCommune/{id}", method = RequestMethod.PUT)
	public boolean udpateCommune(@PathVariable Long id, @RequestBody Commune commune) {
		if (communeRepository.findOne(id) != null) {
			commune.setIdCommune(id);
			communeRepository.save(commune);
			return true;
		}
		return false;
	}

}
