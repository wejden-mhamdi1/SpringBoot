package tn.esprit.spring.Entities;




import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor 
@ToString 
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Rating implements Serializable  {
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	 long id;
	String note;
	@Temporal(TemporalType.DATE)
	Date Date;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonIgnore
	 User user;
	
	 @ManyToOne(cascade = CascadeType.ALL)
	 @JoinColumn(name="offer_id")
		@JsonIgnore
		 Offer Offer;
	 
}

