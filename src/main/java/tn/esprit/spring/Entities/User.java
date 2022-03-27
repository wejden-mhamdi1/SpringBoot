package tn.esprit.spring.Entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor 
@ToString
public class User {
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	 int id;
	String FirstName;
	String Password;
	String email;
	String role;
	String adresse;
	String LastName;
	String civilite;
	int num;
	double moyenne;
	int point;
	 @Enumerated(EnumType.STRING)
		Departement TYPEdepartement;
	 @Enumerated(EnumType.STRING)
		Badge TYPEbadget;
	 //relation



	@OneToMany(mappedBy="user")
	 List<Evaluate> Evaluates;
	  


	public User(String firstName, String password, String email, String role, String adresse, String lastName, String civilite, int num, List<Evaluate> evaluates) {
		this.id = id;
		FirstName = firstName;
		Password = password;
		this.email = email;
		this.role = role;
		this.adresse = adresse;
		LastName = lastName;
		this.civilite = civilite;
		this.num = num;
		Evaluates = evaluates;
	}








}
