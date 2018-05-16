package gov.wilaya.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

import gov.wilaya.beans.ProjetStatsOutPut;
import gov.wilaya.dao.ProjetRepository;
import gov.wilaya.entities.Projet;

@RestController
@RequestMapping(value = "/projet")
@CrossOrigin("*")
public class ProjetController {
	@Autowired
	private ProjetRepository projetRepository;

	@RequestMapping(value = "/getProjets", method = RequestMethod.GET)
	public Page<Projet> getProjets(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		return projetRepository.findAll(new PageRequest(page, size));
	}

	@RequestMapping(value = "/ajout", method = RequestMethod.POST)
	public void ajouterProjet(@RequestBody Projet projet) {
		projetRepository.save(projet);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public boolean udpateProjet(@PathVariable Long id, @RequestBody Projet projet) {
		if (projetRepository.findOne(id) != null) {
			projet.setIdProjet(id);
			projetRepository.save(projet);
			return true;
		}
		return false;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Projet getProjetById(@PathVariable Long id) {
		System.out.println("getProjet:" + id);
		return projetRepository.findOne(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean supprimerProjet(@PathVariable Long id) {
		if (projetRepository.findOne(id) != null) {
			projetRepository.delete(id);
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = "projets", method = RequestMethod.DELETE)
	public void supprimerProjets() {
		projetRepository.deleteAll();
	}

	@RequestMapping(value = "/commune", method = RequestMethod.GET)
	public Page<Projet> getProjetParCommune(@RequestParam(name = "commune") String commune,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		return projetRepository.chercherParCommune(commune, new PageRequest(page, size));
	}

	@RequestMapping(value = "/intitule", method = RequestMethod.GET)
	public Page<Projet> getProjetParIntitule(@RequestParam(name = "intitule") String intitule,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		return projetRepository.chercherParIntitule(intitule, new PageRequest(page, size));
	}

	@RequestMapping(value = "/royal", method = RequestMethod.GET)
	public Page<Projet> getProjetRoyal(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		return projetRepository.chercherProjetRoyal(new PageRequest(page, size));
	}

	@RequestMapping(value = "/masque", method = RequestMethod.GET)
	public Page<Projet> getProjetMasque(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		return projetRepository.chercherProjetMasque(new PageRequest(page, size));
	}

	@RequestMapping(value = "/statistiques", method = RequestMethod.GET)
	public ProjetStatsOutPut getProjetStatistiquesParCritéres(
			@RequestParam(name = "critere", defaultValue = "") String critere) {
		List<Projet> allProjet = projetRepository.findAll();
		System.out.println("----------- critere ------ : " + critere);
		// ****************** For test : to delete
		Map<String, Long> projetMap2 = new HashMap<>();
		projetMap2.put("aaa", new Long(10));
		projetMap2.put("bba", new Long(11));
		projetMap2.put("aba", new Long(12));
		projetMap2.put("aab", new Long(13));
		projetMap2.put("bab", new Long(13));
		projetMap2.put("acab", new Long(13));
		projetMap2.put("aadb", new Long(13));
		projetMap2.put("afb", new Long(13));
		projetMap2.put("agb", new Long(13));
		// ******************
		switch (critere) {
		case "commune":
			return new ProjetStatsOutPut(allProjet.stream().collect(
					Collectors.groupingBy(projet -> projet.getCommune().getLibelleCommune(), Collectors.counting())));
		case "secteur":
			return new ProjetStatsOutPut(allProjet.stream().collect(
					Collectors.groupingBy(projet -> projet.getSecteur().getLibelleSecteur(), Collectors.counting())));
		default:
			return new ProjetStatsOutPut(projetMap2);
		}

		
	}

	@RequestMapping(value = "/recherche", method = RequestMethod.GET)
	public Page<Projet> getProjetParCritere(@RequestParam(name = "critere", defaultValue = "") String critere,
			@RequestParam(name = "mc", defaultValue = "") String mc,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		switch (critere) {
		case "statut":
			return projetRepository.chercherParStatut(mc, new PageRequest(page, size));
		case "secteur":
			return projetRepository.chercherParSecteur(mc, new PageRequest(page, size));
		case "commune":
			return projetRepository.chercherParCommune(mc, new PageRequest(page, size));
		case "royal":
			return projetRepository.chercherProjetRoyal(new PageRequest(page, size));
		case "masque":
			return projetRepository.chercherProjetMasque(new PageRequest(page, size));
		default:
			return projetRepository.chercherParIntitule(mc, new PageRequest(page, size));
		}

	}

}
