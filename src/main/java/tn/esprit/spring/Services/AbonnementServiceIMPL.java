package tn.esprit.spring.Services;

import java.time.LocalDateTime;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import tn.esprit.spring.Entities.Abonnement;
import tn.esprit.spring.Entities.TypeAbonnement;
import tn.esprit.spring.Entities.User;
import tn.esprit.spring.repository.AbonnementRepository;
import tn.esprit.spring.repository.UserRepository;
@Service
public class AbonnementServiceIMPL implements AbonnementService {

	@Autowired
	AbonnementRepository AbonnementRepository;
	@Autowired
	UserRepository userREP;
	@Override
	public List<Abonnement> remiseAbonnement(int id )
	{
		//le temps restant par milleseconde pour labonnement 
		LocalDateTime localDate=  LocalDateTime.now();
		Abonnement ab=AbonnementRepository.findById(id).orElse(null);
		Date datefin= ab.getDateFin();
		//Boolean status= ab.getStatus();
		Date dateActuelle =Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
		float rest=datefin.getTime()-dateActuelle.getTime();
		float test=(rest / (1000*60*60*24));
		//si le temps d'aboonement encore valable donc prix prend le remise 
		List<Abonnement> typeAbonn= AbonnementRepository.findByType(ab.getTypeAbonnement());
		List<Abonnement>liste=new ArrayList<>();
		if(test<=0){
			AbonnementRepository.deleteById(id);
			//status=false;
			System.out.print("il faut payé ton abonnement de nouveau  ");
			return typeAbonn;
			}
			else{
			for(Abonnement typeab:typeAbonn) {
			float prix=typeab.getPrix();
			int pourcentage= typeab.getPourcentage();
			float p=prix-((prix*pourcentage)/100);
			typeab.setPrix(p);
			liste.add(typeab);
			}
		return liste;
	}
}
	//pour selecter tous les abonement selon type d'abonnement
	@Override
	public List<Abonnement> findByType(TypeAbonnement typeAbonnement) {
		return AbonnementRepository.findByType(typeAbonnement);
	}
	//Méthode pour ajouter le pourcentage de remise
	@Override
	public Abonnement addPourcentage(int id, Abonnement porcentage) {
		
		User us= userREP.findById(id).orElse(null);
		porcentage.setUser(us);
		return AbonnementRepository.save(porcentage);
	}
	  @Override
	    public Abonnement addAbonnement(Abonnement abonnement) {
	        return AbonnementRepository.save(abonnement);
	    }
	 
	  
	public List<Abonnement> Getall() {

		
			return AbonnementRepository.findAll();
		
	}
	///////////////////
	public void sendEmailWithAttachment(Abonnement abonnement) throws MailException, MessagingException {
		
		 JavaMailSender javaMailSender = null;

		MimeMessage message = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(abonnement.getUser().getEmail());
		helper.setSubject("Testing Mail API with Attachment");
		helper.setText("Please find the attached document below.");

		
		ClassPathResource classPathResource = new ClassPathResource("Attachment.pdf");
		helper.addAttachment(classPathResource.getFilename(), classPathResource);

		javaMailSender.send(message);
	}
	
	///////////////////////////////////////////////
	public  Optional<Abonnement> findAbonnementById(int id) {

	     return AbonnementRepository.findById(id);
	 }
	

}
