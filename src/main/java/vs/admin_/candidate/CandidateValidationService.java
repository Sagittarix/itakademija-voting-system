package vs.admin_.candidate;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@Service
public class CandidateValidationService {

	@Autowired
	private CandidateCreateService candidateService;
	@Autowired
	private CandidateCSVParserService csvParser;
	@Autowired
	private CandidateRepository candidateRepository;
	private JSONParser parser = new JSONParser(0);
	private static final Logger log = Logger.getLogger(CandidateValidationService.class.getName());

	@SuppressWarnings("rawtypes")
	public ResponseEntity validateSaveConstituencyData(CandidateDataPackage data) {
		log.debug("CandidateValidationService - validateSaveConstituencyData started...");
		List<String[]> parsedStructure = headerRemover(csvParser.csvReader(data.getText(), data.getDelimiter()),
				data.isHasHeader());
		List<Candidate> candidates = new ArrayList<Candidate>();
		boolean dataIsValid = true;
		boolean candidateIsValid = true;

		for (String[] row : parsedStructure) {
			if (row.length != 5) {
				dataIsValid = false;
			}
		}

		if (dataIsValid) {
			candidateService.setCandidatesConstituency(data.getId());
			candidateService.setCandidatesData(parsedStructure);
			candidates = candidateService.saveConstituencyCandidates();

			for (Candidate can : candidates) {
				validateCandidate(can);
				if (validateCandidate(can).isEmpty() == false) {
					// collect hibernate messages here
					// System.out.println(candidateValidationService2.validateCandidate(can).get(0).toString());
					candidateIsValid = false;
				}
			}
		}

		if (dataIsValid && candidateIsValid) {
			for (Candidate can : candidates) {
				candidateRepository.createOrUpdateCandidate(can);
			}
			log.debug("CandidateValidationService - validateSaveConstituencyData finished!");
			return ResponseEntity.status(HttpStatus.CREATED).body(null);
		} else if (dataIsValid == false) {
			JSONObject json = null;
			try {
				json = (JSONObject) parser.parse("{\"error\": \"Bylos turinio struktura neatitinka reikalavimu\"}");
			} catch (ParseException e) {
				log.error("Error Parse, file structure is not valid " + e);
				e.printStackTrace();
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		} else {
			JSONObject json = null;
			try {
				json = (JSONObject) parser.parse("{\"error\": \"Kandidato duomenys turi klaidu\"}");
			} catch (ParseException e) {
				log.error("Error Parse, candidate data has mistakes " + e);
				e.printStackTrace();
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		}
	}

	@SuppressWarnings("rawtypes")
	public ResponseEntity validateSavePartyData(CandidateDataPackage data) {
		log.debug("CandidateValidationService - validateSavePartyData started...");
		List<String[]> parsedStructure = headerRemover(csvParser.csvReader(data.getText(), data.getDelimiter()),
				data.isHasHeader());
		List<Candidate> candidates = new ArrayList<Candidate>();
		boolean dataIsValid = true;
		boolean candidateIsValid = true;

		for (String[] row : parsedStructure) {
			if (row.length != 6) {
				dataIsValid = false;
			}
		}

		if (dataIsValid) {
			candidateService.setCandidatesParty(data.getId());
			candidateService.setCandidatesData(parsedStructure);
			candidates = candidateService.savePartyCandidates();

			for (Candidate can : candidates) {
				validateCandidate(can);
				if (validateCandidate(can).isEmpty() == false) {
					// collect hibernate messages here
					// System.out.println(candidateValidationService2.validateCandidate(can).get(0).toString());
					candidateIsValid = false;
				}
			}
		}

		if (dataIsValid && candidateIsValid) {
			for (Candidate can : candidates) {
				candidateRepository.createOrUpdateCandidate(can);
			}
			log.debug("CandidateValidationService - validateSavePartyData finshed!");
			return ResponseEntity.status(HttpStatus.CREATED).body(null);
		} else if (dataIsValid == false) {
			JSONObject json = null;
			try {
				json = (JSONObject) parser.parse("{\"error\": \"Bylos turinio struktura neatitinka reikalavimu\"}");
			} catch (ParseException e) {
				log.error("Error Parse, file structure is not valid " + e);
				e.printStackTrace();
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		} else {
			JSONObject json = null;
			try {
				json = (JSONObject) parser.parse("{\"error\": \"Kandidato duomenys turi klaidu\"}");
			} catch (ParseException e) {
				log.error("Error Parse, candidate data has mistakes " + e);
				e.printStackTrace();
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		}
	}

	private List<String> validateCandidate(Candidate candidate) {
		log.debug("CandidateValidationService - validateCandidate started...");
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		List<String> violationMessages = new ArrayList<String>();

		if (validator.validate(candidate).isEmpty() == false) {
			Set<ConstraintViolation<Candidate>> constraintViolationsCandidate = validator.validate(candidate);
			for (ConstraintViolation<Candidate> constraintViolation : constraintViolationsCandidate) {
				violationMessages.add(constraintViolation.getMessage());
			}
			constraintViolationsCandidate.clear();
		}
		log.debug("CandidateValidationService - validateCandidate finished!");
		return violationMessages;
	}

	private List<String[]> headerRemover(List<String[]> parsedStructure, boolean header) {
		boolean hasHeader = false;
		hasHeader = header;
		if (hasHeader) {
			parsedStructure.remove(0);
		}
		return parsedStructure;
	}
}
