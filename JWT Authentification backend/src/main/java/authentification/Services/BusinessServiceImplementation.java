package authentification.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import authentification.DAO.ApplicationsRepository;
import authentification.DAO.RolesRepository;
import authentification.DAO.UtilisateurRepository;
import authentification.DAO.UtilisateursRolesApplicationsRepository;
import authentification.entities.Applications;
import authentification.entities.Roles;
import authentification.entities.Utilisateur;
import authentification.entities.UtilisateursRolesApplications;

@Service
public class BusinessServiceImplementation implements BusinessService{
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private RolesRepository rolesRepository;
	@Autowired
	private ApplicationsRepository applicationsRepository;
	@Autowired
	private UtilisateursRolesApplicationsRepository utilisateursRolesApplicationsRepository;

	@Override
	public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
		
		String passwordEncoder = bCryptPasswordEncoder.encode(utilisateur.getMotpasse());
		utilisateur.setMotpasse(passwordEncoder);
		return utilisateurRepository.save(utilisateur);
	}

	@Override
	public Roles saveRoles(Roles roles) {
		return rolesRepository.save(roles); 
	}

	@Override
	public Applications saveApplication(Applications applications) {
		return applicationsRepository.save(applications);
	}

	@Override
	public Utilisateur findUtilisateurByEmail(String email) {
		return utilisateurRepository.findByEmail(email);
	}
	
	@Override
	public List<Applications> getAllApplications(){
		return applicationsRepository.findAll();
	}

	@Override
	public List<Utilisateur> getAllUtilisateurs() {
		return utilisateurRepository.findAll();
	}

	@Override
	public List<Roles> getAllRoles() {
		return rolesRepository.findAll();
	}

	@Override
	public Applications findByUrlapplication(String url) {
		return applicationsRepository.findByUrlapplication(url);
	}

	@Override
	public Roles findByNomrole(String roleNom) {
		return rolesRepository.findByNomrole(roleNom);
	}

	@Override
	public UtilisateursRolesApplications saveUtilisateursRolesApplications(UtilisateursRolesApplications utilisateursRolesApplications) {
		return utilisateursRolesApplicationsRepository.save(utilisateursRolesApplications);
	}

	@Override
	public List<UtilisateursRolesApplications> getAllUtilisateursRolesApplications() {
		return utilisateursRolesApplicationsRepository.findAll();
	}

	@Override
	public List<String> getRolesOfUser(Utilisateur utilisateur) {
		List<String> mesRoles = new ArrayList<String>();
		List<Roles> roles = rolesRepository.findAll();
		utilisateur.getUtilisateursRolesApplications().forEach(ura->{
		
			roles.forEach(r->{
				if(ura.getId().getIdRole() == r.getId())
					if(!estContenu(mesRoles,r.getNomrole()))
						mesRoles.add(r.getNomrole());
			});
		});
		return mesRoles;
	}
	
	private boolean estContenu(List<String> listeRole, String role) {
		for(int i=0; i < listeRole.size(); i++) {
			if(listeRole.get(i).equals(role))
				return true;
		}
		return false;
	}
}
