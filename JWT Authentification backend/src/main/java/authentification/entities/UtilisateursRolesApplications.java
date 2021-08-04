package authentification.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class UtilisateursRolesApplications implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private UtilisateursRolesApplicationsKeyID id;
	
	//@JsonBackReference
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
	private List<Utilisateur> utilisateur = new ArrayList<Utilisateur>();
	
	//@JsonBackReference
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
	private List<Roles> role = new ArrayList<Roles>();

	//@JsonBackReference
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
	private List<Applications> application = new ArrayList<Applications>();

}
