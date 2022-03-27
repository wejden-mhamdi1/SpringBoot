package tn.esprit.spring.Services;

public interface TwillioSMService {

	void sendSms(String to, String from, String body);

	void makeCall(String from, String to);

}
