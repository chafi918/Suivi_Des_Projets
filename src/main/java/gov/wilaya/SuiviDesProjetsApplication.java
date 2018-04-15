package gov.wilaya;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SuiviDesProjetsApplication implements CommandLineRunner{
  /*  @Autowired
	private ProjetRepository projetRepository;
    @Autowired
	private SecteurRepository secteurRepository;
	
    @Autowired
    private SecteurRestControlleur secteurController;
    
	@Autowired
	private StatutRepository statutRepository;*/
	
	public static void main(String[] args) {
		SpringApplication.run(SuiviDesProjetsApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		/*Secteur batiment = new Secteur("batiment");
		Secteur route = new Secteur("route");
		List<Secteur> secteurs = new ArrayList<>();
		secteurs.add(batiment);
		secteurs.add(route);
		secteurController.supprimerSecteurs();
		secteurController.ajouterSecteur(batiment);
		secteurController.ajouterSecteur(batiment);
		secteurController.ajouterSecteur(route);*/
	
//		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
////		projetRepository.save(new Projet("Construction d un espace Tamount","Agadir",5520000, false,df.parse("02/01/2017"),
////			false));//, new Secteur("batiment"),new Statut("en cours")
//		
//		Statut statut = new Statut("en cours");
//		statutRepository.save(statut);
//		Secteur secteur = new Secteur("batiment");
//		secteurRepository.save(secteur);
//		
//		System.out.println(secteur.getIdSecteur() + " and " + statut.getIdStatut());
//		System.out.println(secteurRepository.findAll().size() + " and " + statutRepository.findAll().size());
//		projetRepository.save(new Projet("Construction d un espace Tamount","Agadir",5520000, false,df.parse("02/01/2017"),
//				false,statut, secteur));
//		System.out.println(projetRepository.findAll().size());

	}
}
