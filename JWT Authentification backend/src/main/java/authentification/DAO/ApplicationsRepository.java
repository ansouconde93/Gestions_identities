package authentification.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import authentification.entities.Applications;

public interface ApplicationsRepository extends JpaRepository<Applications, Long>{

	public Applications findByUrlapplication(String url);

}
