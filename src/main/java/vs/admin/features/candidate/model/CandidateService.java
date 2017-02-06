package vs.admin.features.candidate.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vs.admin.features.admin.district.District;
import vs.admin.features.party.model.Party;

@Service
public class CandidateService {

	@Autowired
	private CandidateRepository candidateRepository;

	private String candidatesData;
	private Integer candidatesDistrict;
	private Integer candidatesParty;

	private String[] row;
	private String[] cell;

	public void saveDistrictCandidates() {
		parseRows();
		for (String row : row) {
			cell = null;
			parseCells(row);
			candidateRepository.createOrUpdateCandidate(createDistrictCandidate());
		}
	}

	public void savePartyCandidates() {
		parseRows();
		for (String row : row) {
			cell = null;
			parseCells(row);
			candidateRepository.createOrUpdateCandidate(createPartyCandidate());
		}
	}

	private void parseRows() {
		this.row = this.candidatesData.split("\\r?\\n");
	}

	private void parseCells(String row) {
		this.cell = row.split(";");
	}

	private Candidate createDistrictCandidate() {
		Candidate candidate = new Candidate();
		candidate.setCandidateName(cell[0]);
		candidate.setCandidateSurname(cell[1]);
		candidate.setCandidateDateOfBirth(cell[2]);
		candidate.setCandidatePersonalID(cell[3]);
		candidate.setCandidateDescription(cell[4]);
		District idSetter = new District();
		idSetter.setId(candidatesDistrict);
		candidate.setCandidateDistrict(idSetter);
		candidate.setCandidateParty(null);
		return candidate;
	}

	private Candidate createPartyCandidate() {
		Candidate candidate = new Candidate();
		candidate.setCandidateName(cell[0]);
		candidate.setCandidateSurname(cell[1]);
		candidate.setCandidateDateOfBirth(cell[2]);
		candidate.setCandidatePersonalID(cell[3]);
		candidate.setCandidateDescription(cell[4]);
		candidate.setCandidateNumberInParty(Integer.valueOf(cell[5]));
		candidate.setCandidateDistrict(null);
		Party idSetter = new Party();
		idSetter.setId(candidatesParty);
		candidate.setCandidateParty(idSetter);
		return candidate;
	}

	public void setCandidatesParty(Integer candidatesParty) {
		this.candidatesParty = candidatesParty;
	}

	public void setCandidatesData(String districtCandidates) {
		this.candidatesData = districtCandidates;
	}

	public void setCandidatesDistrict(Integer candidatesDistrict) {
		this.candidatesDistrict = candidatesDistrict;
	}
}
