package tn.esprit.spring.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.Entities.Evaluate;
import tn.esprit.spring.Services.EvaluateService;
import java.util.List;

@RestController
public class EvaluateController {

    @Autowired
    EvaluateService evaluateService;

    @GetMapping("Evaluate/getAll")
    public List<Evaluate> getAllEvaluation(){
        return evaluateService.Getallvote();
    }

    @PostMapping("AddTonAvisNOTE/{id}")
    public double addEvaluation(@RequestBody Evaluate evaluate, @PathVariable("id") int id){
        return evaluateService.addvote(id,evaluate);
    }

   
}
