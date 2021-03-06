package vs.utils_.mail;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;
import vs.admin_.constituency.ConstituencyController;

@RestController
public class MailSendController {
	
	private static final Logger log = Logger.getLogger(MailSendController.class.getName());
	
	@Autowired
	private MailSendService mailSendService;

	@RequestMapping(value = "/api/ADMIN/mail", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "[UNUSED - ADMIN] - Sending mail to representative")
	public void MailSending(@RequestParam String toMail, @RequestParam String p,
			@RequestParam String loginName) {
		log.info("Mail send started. Mail to: " + toMail + " Login name: " + loginName);
		try {
			mailSendService.SendMail(toMail, p, loginName);
			log.info("Mail was send!");
		} catch (MailException ex) {
			log.error("Error sending mail: " + ex);
		}

	}

}