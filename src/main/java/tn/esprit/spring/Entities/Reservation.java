package tn.esprit.spring.Entities;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.Builder;
import java.io.Serializable;
import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor 
@ToString 
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reservation   implements Serializable {
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	 Long id_reservation;
	 @Temporal(TemporalType.DATE)
		Date date;
		@ManyToOne
		@JoinColumn(name="user_id")
		@JsonIgnore
		 User user;
		@ManyToOne
		@JoinColumn(name="offer_id")
		@JsonIgnore
		 Offer offer;
}
