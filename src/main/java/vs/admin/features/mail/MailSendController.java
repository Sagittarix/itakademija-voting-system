package vs.admin.features.mail;

import java.sql.Date;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class MailSendController {
	
	@Autowired
	private MailSendService mailSendService;
	
	@RequestMapping(value = "/api/mail", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "Sending mail to representative")
	public  void MailSending(){
		try{
			mailSendService.SendMail();
		}catch(MailException ex){
			System.err.println("***KLAIDA*** " + ex);
		}
		
	}


}