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

import gov.wilaya.dao.UtilisateurRepository;
import gov.wilaya.entities.Utilisateur;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin("*")
public class UtilisateurRestControlleur {
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@RequestMapping(value = "/ajout", method = RequestMethod.POST)
	public void ajouter(@RequestBody Utilisateur utilisateur) {
		if ( utilisateurRepository.searchByLogin(utilisateur.getLoginUser()) == null || 
				utilisateurRepository.searchByLogin(utilisateur.getLoginUser()).isEmpty()){
			     utilisateurRepository.save(utilisateur);
		}
	}
	@RequestMapping(value = "/{motCle}", method = RequestMethod.GET)
	public Page<Utilisateur> getUserByMC(@PathVariable String motCle,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		return utilisateurRepository.findByMotCle(motCle, new PageRequest(page, size));
	}
	@RequestMapping(value = "/{idDivision}", method = RequestMethod.GET)
	public Page<Utilisateur> getUserByDivision(@PathVariable Long idDivision,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		return utilisateurRepository.findByDivision(idDivision, new PageRequest(page, size));
	}
	@RequestMapping(value = "/getAllUtilisateurs", method = RequestMethod.GET)
	public Page<Utilisateur> getUsers(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		return utilisateurRepository.findAll(new PageRequest(page, size));
	}
	@RequestMapping(value = "/getActiveUtilisateurs", method = RequestMethod.GET)
	public Page<Utilisateur> getActiveUsers(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		return utilisateurRepository.findActiveUsers(new PageRequest(page, size));
	}
	
	@RequestMapping(value = "/utilisateurs", method = RequestMethod.GET)
	public List<Utilisateur> getUsers() {
		return utilisateurRepository.findAll();
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Utilisateur getUserById(@PathVariable Long id) {
		return utilisateurRepository.findOne(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean supprimerUser(@PathVariable Long id) {
		if (utilisateurRepository.findOne(id) != null) {
			utilisateurRepository.delete(id);
			return true;
		} else {
			return false;
		}
	}
	@RequestMapping(value="utilisateurs", method=RequestMethod.DELETE)
	public void supprimerUsers(){
		utilisateurRepository.deleteAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void udpateUser(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
		if (utilisateurRepository.findOne(id) != null) {
			utilisateur.setIdUser(id);
			utilisateurRepository.save(utilisateur);
		}

	}

}
