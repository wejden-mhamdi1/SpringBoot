package tn.esprit.spring.Services;

import com.stripe.exception.StripeException;


import com.stripe.model.PaymentIntent;

import tn.esprit.spring.Entities.Abonnement;

public interface PaymentService {
	public PaymentIntent paymentIntent(int id_user,Abonnement abonnement) throws StripeException;
	public PaymentIntent confirm(String id) throws StripeException;
	 public PaymentIntent cancel(String id) throws StripeException;
	 public void creatCustmer (int id)throws StripeException;
	 public void addCardCustomer () throws StripeException;
	 public void chargeCustomer()throws StripeException;
	 
}
