package authentification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import authentification.Services.BusinessService;
import authentification.entities.Applications;
import authentification.entities.Roles;
import authentification.entities.Utilisateur;
import authentification.entities.UtilisateursRolesApplications;
import authentification.entities.UtilisateursRolesApplicationsKeyID;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class JwtAuthentificationApplication {
	
	public static void main(String[] args) {
		ApplicationContext applContext = SpringApplication.run(JwtAuthentificationApplication.class, args);
	
		BusinessService businessService = applContext.getBean(BusinessService.class);
		
		Utilisateur u1 = new Utilisateur();
		Utilisateur u2 = new Utilisateur();
		Utilisateur u3 = new Utilisateur();		
		u1.setId(null);u1.setNom("fst");u1.setPrenom("paul");u1.setEmail("fst@gmail.com");u1.setMotpasse("1234");		
		u2.setId(null);u2.setNom("tunis");u2.setPrenom("condé");u2.setEmail("tunis@gmail.com");u2.setMotpasse("1234");	
		u3.setId(null);u3.setNom("bardo");u3.setPrenom("bardo 2");u3.setEmail("if4@gmail.com");u3.setMotpasse("1234");	
				
		Roles r1 =new Roles();
		Roles r2 =new Roles();
		Roles r3 =new Roles();		
		r1.setId(null);r1.setNomrole("admin");
		r2.setId(null);r2.setNomrole("user");
		r3.setId(null);r3.setNomrole("manager");
		
		Applications a1 = new Applications();
		Applications a2 = new Applications();
		Applications a3 = new Applications();
		Applications a4 = new Applications();
		Applications a5 = new Applications();
		Applications a6 = new Applications();
		Applications a7 = new Applications();		
		a1.setIdApplication(null);
		a1.setUrlapplication("/bonjour");
		a2.setIdApplication(null);
		a2.setUrlapplication("/bonne/matinee");
		a3.setIdApplication(null);
		a3.setUrlapplication("/bon/apres/midi");
		a4.setIdApplication(null);
		a4.setUrlapplication("/bonsoir");
		a5.setIdApplication(null);
		a5.setUrlapplication("/bonne/soiree");
		a6.setIdApplication(null);
		a6.setUrlapplication("/bonne/nuit");
		a7.setIdApplication(null);
		a7.setUrlapplication("/a/demain");

		u1=businessService.saveUtilisateur(u1);
		u2=businessService.saveUtilisateur(u2);
		u3=businessService.saveUtilisateur(u3);
		
		a1=businessService.saveApplication(a1);
		a2=businessService.saveApplication(a2);
		a3=businessService.saveApplication(a3);
		a4=businessService.saveApplication(a4);
		a5=businessService.saveApplication(a5);
		a6=businessService.saveApplication(a6);
		a7=businessService.saveApplication(a7);
		
		r1=businessService.saveRoles(r1);
		r2=businessService.saveRoles(r2);
		r3=businessService.saveRoles(r3);
		
		UtilisateursRolesApplications ura1 = new UtilisateursRolesApplications();
		UtilisateursRolesApplications ura2 = new UtilisateursRolesApplications();
		UtilisateursRolesApplications ura3 = new UtilisateursRolesApplications();
		
		UtilisateursRolesApplicationsKeyID applicationsKeyID1 = 
				new UtilisateursRolesApplicationsKeyID(u1.getId(),r2.getId(),a5.getIdApplication());		
		UtilisateursRolesApplicationsKeyID applicationsKeyID2 = 
				new UtilisateursRolesApplicationsKeyID(u1.getId(),r1.getId(),a2.getIdApplication());
		UtilisateursRolesApplicationsKeyID applicationsKeyID3 = 
				new UtilisateursRolesApplicationsKeyID(u2.getId(),r2.getId(),a4.getIdApplication());
		
		ura1.setId(applicationsKeyID1); ura1.getUtilisateur().add(u1); ura1.getApplication().add(a5); ura1.getRole().add(r2);
		ura2.setId(applicationsKeyID2); ura2.getUtilisateur().add(u1); ura2.getApplication().add(a2); ura2.getRole().add(r1);
		ura3.setId(applicationsKeyID3); ura3.getUtilisateur().add(u2); ura3.getApplication().add(a4); ura3.getRole().add(r2);
		
		List<UtilisateursRolesApplications> urApp = new ArrayList<UtilisateursRolesApplications>();
		urApp.add(ura1); urApp.add(ura2);
		//u1.setUtilisateursRolesApplications(urApp);
		//u2.getUtilisateursRolesApplications().add(ura3);
				
		businessService.saveUtilisateursRolesApplications(ura1);
		businessService.saveUtilisateursRolesApplications(ura2);
		businessService.saveUtilisateursRolesApplications(ura3);
	}	

	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}

}
