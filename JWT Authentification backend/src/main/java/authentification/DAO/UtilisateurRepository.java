package authentification.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import authentification.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{
	public Utilisateur findByEmail(String email);
}
