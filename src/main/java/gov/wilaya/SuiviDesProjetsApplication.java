package gov.wilaya;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import gov.wilaya.dao.UtilisateurRepository;
import gov.wilaya.entities.Division;
import gov.wilaya.entities.Profil;
import gov.wilaya.entities.Utilisateur;
import gov.wilaya.service.UtilisateurService;

@SpringBootApplication
public class SuiviDesProjetsApplication implements CommandLineRunner{
	@Autowired
	private UtilisateurService utilisateurRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SuiviDesProjetsApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		utilisateurRepository.save(new Utilisateur("chafiq", "madkour", "chaf@fd.fr", "admin", "chafiq", "pdj",
				true, null, null));
		
		utilisateurRepository.save(new Utilisateur("chafiq", "madkour", "chaf@fd.fr", "user", "chafiq", "pdj",
				true, null, null));
	}
}
