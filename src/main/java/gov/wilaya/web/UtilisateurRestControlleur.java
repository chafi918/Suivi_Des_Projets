package gov.wilaya.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gov.wilaya.dao.UtilisateurRepository;
import gov.wilaya.entities.Utilisateur;

@RestController
@RequestMapping(value = "/users")
public class UtilisateurRestControlleur {
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@RequestMapping(value = "/ajout", method = RequestMethod.POST)
	public void ajouter(@RequestBody Utilisateur utilisateur) {
		utilisateurRepository.save(utilisateur);
	}
	
	@RequestMapping(value = "/utilisateurs", method = RequestMethod.GET)
	public List<Utilisateur> getUsers() {
		return utilisateurRepository.findAll();
	}
	@RequestMapping(value = "utilisateur/{id}", method = RequestMethod.GET)
	public Utilisateur getUserById(@PathVariable Long id) {
		return utilisateurRepository.findOne(id);
	}

	@RequestMapping(value = "utilisateur/{id}", method = RequestMethod.DELETE)
	public boolean supprimerUser(@PathVariable Long id) {
		if (utilisateurRepository.findOne(id) != null) {
			utilisateurRepository.delete(id);
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = "utilisateur/{id}", method = RequestMethod.PUT)
	public void udpateUser(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
		if (utilisateurRepository.findOne(id) != null) {
			utilisateur.setIdUser(id);
			utilisateurRepository.save(utilisateur);
		}

	}

}
