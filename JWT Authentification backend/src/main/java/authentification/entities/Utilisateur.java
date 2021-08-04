package authentification.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class Utilisateur implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String prenom;
	@Column(unique = true)
	private String email;
	private String motpasse;
	private boolean actif = false;
	
	//@JsonManagedReference
	@ManyToMany(mappedBy = "utilisateur",fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
	private List<UtilisateursRolesApplications> utilisateursRolesApplications= new ArrayList<UtilisateursRolesApplications>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@JsonIgnore
	public String getMotpasse() {
		return motpasse;
	}
	@JsonSetter
	public void setMotpasse(String motpasse) {
		this.motpasse = motpasse;
	}
	
	public boolean isActif() {
		return actif;
	}
	public void setActif(boolean actif) {
		this.actif = actif;
	}
	public List<UtilisateursRolesApplications> getUtilisateursRolesApplications() {
		return utilisateursRolesApplications;
	}
	public void setUtilisateursRolesApplications(List<UtilisateursRolesApplications> utilisateursRolesApplications) {
		this.utilisateursRolesApplications = utilisateursRolesApplications;
	}
	
	
}
