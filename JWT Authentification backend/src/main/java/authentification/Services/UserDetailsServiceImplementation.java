package authentification.Services;

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

import authentification.entities.Utilisateur;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService{
	
	@Autowired
	BusinessServiceImplementation businessService;
	@Override
	
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Utilisateur utilisateur = businessService.findUtilisateurByEmail(email);
		if(utilisateur == null) throw  new UsernameNotFoundException("Utilisateur n'existe pas");
		
		//Preparer les roles de l'utilisateur sous forme de collection d'objets compressible par spring security
		Collection<GrantedAuthority> authorisations = new ArrayList<GrantedAuthority>();
		businessService.getRolesOfUser(utilisateur).forEach(r->{			
			authorisations.add(new SimpleGrantedAuthority(r));
		});
		return new User(utilisateur.getEmail(),utilisateur.getMotpasse(),authorisations);
	}

}
