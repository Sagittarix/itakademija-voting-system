package vs.public_.multi.results;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import vs.public_.download.results.DownloadResultsService;
import vs.public_.single.results.ElectionDetails;

@RestController
@CrossOrigin
public class MultiElectionResultsController {

	private static final Logger log = Logger.getLogger(MultiElectionResultsController.class.getName());
	
	@Autowired
	MultiElectionResultsService multiElectionResultsService;

	@RequestMapping(value = "/api/PUBLIC/multicons", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public List<MultiElectionResults> getConstituencyResults() {
		log.info("||--> was used." );
		return multiElectionResultsService.getMultiElectionResults();
	}

	@RequestMapping(value = "/api/PUBLIC/multiconslist", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public List<MultiElectionConstituencyList> getConstituencyResultsList() {
		log.info("||--> was used." );
		return multiElectionResultsService.getMultiElectionConstituencyList();
	}

	@RequestMapping(value = "/api/PUBLIC/multidistlist/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public List<MultiElectionDistrictList> getDistrictsResultsList(@PathVariable Integer id) {
		log.info("||--> was used." );
		return multiElectionResultsService.getResultsOfDistricts(id);
	}

	@RequestMapping(value = "/api/PUBLIC/multidis/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public List<MultiElectionResults> getPartiesDistrictResultsList(@PathVariable Integer id) {
		log.info("||--> was used." );
		return multiElectionResultsService.getDistrictPartiesResults(id);
	}

	@RequestMapping(value = "/api/PUBLIC/multicons/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public List<MultiElectionResults> getPartiesConsResultsList(@PathVariable Integer id) {
		log.info("||--> was used." );
		return multiElectionResultsService.getConstituencyPartiesResults(id);
	}

	@RequestMapping(value = "/api/PUBLIC/multiDetails/", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public ElectionDetails getSinglElectionDetails() {
		log.info("||--> was used." );
		return multiElectionResultsService.getMultiElectionDetails();
	}

	@RequestMapping(value = "/api/PUBLIC/multiDistrictDetails/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "[PUBLIC] - ")
	public ElectionDistrictDetails getSinglElectionDetails(@PathVariable Integer id) {
		log.info("||--> was used." );
		return multiElectionResultsService.getDistrictElectionDetails(id);
	}

}
