package gov.wilaya.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import gov.wilaya.dao.UtilisateurRepository;
import gov.wilaya.entities.Utilisateur;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utilisateur utilisateur = utilisateurRepository.findByUserName(username);
		if(utilisateur == null) throw new UsernameNotFoundException(username);
		Collection<GrantedAuthority> authorities=new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(utilisateur.getProfil().getLibelleProfil()));
		return new User(utilisateur.getLoginUser(), utilisateur.getMdpUser(), authorities);
	}

}
