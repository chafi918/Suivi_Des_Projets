package gov.wilaya.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gov.wilaya.dao.GlobalRepository;
import gov.wilaya.entities.Projet;

@RestController
public class ProjetController {
	@Autowired
	private GlobalRepository globalRepository;

	
	@RequestMapping
	public List<Projet> getProjets(){
		return globalRepository.getProjetRepository().findAll();
	}
	

}
