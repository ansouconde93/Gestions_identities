package authentification.Controlleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import authentification.Services.BusinessServiceImplementation;
import authentification.entities.Applications;
import authentification.entities.Roles;
import authentification.entities.Utilisateur;
import authentification.entities.UtilisateursRolesApplications;

@RestController
@CrossOrigin("*")
public class BusinessControlleur {
	@Autowired
	private BusinessServiceImplementation businessServiceImplementation;
	
	@GetMapping("/utilisateurs")
	public List<Utilisateur> getAllUtilisateurs(){
		return businessServiceImplementation.getAllUtilisateurs();
	}
	
	@PostMapping("/enregistrerutilisateur")
	public Utilisateur saveUtilisateur(@RequestBody Utilisateur utilisateur) {
		return businessServiceImplementation.saveUtilisateur(utilisateur);
	}
	
	@GetMapping("/roles")
	public List<Roles> getAllRoles(){
		return businessServiceImplementation.getAllRoles();
	}
	
	@PostMapping("/role")
	public Roles saveRole(@RequestBody Roles role) {
		return businessServiceImplementation.saveRoles(role);
	}
	
	@GetMapping("/applications")
	public List<Applications> getAllApplications(){
		return businessServiceImplementation.getAllApplications();
	}
		
	@PostMapping("/application")
	
	public Applications saveApplication(@RequestBody Applications application) {
		return businessServiceImplementation.saveApplication(application);
	}

	@PostMapping("/utilisateurroleapplication")
	public UtilisateursRolesApplications saveUtilisateursRolesApplications(UtilisateursRolesApplications utilisateursRolesApplications) {
		return businessServiceImplementation.saveUtilisateursRolesApplications(utilisateursRolesApplications);
	}

	@GetMapping("/utilisateursrolesapplications")
	public List<UtilisateursRolesApplications> getAllUtilisateursRolesApplications() {
		return businessServiceImplementation.getAllUtilisateursRolesApplications();
	}

	@GetMapping("/utilisateursroles")
	public List<String> getAllUtilisateursRoles() {	
		return businessServiceImplementation.getRolesOfUser(businessServiceImplementation.findUtilisateurByEmail("fst@gmail.com"));
	}
}
