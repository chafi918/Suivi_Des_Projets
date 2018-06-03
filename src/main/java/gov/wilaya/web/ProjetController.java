package gov.wilaya.web;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gov.wilaya.beans.ExportProjetsString;
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
		switch (critere) {
		case "commune":
			return new ProjetStatsOutPut(allProjet.stream().collect(
					Collectors.groupingBy(projet -> projet.getCommune().getLibelleCommune(), Collectors.counting())));
		case "secteur":
			return new ProjetStatsOutPut(allProjet.stream().collect(
					Collectors.groupingBy(projet -> projet.getSecteur().getLibelleSecteur(), Collectors.counting())));
		case "statut":
			return new ProjetStatsOutPut(allProjet.stream().collect(
					Collectors.groupingBy(projet -> projet.getStatut().getLibelleStatut(), Collectors.counting())));
		case "annee":
			return statistiquesProjetAnnee(allProjet);
		default:
			return statistiquesProjetAnnee(allProjet);
		}

		
	}

	private ProjetStatsOutPut statistiquesProjetAnnee(List<Projet> allProjet){
		Map<String, Long> statMap = allProjet.stream().collect(
				Collectors.groupingBy(projet -> {
					Calendar cal = Calendar.getInstance();
					cal.setTime(projet.getDateCommTravaux());
					return String.valueOf(cal.get(Calendar.YEAR));	
				}, Collectors.counting()));
		return new ProjetStatsOutPut(statMap);
	}
	
	@RequestMapping(value = "/exportProjets", method = RequestMethod.GET)
	public ExportProjetsString exportProjets(HttpServletResponse response) throws IOException{
        String[] header = { "Intitulé", "Commune", "Province", "Taux d'avancement",
                "Montant programmé", "Date d'ouverture des plis", "Chargé du projet", "Date de début des travaux"
                , "Date de fin des travaux", "Secteur", "Statut"};
        String headers = String.join(",", header);
        
        List<Projet> projets = projetRepository.findAll();
        String recordAsCsv = projets.stream()
                .map(Projet::toCsvRow)
                .collect(Collectors.joining(System.getProperty("line.separator")));
        recordAsCsv = String.join(System.getProperty("line.separator"), headers, recordAsCsv);
        return new ExportProjetsString(recordAsCsv);
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
