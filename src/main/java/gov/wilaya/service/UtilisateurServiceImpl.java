package gov.wilaya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import gov.wilaya.dao.UtilisateurRepository;
import gov.wilaya.entities.Utilisateur;

@Service
public class UtilisateurServiceImpl implements UtilisateurService{

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Autowired
	private  BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public void save(Utilisateur utilisateur) {
		if ( utilisateurRepository.searchByLogin(utilisateur.getLoginUser()) == null || 
				utilisateurRepository.searchByLogin(utilisateur.getLoginUser()).isEmpty()){
			String password = bCryptPasswordEncoder.encode(utilisateur.getMdpUser());
			utilisateur.setMdpUser(password);
			utilisateurRepository.save(utilisateur);
		}
	}

}
