package vs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vs.admin.features.admin.representative.Representative;

@Controller
public class LinkController {

	@RequestMapping(value = "/admin")
	public String admin() {
		return "admin";
	}
	
	@RequestMapping(value = "/rep")
	public String representative() {
		return "rep";
	}
	
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
	
	
	@RequestMapping(value = "/currentuser", method = RequestMethod.GET)
    @ResponseBody
    public Representative currentUser(@CurrentUser Representative representative) {
        return representative;
    }
	
}
