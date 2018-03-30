package gov.wilaya.web;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import gov.wilaya.dao.ProjetRepository; 
import gov.wilaya.entities.Projet;

@RestController
@RequestMapping(value = "/projet")
public class ProjetController {
	@Autowired
	private ProjetRepository projetRepository;

	
	@RequestMapping(value = "/projets", method = RequestMethod.GET)
	public List<Projet> getProjets(){
		return projetRepository.findAll();
	}
	@RequestMapping(value = "/ajout", method = RequestMethod.POST)
	public void ajouterProjet(@RequestBody Projet projet) {
			projetRepository.save(projet);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public boolean udpateProjet(@PathVariable Long id, @RequestBody Projet projet) {
		if ( projetRepository.findOne(id) != null) {
			projet.setIdProjet(id);
			projetRepository.save(projet);
			return true;
		}
		return false;
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Projet getProjetById(@PathVariable Long id) {
		return projetRepository.findOne(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean supprimerProjet(@PathVariable Long id) {
		if ( projetRepository.findOne(id) != null) {
			 projetRepository.delete(id);
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value="projets", method=RequestMethod.DELETE)
	public void supprimerProjets(){
		 projetRepository.deleteAll();
	}
	
	@RequestMapping(value = "/commune", method = RequestMethod.GET)
	public List<Projet> getProjetParCommune(@RequestParam(name="commune") String commune) {
		return projetRepository.chercherParCommune(commune);
	}
	@RequestMapping(value = "secteur/{idSecteur}", method = RequestMethod.GET)
	public List<Projet> getProjetParSecteur(@PathVariable Long idSecteur) {
		return projetRepository.chercherParSecteur(idSecteur);
	}
}
	


