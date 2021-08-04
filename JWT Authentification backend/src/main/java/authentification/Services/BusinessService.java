package authentification.Services;

import java.util.List;

import authentification.entities.Applications;
import authentification.entities.Roles;
import authentification.entities.Utilisateur;
import authentification.entities.UtilisateursRolesApplications;

public interface BusinessService {
	public Utilisateur saveUtilisateur(Utilisateur utilisateur);
	public Roles saveRoles(Roles roles);
	public Applications saveApplication(Applications applications);
	public List<Applications> getAllApplications();
	public List<Utilisateur> getAllUtilisateurs();
	public List<Roles> getAllRoles();
	public Applications findByUrlapplication(String url);
	public Roles findByNomrole(String roleNom);
	public List<UtilisateursRolesApplications> getAllUtilisateursRolesApplications();
	public UtilisateursRolesApplications saveUtilisateursRolesApplications(UtilisateursRolesApplications utilisateursRolesApplications);
	
	public List<String>getRolesOfUser(Utilisateur utilisateur);
	public Utilisateur findUtilisateurByEmail(String email);
}
