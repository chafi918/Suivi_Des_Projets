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

import gov.wilaya.dao.ProfilRepository;
import gov.wilaya.entities.Profil;
import gov.wilaya.entities.Secteur;

@RestController
@RequestMapping(value = "/adminProfil")
@CrossOrigin("*")
public class ProfilRestControlleur {
	@Autowired
	private ProfilRepository profilRepository;

	@RequestMapping(value = "/ajout", method = RequestMethod.POST)
	public void ajouterProfil(@RequestBody Profil profil) {
		if (profilRepository.searchByName(profil.getLibelleProfil()) == null
				|| profilRepository.searchByName(profil.getLibelleProfil()).isEmpty()) {
			profilRepository.save(profil);
		}
	}

	@RequestMapping(value = "/profils", method = RequestMethod.GET)
	public List<Profil> getProfils() {
		return profilRepository.findAll();
	}

	@RequestMapping(value = "/getAllProfils", method = RequestMethod.GET)
	public Page<Profil> getProfils(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		return profilRepository.findAll(new PageRequest(page, size));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Profil getProfilById(@PathVariable Long id) {
		return profilRepository.findOne(id);
	}
	
	@RequestMapping(value = "/profilBN/", method = RequestMethod.GET)
	public Page<Profil> getProfilByName(@RequestParam(name = "name", defaultValue = "") String profil,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		return profilRepository.searchByName(profil, new PageRequest(page, size));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean supprimerProfil(@PathVariable Long id) {
		if (profilRepository.findOne(id) != null) {
			profilRepository.delete(id);
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = "profils", method = RequestMethod.DELETE)
	public void supprimerProfils() {
		profilRepository.deleteAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void udpateProfil(@PathVariable Long id, @RequestBody Profil profil) {
		if (profilRepository.findOne(id) != null) {
			profil.setIdProfil(id);
			profilRepository.save(profil);
		}

	}
}
