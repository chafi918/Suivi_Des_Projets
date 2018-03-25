package gov.wilaya.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GlobalRepository {
	@Autowired
	private ProjetRepository projetRepository;
	
	@Autowired
	private StatutRepository statutRepository;
	
	@Autowired
	private SecteurRepository secteurRepository;
	public GlobalRepository() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GlobalRepository(ProjetRepository projetRepository, StatutRepository statutRepository,
			SecteurRepository secteurRepository) {
		super();
		this.projetRepository = projetRepository;
		this.statutRepository = statutRepository;
		this.secteurRepository = secteurRepository;
	}
	public ProjetRepository getProjetRepository() {
		return projetRepository;
	}
	public void setProjetRepository(ProjetRepository projetRepository) {
		this.projetRepository = projetRepository;
	}
	public StatutRepository getStatutRepository() {
		return statutRepository;
	}
	public void setStatutRepository(StatutRepository statutRepository) {
		this.statutRepository = statutRepository;
	}
	public SecteurRepository getSecteurRepository() {
		return secteurRepository;
	}
	public void setSecteurRepository(SecteurRepository secteurRepository) {
		this.secteurRepository = secteurRepository;
	}
	
	
}