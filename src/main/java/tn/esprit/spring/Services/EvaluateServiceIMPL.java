package tn.esprit.spring.Services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.Entities.Evaluate;
import tn.esprit.spring.Entities.User;
import tn.esprit.spring.repository.EvaluateRepository;
import tn.esprit.spring.repository.UserRepository;

@Service
public class EvaluateServiceIMPL implements EvaluateService {
	@Autowired
	EvaluateRepository evaluaterepo;
	@Autowired
	UserService userService;
	

	
	@Override
	public List<Evaluate> Getallvote() {
		return evaluaterepo.findAll();
	}

	@Override
	public double addvote(int id, Evaluate evaluate) {
		double moyenne;
		
		
		  User user = userService.findUserById(id); //idUSER
   	   User IDvotedTo = userService.findUserById(evaluate.getVoteTo());//idVoteTO
		 
		
		  
		
			if(IDvotedTo.getTYPEdepartement().equals(user.getTYPEdepartement())){
		
      		moyenne = evaluate.getNote() +( evaluate.getNote()*80)/100;	
      		
      	}
      	else {
      	 moyenne = evaluate.getNote()+ (evaluate.getNote()*30)/100;	
      		  
      	} 
		evaluate.setNote(moyenne);
		
		evaluate.setUser(user);
		evaluaterepo.save(evaluate);
		
		
		return moyenne;
	}
		
		

	@Override
	public Evaluate findEVALUATEById(int id) {
		// TODO Auto-generated method stub
		return evaluaterepo.findById(id).get();
	}
	


}



