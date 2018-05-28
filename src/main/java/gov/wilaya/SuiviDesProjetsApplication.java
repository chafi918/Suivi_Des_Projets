package gov.wilaya;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import gov.wilaya.dao.DivisionRepository;
import gov.wilaya.dao.ProfilRepository;
import gov.wilaya.entities.Division;
import gov.wilaya.entities.Profil;
import gov.wilaya.entities.Utilisateur;
import gov.wilaya.service.UtilisateurService;

@SpringBootApplication
public class SuiviDesProjetsApplication implements CommandLineRunner {
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
	public void run(String... arg0) throws Exception {/*
		Division d1 = divisionRepository.findByName("comptabilité"); 
		if (d1 == null) {
			divisionRepository.save(new Division("comptabilité"));
		}
		
		Profil admin = profilRepository.findByProfilName("ADMIN");
		Profil user = profilRepository.findByProfilName("USER");
		
		if(admin == null){
			 admin = profilRepository.save(new Profil("ADMIN"));
		}
		
		if(user == null){
			user = profilRepository.save(new Profil("USER"));
		}
		
		utilisateurRepository
				.save(new Utilisateur("oumaima", "houban", "ouma@fd.fr", "admin", "oumaima", "pdj", true, d1, admin));
		utilisateurRepository
				.save(new Utilisateur("oumaima", "houban", "houban@fd.fr", "user", "oumaima", "pdj", true, d1, user));*/
	}
}
