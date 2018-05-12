package gov.wilaya.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import gov.wilaya.entities.Utilisateur;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(
			HttpServletRequest request, 
			HttpServletResponse response){
		Utilisateur utilisateur = null;
		try {
			utilisateur = new ObjectMapper().readValue(request.getInputStream(), Utilisateur.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		System.out.println(utilisateur.getLoginUser() + " =    jhlckjhlkjsdc === " + utilisateur.getMdpUser());
		return authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						utilisateur.getLoginUser(),
						utilisateur.getMdpUser()
						));
	}
	
	@Override
	protected void successfulAuthentication(
			HttpServletRequest request,
			HttpServletResponse response, 
			FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		User springUser = (User) authResult.getPrincipal();
		
		String jwtToken = Jwts.builder()
				.setSubject(springUser.getUsername())
				.setExpiration(
						new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, SecurityConstants.SECRET)
				.claim("profil", springUser.getAuthorities())
				.compact();
		response.addHeader(SecurityConstants.HEADER_STRING,
				SecurityConstants.TOKEN_PREFIX+jwtToken);
	}

}
