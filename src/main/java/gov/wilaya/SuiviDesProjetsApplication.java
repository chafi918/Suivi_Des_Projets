package gov.wilaya;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import gov.wilaya.dao.DivisionRepository;
import gov.wilaya.dao.ProfilRepository;
import gov.wilaya.dao.UtilisateurRepository;
import gov.wilaya.entities.Division;
import gov.wilaya.entities.Profil;
import gov.wilaya.entities.Utilisateur;
import gov.wilaya.service.UtilisateurService;

@SpringBootApplication
public class SuiviDesProjetsApplication implements CommandLineRunner{
	@Autowired
	private UtilisateurService utilisateurRepository;
	@Autowired
	private DivisionRepository divisionRepository;
	@Autowired
	private ProfilRepository profilRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SuiviDesProjetsApplication.class, args);
	}
	
	
	@Override
	public void run(String... arg0) throws Exception {
	/*	Division d1= divisionRepository.save(new Division("comptabilité"));
		Profil p1 = profilRepository.save(new Profil("ADMIN"));
		Profil p2 = profilRepository.save(new Profil("USER"));
		utilisateurRepository.save(new Utilisateur("oumaima", "houban", "ouma@fd.fr", "admin", "oumaima", "pdj",
				true, d1, p1));
		utilisateurRepository.save(new Utilisateur("oumaima", "houban", "houban@fd.fr", "user", "oumaima", "pdj",
				true, d1, p2));*/
	}
}
