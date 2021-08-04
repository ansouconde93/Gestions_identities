package authentification.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class UtilisateursRolesApplicationsKeyID implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idUtilisateur;
	private Long idRole;
	private Long idApplication;

}
