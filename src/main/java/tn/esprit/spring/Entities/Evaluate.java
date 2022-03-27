package tn.esprit.spring.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@NoArgsConstructor
@Data
@Getter
public class Evaluate  {

	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private int id;
	private double note;
	private String description;
	private int voteTo;
	private int voter;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user_id")

	private User user;


	public Evaluate(int id, double note, String description, int voteTo, int voter,User user) {
		this.id = id;
		this.note = note;
		this.description = description;
		this.voteTo = voteTo;
		this.voter = voter;
		this.user = user;
	
	}

}
