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
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.Builder;
import java.io.Serializable;
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
public class Offer   implements Serializable {
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	 Long id_offre;
	float Price;
	 @Temporal(TemporalType.DATE)
		Date Time_limit;
	
	String type;
	 @Enumerated(EnumType.STRING)
		OffreType Typeoffre;
//relation
	
	 @ManyToOne(cascade = CascadeType.ALL)
	 @JoinColumn(name="coll_id")
		@JsonIgnore
		 Collaboration Collaboration;
	
	 @OneToMany(mappedBy="Offer")
	 List<Rating> Ratings;
}
