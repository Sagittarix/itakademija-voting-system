package vs.admin_.candidate;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CandidateRepository {
	private static final String FIND_ALL = "SELECT c FROM Candidate c " + "WHERE c.candidateDeletedDate is NULL";

	private static final String FIND_ALL_CONSTITUENCY = "SELECT c FROM Candidate c "
			+ "LEFT JOIN c.candidateConstituency cc " + "WHERE c.candidateDeletedDate is null " + "AND cc.id=:id";

	private static final String FIND_ALL_PARTY = "SELECT c FROM Candidate c " + "LEFT JOIN c.candidateParty cp "
			+ "WHERE c.candidateDeletedDate is NULL " + "AND cp.id=:id";

	@Autowired
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Candidate> findAllUndeletedCandidates() {
		return em.createQuery(FIND_ALL).getResultList();
	}

	/* ===================================================== */

	@Transactional
	public Candidate createOrUpdateCandidate(Candidate candidate) {
		if (candidate.getCandidateID() == null) {

			// find a match

			boolean canPersIdNoMatch = true;
			Candidate match = null;
			@SuppressWarnings("unchecked")
			List<Candidate> candidates = em.createQuery(FIND_ALL).getResultList();

			for (Candidate matchingCandidate : candidates) {
				if (matchingCandidate.equals(candidate)) {
					canPersIdNoMatch = false;
					match = matchingCandidate;
				}
			}

			if (canPersIdNoMatch == false) {

				if (match.getCandidateParty() != null) {
					// add constituency id
					Candidate updatedCandidate = new Candidate();
					updatedCandidate.setCandidateID(match.getCandidateID());
					updatedCandidate.setCandidateName(match.getCandidateName());
					updatedCandidate.setCandidateSurname(match.getCandidateSurname());
					updatedCandidate.setCandidateDateOfBirth(match.getCandidateDateOfBirth());
					updatedCandidate.setCandidatePersonalID(match.getCandidatePersonalID());
					updatedCandidate.setCandidateDescription(match.getCandidateDescription());
					updatedCandidate.setCandidateDeletedDate(null);

					updatedCandidate.setCandidateParty(match.getCandidateParty());
					updatedCandidate.setCandidateNumberInParty(match.getCandidateNumberInParty());

					updatedCandidate.setCandidateConstituency(candidate.getCandidateConstituency());

					em.persist(em.merge(updatedCandidate));
					return updatedCandidate;

				} else if (match.getCandidateConstituency() != null) {
					// add party id & number in party
					Candidate updatedCandidate = new Candidate();
					updatedCandidate.setCandidateID(match.getCandidateID());
					updatedCandidate.setCandidateName(match.getCandidateName());
					updatedCandidate.setCandidateSurname(match.getCandidateSurname());
					updatedCandidate.setCandidateDateOfBirth(match.getCandidateDateOfBirth());
					updatedCandidate.setCandidatePersonalID(match.getCandidatePersonalID());
					updatedCandidate.setCandidateDescription(match.getCandidateDescription());
					updatedCandidate.setCandidateDeletedDate(null);

					updatedCandidate.setCandidateParty(candidate.getCandidateParty());
					updatedCandidate.setCandidateNumberInParty(candidate.getCandidateNumberInParty());

					updatedCandidate.setCandidateConstituency(match.getCandidateConstituency());

					em.persist(em.merge(updatedCandidate));
					return updatedCandidate;
				} else {
					System.err.println("candidate merge error");
					return null;
				}

			} else {
				em.persist(candidate);
				return candidate;
			}
		} else {
			return null; // if candidate id is not null
		}
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~update--disabled
		// } else {
		//
		// Candidate merged = em.merge(candidate);
		// em.persist(merged);
		// return merged;
		//
		// }
	}

	/* ===================================================== */

	@SuppressWarnings("unchecked")
	public List<Candidate> findCandidatesByConstituencyId(Integer id) {
		return em.createQuery(FIND_ALL_CONSTITUENCY).setParameter("id", id).getResultList();
	}

	@Transactional
	public void deleteCandidatesByConstituencyId(Integer constituencyId) {
		@SuppressWarnings("unchecked")
		List<Candidate> candidatesToDelete = em.createQuery(FIND_ALL_CONSTITUENCY).setParameter("id", constituencyId)
				.getResultList();

		for (Candidate candidate : candidatesToDelete) {
			if (candidate.getCandidateParty() != null) {
				candidate.setCandidateConstituency(null);
				em.persist(candidate);
			} else {
				Date date = new Date();
				candidate.setCandidateDeletedDate(date);
				em.persist(candidate);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<Candidate> findCandidatesByPartyId(Integer partyId) {
		return em.createQuery(FIND_ALL_PARTY).setParameter("id", partyId).getResultList();
	}

	@Transactional
	public void deleteCandidatesByPartyId(Integer partyId) {
		@SuppressWarnings("unchecked")
		List<Candidate> candidatesToDelete = em.createQuery(FIND_ALL_PARTY).setParameter("id", partyId).getResultList();

		for (Candidate candidate : candidatesToDelete) {
			if (candidate.getCandidateConstituency() != null) {
				candidate.setCandidateParty(null);
				candidate.setCandidateNumberInParty(null);
				em.persist(candidate);
			} else {
				Date date = new Date();
				candidate.setCandidateDeletedDate(date);
				em.persist(candidate);
			}
		}
	}

}