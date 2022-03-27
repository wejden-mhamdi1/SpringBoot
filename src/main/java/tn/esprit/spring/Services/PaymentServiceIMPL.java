package tn.esprit.spring.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Token;
import tn.esprit.spring.Payment;
import tn.esprit.spring.Entities.Abonnement; //entities.Order
import tn.esprit.spring.Payment.Currency;


@Service
public class PaymentServiceIMPL implements PaymentService {
	

	@Value("${stripe.key.secret}")
    String secretKey;
	@Autowired
	UserService userService;
	 public PaymentIntent paymentIntent(int id_user, Abonnement abonnement) throws StripeException {
	        Stripe.apiKey = secretKey;
	        Customer a= Customer.retrieve("cus_LMDKEntmUgUfG7"); //
	        
	        List<String> paymentMethodTypes = new ArrayList<String>();
	        paymentMethodTypes.add("card");
	        Map<String, Object> params = new HashMap<>();
	        Abonnement  abonn=userService.addAbonnement(id_user, abonnement );
	    	Payment p= new Payment();
	    	p.setAmount(abonn.getPrix());// 2560.0255
		    //p.setAmount(abonn.getPrix());
		    p.setDescription(abonn.getUser().getFirstName());
	    	p.setCurrency(Currency.eur);
	        params.put("amount",(int) Math.round(p.getAmount()));//
	        params.put("currency",p.getCurrency());
	       // params.put("description",p.getDescription());
	        params.put("payment_method_types", paymentMethodTypes);
	        params.put("customer", "cus_LMDKEntmUgUfG7");
	        return PaymentIntent.create(params);
	    }
	
	 @Override
	 public PaymentIntent confirm(String id) throws StripeException {
	        Stripe.apiKey = secretKey;
	        Customer a= Customer.retrieve("cus_LMDKEntmUgUfG7");
	        PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
	        Map<String , Object> chargeParm= new HashMap<String , Object>();
			chargeParm.put("amount", paymentIntent.getAmount());
			chargeParm.put("currency", "usd");
			chargeParm.put("customer", a.getId());
			
			Charge.create(chargeParm);
	       // Map<String, Object> params = new HashMap<>();
	       // params.put("payment_method", "pm_card_visa");
	       // params.put("customer",a.getId());
	       // paymentIntent.confirm(params);
	        return paymentIntent;
	  
	        }
	 @Override
	    public PaymentIntent cancel(String id) throws StripeException {
	        Stripe.apiKey = secretKey;
	        PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
	        paymentIntent.cancel();
	        return paymentIntent;
	    }
	 
	 
	 
	 
	 
	 
	 //testinehom manuel


	@Override
	public void addCardCustomer() throws StripeException {
		  Stripe.apiKey = secretKey;
		Customer a= Customer.retrieve("cus_LMDKEntmUgUfG7");
		Map<String , Object> cardParam= new HashMap<String , Object>();
		cardParam.put("number", "4242424242424242");
		cardParam.put("exp_month", "11");
		cardParam.put("exp_year", "2022");
		cardParam.put("cvc", 123);
		
		Map<String , Object> tokenParm= new HashMap<String , Object>();
		tokenParm.put("card", cardParam);
		Token token= Token.create(tokenParm);
		Map<String , Object> source= new HashMap<String , Object>();
		source.put("source", token.getId());
		a.getSources().create(source);
	}
	 //on va cree custmer avec email=w@test1
	@Override
	public void creatCustmer(int id) throws StripeException {
		Stripe.apiKey = secretKey;
		Map<String, Object> params = new HashMap<>();
		params.put("email", "wejden.mhamdi@esprit.com");
		Customer customer = Customer.create(params);
		System.out.println(customer.getId());
		
	}
	@Override
	public void chargeCustomer() throws StripeException {
		Stripe.apiKey = secretKey;
		Customer a= Customer.retrieve("cus_LMDKEntmUgUfG7");
		Map<String , Object> chargeParm= new HashMap<String , Object>();
		chargeParm.put("amount", "500");
		chargeParm.put("currency", "usd");
		chargeParm.put("customer", a.getId());
		
		Charge.create(chargeParm);
		
	}

	
	
	
	


}