package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.Entities.Abonnement;
import tn.esprit.spring.Entities.TypeAbonnement;
public interface AbonnementRepository extends JpaRepository<Abonnement, Integer>{
	@Query("select u from Abonnement u where u.TypeAbonnement=?1")
	public List<Abonnement> findByType(TypeAbonnement typeAbonnement);

	//List<Abonnement> findByType(TypeAbonnement typeAbonnement);



}
