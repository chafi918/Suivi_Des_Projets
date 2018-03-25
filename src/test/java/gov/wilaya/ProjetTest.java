package gov.wilaya;

import static org.junit.Assert.*;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import gov.wilaya.dao.ProjetRepository;
import gov.wilaya.dao.SecteurRepository;
import gov.wilaya.dao.StatutRepository;
import gov.wilaya.entities.Projet;
import gov.wilaya.entities.Secteur;
import gov.wilaya.entities.Statut;

//@RunWith(SpringJUnit4ClassRunner.class)
public class ProjetTest {
	@Autowired
	private ProjetRepository projetRepository;
	
	@Autowired
	private SecteurRepository secteurRepository;
	
	@Autowired
	private StatutRepository statutRepository;
	

	@Test
	public void testAjout() throws Exception {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//		List<Projet> p1= projetRepository.findAll();
		Statut statut = new Statut("en cours");
		statutRepository.save(statut);
		Secteur secteur = new Secteur("batiment");
		secteurRepository.save(secteur);
		
		System.out.println(secteur.getIdSecteur() + " and " + statut.getIdStatut());
		System.out.println(secteurRepository.findAll().size() + " and " + statutRepository.findAll().size());

		/*p1.add(new Projet("Construction d un espace Tamount","Agadir",5520000, false,df.parse("02/01/2017"),
				false,statut, secteur));
		List<Projet> p2= projetRepository.findAll();
		assertTrue(p2.size()==p1.size()+1);*/
	}

}
