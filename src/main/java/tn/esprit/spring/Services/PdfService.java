package tn.esprit.spring.Services;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
//import org.xhtmlrenderer.pdf.ITextRenderer;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Optional;

//import com.itextpdf.text.DocumentException;

import tn.esprit.spring.Entities.Abonnement;
@Service

public class PdfService {
	/* private static final String PDF_RESOURCES = "/templates/";
	    private AbonnementService abonnementService;
	    private SpringTemplateEngine templateEngine;
	    Abonnement abonnement;

	    @Autowired
	    public PdfService( AbonnementService abonnementService, SpringTemplateEngine templateEngine) {
	        this.abonnementService = abonnementService;
	        this.templateEngine = templateEngine;
	    }

	    public File generatePdf(Optional<Abonnement> abonnement) throws IOException, DocumentException {
	        Context context = getContext(abonnement);
	        String html = loadAndFillTemplate(context);
	        return renderPdf(html);
	    }


	    private File renderPdf(String html) throws IOException, DocumentException {
	        File file = File.createTempFile("abonnement", ".pdf");
	        OutputStream outputStream = new FileOutputStream(file);
	        ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
	        renderer.setDocumentFromString(html, new ClassPathResource(PDF_RESOURCES).getURL().toExternalForm());
	        renderer.layout();
	        try {
				renderer.createPDF(outputStream);
			} catch (com.lowagie.text.DocumentException e) {
				e.printStackTrace();
			}
	        outputStream.close();
	        file.deleteOnExit();
	        return file;
	    }

	    private Context getContext(Optional<Abonnement> abonnement) {
	    	
	        Context context = new Context();
	        
			context.setVariable("abonnement", abonnement.get());
	        return context;
	    }

	    private String loadAndFillTemplate(Context context) {
	        return templateEngine.process("abonnement", context);
	    }*/


	}




