package authentification.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import authentification.entities.UtilisateursRolesApplications;
import authentification.entities.UtilisateursRolesApplicationsKeyID;

public interface UtilisateursRolesApplicationsRepository extends JpaRepository<UtilisateursRolesApplications, UtilisateursRolesApplicationsKeyID>{
	
}
