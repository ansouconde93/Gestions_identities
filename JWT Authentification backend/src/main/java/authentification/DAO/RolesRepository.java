package authentification.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import authentification.entities.Roles;

public interface RolesRepository extends JpaRepository<Roles, Long>{
	public Roles findByNomrole(String roleNom);	

}
