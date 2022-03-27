package tn.esprit.spring.Services;

import java.util.List;
import java.util.Optional;

import tn.esprit.spring.Entities.Abonnement;
import tn.esprit.spring.Entities.TypeAbonnement;
public interface AbonnementService {

	public List<Abonnement> remiseAbonnement(int id ); //si la date est expiré l'abonnement va supprimer sinon soldé
	public List<Abonnement> findByType(TypeAbonnement typeAbonnement);
	public Abonnement addPourcentage(int id, Abonnement pro); //l'admin va ajouter la pourcentage de solde
	public Abonnement addAbonnement(Abonnement abonnement); //l'admin va ajouter un abonnement
	 List<Abonnement> Getall();
	   Optional<Abonnement> findAbonnementById(int id);

}
