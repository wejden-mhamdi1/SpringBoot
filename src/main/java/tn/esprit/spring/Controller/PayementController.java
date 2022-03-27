package tn.esprit.spring.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;

import tn.esprit.spring.Entities.Abonnement;
import tn.esprit.spring.Entities.User;
import tn.esprit.spring.Services.PaymentService;


	@Controller
	@RequestMapping("/stripe")
	public class PayementController {
		@Autowired
	    PaymentService paymentService;
		//hedhi tajouti client wejden.mhamdi
		/*@PostMapping("/createCustomer/{id}")
		public void createCustomer(@PathVariable("id")  int id) throws StripeException {
	
			paymentService.creatCustmer(id);
		}
		//hedhi tajouti mode de paiement visa
		@PostMapping("/addCardCustomer")
		public void addCardCustomer() throws StripeException {
			paymentService.addCardCustomer();
		}
		//hedhi khales beha 5euro
		@PostMapping("/chargeCustomer")
		public void chargeCustomer() throws StripeException {
			paymentService.chargeCustomer();
		}
		*/
		
	@PostMapping("/paymentintent/{id}")
	    public ResponseEntity<String> payment(@PathVariable("id") int id,@RequestBody Abonnement abonnement) throws StripeException {
	        PaymentIntent paymentIntent = paymentService.paymentIntent(id,abonnement);
	        String paymentStr = paymentIntent.toJson();
	        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
	    }
	

	    @PostMapping("/confirm/{id}")
	    public ResponseEntity<String> confirm(@PathVariable("id") String id) throws StripeException {
	        PaymentIntent paymentIntent = paymentService.confirm(id);
	        String paymentStr = paymentIntent.toJson();
	        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
	    }

	    @PostMapping("/cancel/{id}")
	    public ResponseEntity<String> cancel(@PathVariable("id") String id) throws StripeException {
	        PaymentIntent paymentIntent = paymentService.cancel(id);
	        String paymentStr = paymentIntent.toJson();
	        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
	    }

	/*//teba3 video
	 * 	@Value("${stripe.key.secret}")
	String secretKey;
		  @RequestMapping("/")
		    public String index() {
		    	return "index"; //+secretKey
		    }
		  //teba3 video
		  @RequestMapping("/createCustomer")
		    public String createCustomer() {
		    	return "create"; //+secretKey
		    }
		  //teba3 video

			@RequestMapping("/addCustomer")
			public String addCustomer(CustomerDataStripe customerData) throws StripeException {
				
				Stripe.apiKey = secretKey;
				Map<String, Object> params = new HashMap<>();
				params.put("name", customerData.getName());
				params.put("email", customerData.getEmail());
				Customer customer = Customer.create(params);
				
				return "success";
			}
			 //ki ydakhel mail et name yetsajel fl compte stripe teba3 video
		    @RequestMapping("/createCustomer1")
			public CustomerDataStripe createCustomer(@RequestBody CustomerDataStripe data) throws StripeException  {//
		        Stripe.apiKey = secretKey;
				Map<String, Object> params = new HashMap<>();
				params.put("name", data.getName());
				params.put("email", data.getEmail());
				Customer customer = Customer.create(params);
				data.setCustomerId(customer.getId());
				return data;
		
		    //njarbou beha l cle todh.her ou pas
				}*/
		 
			
		  
		  
		    
		 

		}
	

