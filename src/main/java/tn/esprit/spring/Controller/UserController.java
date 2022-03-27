package tn.esprit.spring.Controller;
import org.springframework.web.bind.annotation.*;

import com.stripe.exception.StripeException;

import tn.esprit.spring.Payment;
import tn.esprit.spring.Payment.Currency;
import tn.esprit.spring.Entities.Abonnement;
import tn.esprit.spring.Entities.Evaluate;

import tn.esprit.spring.Entities.User;
import tn.esprit.spring.Services.PaymentService;
import tn.esprit.spring.Services.UserService;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
public class UserController {
	@Autowired
    private PaymentService paymentService;
    @Autowired
    private UserService userService;
   


    @GetMapping("ListTri/{id}")
    public List<User> users(@PathVariable int id){
        return userService.sortMethode(id);
    }


    @GetMapping("User/{id}")
    public User findUserById(@PathVariable int id){
        return userService.findUserById(id);
    }

    @PostMapping("User")
    public User adduser(@RequestBody User user){

        return userService.addUser(user);
    }

    @PutMapping ("User/moyenne/{id}")
    public double userMoyenne(@RequestBody List<Evaluate> note, @PathVariable int id){
        return userService.moyenne(note,id);
    }
    @PostMapping("abonnement/{id}")
    public Abonnement addabonnement(@RequestBody Abonnement abonnement,@PathVariable int id ){

        return userService.addAbonnement(id,abonnement);
    }
 
   // @PostMapping("PayeAbonnement/{id}")
   // public ResponseEntity<Abonnement> addAbonnement(@PathVariable("id")int id) throws StripeException
   // {
   // 	Abonnement abonn=userService.addAbonnement(id);
    //	Payment p= new Payment();
    	//p.setAmount(abonn.getPrix());
    	//p.setDescription(abonn.getUser().getFirstName());
    	//p.setCurrency(Currency.eur);
    	//paymentService.confirm("pi_3K2YQOAyugxFsHxL3xnjBVkS");
    	//return  ;//new ResponseEntity<>(abonn,HttpStatus.OK);
    	
   // }


}
