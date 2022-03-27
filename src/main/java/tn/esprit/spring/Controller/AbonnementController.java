package tn.esprit.spring.Controller;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.Entities.Abonnement;
import tn.esprit.spring.Entities.User;
import tn.esprit.spring.Services.AbonnementService;

import tn.esprit.spring.Services.PdfService;
import tn.esprit.spring.Services.TwillioSMService;
import tn.esprit.spring.repository.AbonnementRepository;
import tn.esprit.spring.repository.UserRepository;
////
import java.util.Optional;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
public class AbonnementController {
	@Autowired
	AbonnementRepository abonnrep;
	@Autowired
	UserRepository userrep;
	@Autowired
	private JavaMailSender javaMailSender;
	
		@Autowired
		AbonnementService abonnementService;
		 @Autowired
	    	TwillioSMService twillioService;
		 SimpleMailMessage msg = new SimpleMailMessage();
		  private PdfService pdfService;
		  @Autowired
		    public AbonnementController(AbonnementService abonnementService, PdfService pdfService) {//
		        this.abonnementService = abonnementService;
		       this.pdfService = pdfService;
		    }
	
		 @GetMapping("abonnement/getAll")
		    public List<Abonnement> getAllAbonnement(){
		        return abonnementService.Getall();
		    }
		 /////////////////////////////////////////********mail******//////////////////////////////////////////////////////////////
		//ajout abonnement
		
		   @PostMapping("/ajoutAbonnement")
		    public void ajoutAbonnement(@RequestBody Abonnement abonnement){
			  // abonnement.setUser(userrep.findById(1).get());//3tina user id 1
			   abonnementService.addAbonnement(abonnement);
			   
				try{
				    
			    		String body = "success d'ajout ";
			    		twillioService.sendSms(to, from, body);
			    		}
		           	     
		            catch (Exception e) {
				         e.printStackTrace();
				     }
					
	    	}
		   //////////////////////////////////////////////////////
		
		//tester l'ajout de poucentage de remise
		@PostMapping("AjouterPourcentage/{id_chef}")
		public ResponseEntity<Abonnement>addPourcentage(@PathVariable("id_chef")int id,@RequestBody Abonnement porcentage){
			Abonnement abonn= abonnementService.addPourcentage(id,porcentage);
			return new ResponseEntity<Abonnement>(abonn,HttpStatus.CREATED);
		}
		
		//la liste des remises pour les abonnements
		@GetMapping("RemiseAbonn/{Remise_id}")
		public ResponseEntity<?> remiseAbonnement(@PathVariable("Remise_id")int id) // List<Abonnement>
		{
		//return	abonnementService.remiseAbonnement(id);
			List<Abonnement> listpro=abonnementService.remiseAbonnement(id);
			return new ResponseEntity<>(listpro,HttpStatus.OK);
		
			
		}
			
		
/////*******************SMS TWILLIO***************************

			@Value("${app.twillio.fromPhoneNo}")
	    	private String from;
	    	
	    	@Value("${app.twillio.toPhoneNo}")
	    	private String to;


		    	@GetMapping("/sendSms/{id}")
		    	public float sendSms(Abonnement abonnement,@PathVariable("id") int id) {
					LocalDateTime localDate = LocalDateTime.now();
					 float res=0;
						Date dateAvant=abonnrep.findById(id).get().getDateFin();		
						
						Date dateApres=Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
						 float diff = dateApres.getTime() - dateAvant.getTime();
						try{
					    res=(diff / (1000*60*60*24));
			            if (res>=0){
				    		String body = "encore abonnement disponible!!";
				    		twillioService.sendSms(to, from, body);
				    		}}
			           	     
			            catch (Exception e) {
					         e.printStackTrace();
					     }
						return res;
		    	}
		    	///////////////////////////
		    	@PostMapping("/Rappel/{id}")
				@ResponseBody
				public void Rappel(Abonnement abonnement,@PathVariable("id") int id){
					 LocalDateTime localDate = LocalDateTime.now();
					 float res=0;
					Date dateAvant=abonnrep.findById(id).get().getDateFin();		
					
					Date dateApres=Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
					 float diff = dateApres.getTime() - dateAvant.getTime();
					try{
				    res=(diff / (1000*60*60*24));
				    		
		           if (res <= 30.0){
		    		
		    			msg.setTo("mhamdi.wejden@esprit.tn"); 
		    			msg.setSubject("atention!!"); 
		    			msg.setText("votre abonnement va etre expiré");
		    			javaMailSender.send(msg);
		    			
		            }

			     } catch (Exception e) {
			         e.printStackTrace();
			     }}
		    	/////////////////////////
		    	//////////////////***********************************PDF*********************************
		    	@GetMapping("/download")

				 
				    public void downloadPDFResource(HttpServletResponse response) {
				        try {
				        	Optional<Abonnement> abonnement=abonnementService.findAbonnementById(5);
				        	
				        		Path file = Paths.get(pdfService.generatePdf(abonnement).getAbsolutePath());
					            if (Files.exists(file)) {
					                response.setContentType("application/pdf");
					                response.addHeader("Content-Disposition",
					                        "attachment; filename=" + file.getFileName());
					                Files.copy(file, response.getOutputStream());
					                response.getOutputStream().flush();
					            }
				        	
				            
				        } catch (IOException | com.itextpdf.text.DocumentException ex) {
				            ex.printStackTrace();}
				        }
		
				        
		    	
		    	



}


