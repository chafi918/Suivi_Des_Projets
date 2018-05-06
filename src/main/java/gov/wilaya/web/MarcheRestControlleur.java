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

import gov.wilaya.beans.InputMarche;
import gov.wilaya.dao.MarcheRepository;
import gov.wilaya.dao.ProjetRepository;
import gov.wilaya.entities.Entreprise;
import gov.wilaya.entities.Marche;


@RestController
@RequestMapping(value = "/marche")
@CrossOrigin("*")
public class MarcheRestControlleur { 
	@Autowired
	private MarcheRepository marcheRepository;
	@Autowired
	private ProjetRepository projetRepository;
	
	@RequestMapping(value = "/ajout", method = RequestMethod.POST)
	public void ajouterDivision(@RequestBody InputMarche inputmarche) {
		Marche marche = inputmarche.getMarche();
		if (marcheRepository.searchByNumero(marche.getNumeroMarche()) == null || 
				marcheRepository.searchByNumero(marche.getNumeroMarche()).isEmpty()) {
			marche.setProjet(projetRepository.findOne(inputmarche.getIdProjet()));
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

	@RequestMapping(value = "/entrepriseBM/{id}", method = RequestMethod.GET)
	public Entreprise getEntrepriseByMarcheId(@PathVariable Long id) {
		System.out.println("idMArche= " + id);
		return marcheRepository.findOne(id).getEntreprise();
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
		
	@RequestMapping(value = "/projet", method = RequestMethod.GET)
	public Page<Marche> getMarchesParProjet(
			@RequestParam(name="idProjet")Long idProjet,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size) {
		return marcheRepository.findByProjet(idProjet, new PageRequest(page, size));
	}
	
	@RequestMapping(value = "/entreprise/{id}", method = RequestMethod.GET)
	public Page<Marche> getMarchesParEntreprise(@PathVariable Long id,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size) {
		return marcheRepository.findByEntreprise(id, new PageRequest(page, size));
	}
	
	@RequestMapping(value = "/numero", method = RequestMethod.GET)
	public Page<Marche> getMarchesParNumero(@RequestParam(name = "name", defaultValue = "") String numero,
			@RequestParam(name="idProjet")Long idProjet,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size) {
		return marcheRepository.findByNumero(numero,idProjet, new PageRequest(page, size));
	}
}
