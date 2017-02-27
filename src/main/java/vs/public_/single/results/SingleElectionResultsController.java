package vs.public_.single.results;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class SingleElectionResultsController {

	@Autowired
	SingleElectionResultsService singleElectionConstitencyService;

	@RequestMapping(value = "/api/constresults", method = RequestMethod.GET)
	public List<SingleElectionConstituency> getConstituencyResults() {
		return singleElectionConstitencyService.singleElectionConstituencyResults();
	}

	@RequestMapping(value = "/api/districtresults/{id}", method = RequestMethod.GET)
	public List<SingleElectionDistrict> getDistrictResults(@PathVariable Integer id) {
		return singleElectionConstitencyService.singleElectionDistrictResults(id);
	}

	@RequestMapping(value = "/api/candidatesresults/{id}", method = RequestMethod.GET)
	public List<SingleElectionResult> getSingleCandidatesResults(@PathVariable Integer id) {
		return singleElectionConstitencyService.getSingleElectionResults(id);
	}

	@RequestMapping(value = "/api/constresults/{id}", method = RequestMethod.GET)
	public SingleElectionConstituency getConstiuencyResultsDetails(@PathVariable Integer id) {
		return singleElectionConstitencyService.getConstiteuncyResult(id);
	}

	@RequestMapping(value = "/api/districtdetails/{id}", method = RequestMethod.GET)
	public List<SingleElectionResult> getDistrictResultsDetails(@PathVariable Integer id) {
		return singleElectionConstitencyService.getSingleElectionResultInDistrict(id);
	}

}