package vs.admin.features.password;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ResponseHeader;
import vs.representative.features.corrupted.votes.CorruptedVotes;
import vs.representative.features.corrupted.votes.CorruptedVotesRepository;

@RestController
public class PasswordController {

	@Autowired
	private PasswordService passwordService;

	@RequestMapping(value = "/api/password/gen", method = RequestMethod.GET)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "Get Password")
	public @ResponseBody String getGeneratedPassword() {

		return passwordService.PassGenerator();

	}

	@RequestMapping(value = "/api/password/hash", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	@ApiOperation(value = "Hash Password")
	public String getHashedPassword(@RequestParam String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		return passwordService.PassHashing(password);
	}

}