package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.Entities.Evaluate;

@Repository
public interface EvaluateRepository extends JpaRepository<Evaluate, Integer> {
	@Query("select u from Evaluate u where u.note=?1")
	public List<Evaluate> findByNote(float note);

}
