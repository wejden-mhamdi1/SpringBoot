package tn.esprit.spring.Services;
import tn.esprit.spring.Entities.Abonnement;
import tn.esprit.spring.Entities.Badge;
import tn.esprit.spring.Entities.Evaluate;
import tn.esprit.spring.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.repository.AbonnementRepository;
import tn.esprit.spring.repository.EvaluateRepository;
import tn.esprit.spring.repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceIMPL implements UserService{

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    EvaluateRepository rep;
    @Autowired
    AbonnementRepository repAbon;



    @Override
    public List<User> sortMethode(int id_client) {
        List<User> users = userRepository.findAll();
        User user=userRepository.findById(id_client).get();
		Badge gold=user.getTYPEbadget().GOLD; //1/3
		Badge selver=user.getTYPEbadget().SELVER;//1/3..2/3
		Badge simple=user.getTYPEbadget().SIMPLE;//2/3..
		

        Comparator<User> cmp = Comparator.comparing(User::getMoyenne).reversed();
         users.sort(cmp);
         
       int  length=users.size();
        	 if(length<=(length/3)) {
        		 user.setPoint(100);
        		 user.setTYPEbadget(gold);
     			userRepository.save(user);	
        	 }
        	 if((length >= length/3) && (length <= (2*length)/3))  {
        		 user.setPoint(50);
        		 user.setTYPEbadget(selver);
     			userRepository.save(user);	
        	 }
        	 if(length >=(2*length)/3) {
        		 user.setPoint(20);
        		 user.setTYPEbadget(simple);
     			userRepository.save(user);	
        	 }
        	 
         

         return users;
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    //etape1: parcours listeEV somme des notes
    @Override
    public double moyenne(List<Evaluate> TABLEev,int id){
    	   
    	   double sommeNote=0;
    	   
    	   
    	   User IDuser = findUserById(id); //idUSER
  
           //calcul des notes
           List<Evaluate> listeEV = TABLEev.stream()
                   .filter(e -> e.getVoteTo() == id).collect(Collectors.toList());
          for (Evaluate i : listeEV){
        	  sommeNote=i.getNote()+sommeNote;
        	  	 
          }
         
          IDuser.setMoyenne(sommeNote);
          userRepository.save(IDuser);
           return IDuser.getMoyenne();
      
       }

	@Override
	public void PointUser(int id_client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User findUserById(int id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).orElse(null);
	}
	
	@Override
	public Abonnement addAbonnement(int id, Abonnement abonnement) {
		//Abonnement p= new Abonnement();
		abonnement.setUser(findUserById(id));
		
		return repAbon.save(abonnement);
		
	}

    
         
         
    
    //public List<User> find(Departement dep){
    	//return userRepository.find(dep);
    //}


}
