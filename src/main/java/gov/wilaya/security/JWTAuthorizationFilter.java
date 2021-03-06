package gov.wilaya.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Origin, Accept, X-Requested-With, Content-Type, "
				+ "Access-Control-Request-Method, Access-Control-Request-Headers,"
				+ "authorization");
		response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, "
				+ "Access-Control-Allow-Credentials, authorization");
		if (request.getMethod().equals("OPTIONS")) {
			response.setStatus(HttpServletResponse.SC_OK);
		}else {
			String jwtToken = request.getHeader(SecurityConstants.HEADER_STRING);
			System.out.println("jwtToken: " + jwtToken);
			if (jwtToken ==null || !jwtToken.startsWith(SecurityConstants.TOKEN_PREFIX)) {
				filterChain.doFilter(request, response);
				return;
			}
			Claims claims = Jwts.parser().
					setSigningKey(SecurityConstants.SECRET)
					.parseClaimsJws(jwtToken.replace(SecurityConstants.TOKEN_PREFIX, ""))
					.getBody();
			String username = claims.getSubject();
			ArrayList<Map<String, String>> mapProfils =(ArrayList<Map<String, String>>) claims.get("profil");
			Collection<GrantedAuthority> profil = new ArrayList<>(); 
			profil.add(new SimpleGrantedAuthority(mapProfils.get(0).get("authority"))); 
			UsernamePasswordAuthenticationToken authorizationToken = 
					new UsernamePasswordAuthenticationToken(username, null, profil);
			SecurityContextHolder.getContext().setAuthentication(authorizationToken);
			filterChain.doFilter(request, response);
		}
		

	}

}
