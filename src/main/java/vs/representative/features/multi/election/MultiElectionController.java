package vs.representative.features.multi.election;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vs.admin.features.party.model.Party;

@RestController
public class MultiElectionController {

	@Autowired
	private MultiElectionRepository multiElectionRepository;
	
	@RequestMapping(value = "/api/reg-votes-multi", method = RequestMethod.GET)
	public List<MultiElection> findAllElection() {
		return multiElectionRepository.findAllElection();
	}
	
	@RequestMapping(value = "/api/reg-votes-multi", method = RequestMethod.POST)
	public MultiElection createOrUpdateMulti(@RequestBody MultiElection multiElection) {
		return multiElectionRepository.saveOrUpdate(multiElection);
	}
}