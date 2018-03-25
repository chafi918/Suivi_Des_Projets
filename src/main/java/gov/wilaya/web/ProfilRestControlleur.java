package gov.wilaya.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gov.wilaya.dao.ProfilRepository;
import gov.wilaya.entities.Profil;

@RestController
@RequestMapping(value = "/profil")
public class ProfilRestControlleur {
	@Autowired
	private ProfilRepository profilRepository;

	@RequestMapping(value = "/ajout", method = RequestMethod.POST)
	public void ajouterProfil(@RequestBody Profil profil) {
		profilRepository.save(profil);
	}
	
	@RequestMapping(value = "/profils", method = RequestMethod.GET)
	public List<Profil> getProfils() {
		return profilRepository.findAll();
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Profil getProfilById(@PathVariable Long id) {
		return profilRepository.findOne(id);
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

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void udpateProfil(@PathVariable Long id, @RequestBody Profil profil) {
		if (profilRepository.findOne(id) != null) {
			profil.setIdProfil(id);
			profilRepository.save(profil);
		}

	}
}
