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

import gov.wilaya.dao.NatureMarcheRepository;
import gov.wilaya.entities.Division;
import gov.wilaya.entities.NatureMarche;
@RestController
@RequestMapping(value = "/adminNature")
@CrossOrigin("*")
public class NatureMarcheRestControlleur {
	@Autowired
	private NatureMarcheRepository natureMarcheRepository;

	@RequestMapping(value = "/ajout", method = RequestMethod.POST)
	public void ajouterNatureMarche(@RequestBody NatureMarche natureMarche) {
		if (natureMarcheRepository.searchByName(natureMarche.getLibelleNature()) == null || 
				natureMarcheRepository.searchByName(natureMarche.getLibelleNature()).isEmpty()) {
			natureMarcheRepository.save(natureMarche);
		}
	}
	
	@RequestMapping(value = "/getAllNatures", method = RequestMethod.GET)
	public Page<NatureMarche> getNatures(@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size) {
		return natureMarcheRepository.findAll(new PageRequest(page, size));
	}
	@RequestMapping(value = "/name", method = RequestMethod.GET)
	public Page<Division> getNaturesByName(@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		return natureMarcheRepository.searchByName(name, new PageRequest(page, size));
	}
	
	@RequestMapping(value = "/allNatures", method = RequestMethod.GET)
	public List<NatureMarche> getNatures() {
		return natureMarcheRepository.findAll();
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public NatureMarche getNatureById(@PathVariable Long id) {
		return natureMarcheRepository.findOne(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean supprimerNature(@PathVariable Long id) {
		if (natureMarcheRepository.findOne(id) != null) {
			natureMarcheRepository.delete(id);
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping(value="natures", method=RequestMethod.DELETE)
	public void supprimerNatures(){
		natureMarcheRepository.deleteAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void udpateNature(@PathVariable Long id, @RequestBody NatureMarche natureMarche) {
		if (natureMarcheRepository.findOne(id) != null) {
			natureMarche.setIdNature(id);
			natureMarcheRepository.save(natureMarche);
		}

	}

}
