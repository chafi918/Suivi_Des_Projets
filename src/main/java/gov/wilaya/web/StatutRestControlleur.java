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

import gov.wilaya.dao.StatutRepository;
import gov.wilaya.entities.Statut;

@RestController
@RequestMapping(value = "/admin")
@CrossOrigin("*")
public class StatutRestControlleur {
	@Autowired
	private StatutRepository statutRepository;

	@RequestMapping(value = "/statut", method = RequestMethod.POST)
	public void ajouterStatut(@RequestBody Statut statut) {
		if (statutRepository.searchByName(statut.getLibelleStatut()) == null
				|| statutRepository.searchByName(statut.getLibelleStatut()).isEmpty()) {
			statutRepository.save(statut);
		}
	}

	@RequestMapping(value = "/statutBN", method = RequestMethod.GET)
	public Page<Statut> getStatutByName(@RequestParam(name = "name", defaultValue = "")  String statut,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		return statutRepository.findByName(statut, new PageRequest(page, size));
	}

	@RequestMapping(value = "/getAllStatuts", method = RequestMethod.GET)
	public Page<Statut> getStatuts(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		return statutRepository.findAll(new PageRequest(page, size));
	}

	@RequestMapping(value = "/statuts", method = RequestMethod.GET)
	public List<Statut> getStatuts() {
		return statutRepository.findAll();
	}

	@RequestMapping(value = "statut/{id}", method = RequestMethod.GET)
	public Statut getStatut(@PathVariable Long id) {
		return statutRepository.findOne(id);
	}

	@RequestMapping(value = "statuts/{id}", method = RequestMethod.DELETE)
	public boolean supprimerStatut(@PathVariable Long id) {
		if (statutRepository.findOne(id) != null) {
			statutRepository.delete(id);
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = "statuts", method = RequestMethod.DELETE)
	public void supprimerStatuts() {
		statutRepository.deleteAll();
	}

	@RequestMapping(value = "statuts/{id}", method = RequestMethod.PUT)
	public boolean udpateStatut(@PathVariable Long id, @RequestBody Statut statut) {
		if (statutRepository.findOne(id) != null) {
			statut.setIdStatut(id);
			statutRepository.save(statut);
			return true;
		}
		return false;
	}
}
