package authentification.Securites;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import authentification.entities.Utilisateur;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/*
 * Filtre d'extraction de l'utilisateurs
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private AuthenticationManager authenticationManager;
	
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

	/*
	 * Methode d'extraction de l'utilisateur
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		Utilisateur u = null;
		try {
			u = new ObjectMapper().readValue(request.getInputStream(), Utilisateur.class);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		return authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(u.getEmail(), u.getMotpasse()));
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
			User springUser =(User) authResult.getPrincipal();
			String jwtToken = Jwts.builder()
								.setSubject(springUser.getUsername())
								.setExpiration(new Date(System.currentTimeMillis()+SecurityConstante.EXPIRATION_TIME))
								.signWith(SignatureAlgorithm.HS256, SecurityConstante.SECRET)
								.claim("roles", springUser.getAuthorities())
								.claim("nom", "paul")
								.claim("prenom", "pière")
								.claim("url_callback", "/login/callback")
								.compact();
			response.addHeader(SecurityConstante.HEADER_STRING, SecurityConstante.TOKEN_PREFIX+jwtToken);
	}

}
