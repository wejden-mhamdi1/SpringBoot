package tn.esprit.spring.Services;

import java.util.List;

import tn.esprit.spring.Entities.Evaluate;

public interface EvaluateService {


    List<Evaluate> Getallvote();
    double addvote(int id, Evaluate note);//un emploi peut voter a les autre
	Evaluate findEVALUATEById(int id);
    

}
