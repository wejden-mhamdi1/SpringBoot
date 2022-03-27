package tn.esprit.spring.Services;
import tn.esprit.spring.Entities.Abonnement;
import tn.esprit.spring.Entities.Evaluate;
import tn.esprit.spring.Entities.User;
import java.util.List;
public interface UserService {

    List<User> sortMethode(int id_client); // les badges des 1ere selon leurs moyenne
    User addUser(User user);
    User findUserById(int id);
   double moyenne (List<Evaluate> listEvaluations, int id); // moyenne de chaque employe selon les votes 
   // double moyenne(Evaluate TABLEev,int id);
	void PointUser(int id_client);
	Abonnement addAbonnement(int id,Abonnement abonnement);// employee peut ajouter un abonnement


}
