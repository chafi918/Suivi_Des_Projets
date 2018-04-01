package gov.wilaya.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gov.wilaya.dao.EntrepriseRepository;
import gov.wilaya.dao.MarcheRepository;
import gov.wilaya.dao.NatureMarcheRepository;
import gov.wilaya.dao.ProjetRepository;
import gov.wilaya.entities.Marche;


@RestController
@RequestMapping(value = "/marche")
public class MarcheRestControlleur { 
	@Autowired
	private MarcheRepository marcheRepository;
	@Autowired
	private ProjetRepository projetRepository;
	@Autowired
	private NatureMarcheRepository natureMarcheRepository;
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	
	@RequestMapping(value = "/ajout", method = RequestMethod.POST)
	public void ajouterDivision(@RequestBody Marche marche,
			@RequestParam Long idProjet, @RequestParam Long idNature, @RequestParam Long idEntreprise) {
		if (marcheRepository.searchByNumero(marche.getNumeroMarche()) == null || 
				marcheRepository.searchByNumero(marche.getNumeroMarche()).isEmpty()) {
			marche.setEntreprise(entrepriseRepository.findOne(idEntreprise));
			marche.setProjet(projetRepository.findOne(idProjet));
			marche.setNature(natureMarcheRepository.findOne(idNature));
			marcheRepository.save(marche);
		}
	}
	
	@RequestMapping(value = "/getAllMarches", method = RequestMethod.GET)
	public Page<Marche> getMarches(@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size) {
		return marcheRepository.findAll(new PageRequest(page, size));
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
	
	@RequestMapping(value = "/nature/{id}", method = RequestMethod.GET)
	public Page<Marche> getMarchesParNature(@PathVariable Long id,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size) {
		return marcheRepository.findByNature(id, new PageRequest(page, size));
	}
	
	@RequestMapping(value = "/projet/{id}", method = RequestMethod.GET)
	public Page<Marche> getMarchesParProjet(@PathVariable Long id,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size) {
		return marcheRepository.findByProjet(id, new PageRequest(page, size));
	}
	
	@RequestMapping(value = "/entreprise/{id}", method = RequestMethod.GET)
	public Page<Marche> getMarchesParEntreprise(@PathVariable Long id,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size) {
		return marcheRepository.findByEntreprise(id, new PageRequest(page, size));
	}
	
	@RequestMapping(value = "/nature/{numero}", method = RequestMethod.GET)
	public Page<Marche> getMarchesParNumero(@PathVariable String numero,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size) {
		return marcheRepository.findByNumero(numero, new PageRequest(page, size));
	}
}
