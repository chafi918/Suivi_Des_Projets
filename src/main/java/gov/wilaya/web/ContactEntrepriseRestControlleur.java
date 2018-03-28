package gov.wilaya.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gov.wilaya.dao.ContactEntrepriseRepository;
import gov.wilaya.entities.ContactEntreprise;
@RestController
@RequestMapping(value = "/contact")
public class ContactEntrepriseRestControlleur {
	
	@Autowired
	private ContactEntrepriseRepository contactEntrepriseRepository;
	@RequestMapping(value = "/ajout", method = RequestMethod.POST)
	public void ajouterContact(@RequestBody ContactEntreprise contactEntreprise) {
		if (contactEntrepriseRepository.findByName(contactEntreprise.getNomContact()) == null || 
				contactEntrepriseRepository.findByName(contactEntreprise.getNomContact()).isEmpty()) {
			contactEntrepriseRepository.save(contactEntreprise);
		}
	}
	
	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	public List<ContactEntreprise> getContacts() {
		return contactEntrepriseRepository.findAll();
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ContactEntreprise getContactById(@PathVariable Long id) {
		return contactEntrepriseRepository.findOne(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean supprimerContact(@PathVariable Long id) {
		if ( contactEntrepriseRepository.findOne(id) != null) {
			 contactEntrepriseRepository.delete(id);
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value="contacts", method=RequestMethod.DELETE)
	public void supprimerContacts(){
		 contactEntrepriseRepository.deleteAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public boolean udpateDivision(@PathVariable Long id, @RequestBody ContactEntreprise contactEntreprise) {
		if ( contactEntrepriseRepository.findOne(id) != null) {
			 contactEntreprise.setIdContact(id);
			 contactEntrepriseRepository.save(contactEntreprise);
			return true;
		}
		return false;
	}

}
