package tn.esprit.spring.Entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import lombok.Builder;
import java.io.Serializable;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor 
@ToString 
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Abonnement implements Serializable{
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	 int id;
	float prix;
	int pourcentage;
	 Boolean status;
	
	 @Temporal(TemporalType.DATE)
		Date dateDebut;
	 @Temporal(TemporalType.DATE)
		Date dateFin;
	 @Enumerated(EnumType.STRING)
		TypeAbonnement TypeAbonnement;
	 
	 //relation
	 @ManyToOne(cascade = CascadeType.ALL)
	 @JoinColumn(name="user_id")
		@JsonIgnore
		 User user;

	
	

}

